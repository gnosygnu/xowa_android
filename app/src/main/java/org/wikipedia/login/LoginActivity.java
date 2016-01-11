package org.wikipedia.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import org.wikipedia.*;
import org.wikipedia.activity.ActivityUtil;
import org.wikipedia.activity.ThemedActionBarActivity;
import org.wikipedia.analytics.LoginFunnel;
import org.wikipedia.createaccount.CreateAccountActivity;
import org.wikipedia.util.FeedbackUtil;
import org.wikipedia.views.PasswordTextInput;

import static org.wikipedia.util.FeedbackUtil.setErrorPopup;
import static org.wikipedia.util.DeviceUtil.hideSoftKeyboard;

public class LoginActivity extends ThemedActionBarActivity {
    public static final int REQUEST_LOGIN = 100;

    public static final int RESULT_LOGIN_SUCCESS = 1;
    public static final int RESULT_LOGIN_FAIL = 2;

    public static final String LOGIN_REQUEST_SOURCE = "login_request_source";
    public static final String EDIT_SESSION_TOKEN = "edit_session_token";
    public static final String ACTION_CREATE_ACCOUNT = "action_create_account";

    private EditText usernameText;
    private EditText passwordText;
    private View loginButton;

    private WikipediaApp app;

    private LoginFunnel funnel;
    private String loginSource;

    private ProgressDialog progressDialog;
    private boolean wentStraightToCreateAccount;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (WikipediaApp)getApplicationContext();

        setContentView(R.layout.activity_login);

        usernameText = (EditText) findViewById(R.id.login_username_text);
        passwordText = ((PasswordTextInput) findViewById(R.id.login_password_input)).getEditText();
        View createAccountLink = findViewById(R.id.login_create_account_link);

        // Don't allow user to attempt login until they've put in a username and password
        new NonEmptyValidator(new NonEmptyValidator.ValidationChangedCallback() {
            @Override
            public void onValidationChanged(boolean isValid) {
                loginButton.setEnabled(isValid);
            }
        }, usernameText, passwordText);

        passwordText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    doLogin();
                    return true;
                }
                return false;
            }
        });

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        createAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCreateAccountActivity();
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.login_in_progress_dialog_message));
        progressDialog.setCancelable(false);

        funnel = new LoginFunnel(app);

        loginSource = getIntent().getStringExtra(LOGIN_REQUEST_SOURCE);

        if (getIntent().getBooleanExtra(ACTION_CREATE_ACCOUNT, false)) {
            wentStraightToCreateAccount = true;
            startCreateAccountActivity();
        } else if (savedInstanceState == null) {
            // Only send the login start log event if the activity is created for the first time
            logLoginStart();
        }

        // Assume no login by default
        setResult(RESULT_LOGIN_FAIL);
    }

    public void showPrivacyPolicy(View v) {
        FeedbackUtil.showPrivacyPolicy(this);
    }

    @Override
    protected void setTheme() {
        setActionBarTheme();
    }

    private void logLoginStart() {
        if (loginSource.equals(LoginFunnel.SOURCE_EDIT)) {
            funnel.logStart(
                    LoginFunnel.SOURCE_EDIT,
                    getIntent().getStringExtra(EDIT_SESSION_TOKEN)
            );
        } else {
            funnel.logStart(loginSource);
        }
    }

    private void startCreateAccountActivity() {
        funnel.logCreateAccountAttempt();
        Intent intent = new Intent(this, CreateAccountActivity.class);
        intent.putExtra(CreateAccountActivity.LOGIN_SESSION_TOKEN, funnel.getSessionToken());
        intent.putExtra(CreateAccountActivity.LOGIN_REQUEST_SOURCE, loginSource);
        startActivityForResult(intent, CreateAccountActivity.ACTION_CREATE_ACCOUNT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CreateAccountActivity.ACTION_CREATE_ACCOUNT) {
            if (wentStraightToCreateAccount) {
                logLoginStart();
            }
            if (resultCode == CreateAccountActivity.RESULT_ACCOUNT_CREATED) {
                usernameText.setText(data.getStringExtra("username"));
                passwordText.setText(data.getStringExtra("password"));
                funnel.logCreateAccountSuccess();
                FeedbackUtil.showMessage(this,
                        R.string.create_account_account_created_toast);
                doLogin();
            } else {
                funnel.logCreateAccountFailure();
            }
        }
    }

    private void doLogin() {
        final String username = usernameText.getText().toString();
        final String password = passwordText.getText().toString();
        new LoginTask(this, app.getPrimarySite(), username, password) {
            @Override
            public void onBeforeExecute() {
                progressDialog.show();
            }

            @Override
            public void onCatch(Throwable caught) {
                Log.d("Wikipedia", "Caught " + caught.toString());
                if (!progressDialog.isShowing()) {
                    // no longer attached to activity!
                    return;
                }
                progressDialog.dismiss();
                FeedbackUtil.showError(LoginActivity.this, caught);
            }

            @Override
            public void onFinish(LoginResult result) {
                super.onFinish(result);
                if (!progressDialog.isShowing()) {
                    // no longer attached to activity!
                    return;
                }
                progressDialog.dismiss();
                if (result.getCode().equals("Success")) {
                    funnel.logSuccess();

                    hideSoftKeyboard(LoginActivity.this);
                    setResult(RESULT_LOGIN_SUCCESS);

                    finish();
                } else {
                    funnel.logError(result.getCode());
                    handleError(result.getCode());
                }
            }
        }.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return ActivityUtil.defaultOnOptionsItemSelected(this, item)
                || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        hideSoftKeyboard(this);
        super.onBackPressed();
    }

    private void handleError(String result) {
        switch (result) {
            case "WrongPass":
            case "WrongPluginPass":
                // Authentication extensions, like CentralAuth, return "WrongPluginPass" if there
                // is no local account with the specified username, but there is a global account
                // with that name and the user didn't specify the correct password. To a user with
                // a global account (i.e. almost every single user), there is no difference between
                // WrongPass and WrongPluginPass, so we treat them the same here.
                passwordText.requestFocus();
                setErrorPopup(passwordText, getString(R.string.login_error_wrong_password));
                break;
            case "NotExists":
                usernameText.requestFocus();
                setErrorPopup(usernameText, getString(R.string.login_error_wrong_username));
                break;
            case "Illegal":
                usernameText.requestFocus();
                setErrorPopup(usernameText, getString(R.string.login_error_illegal));
                break;
            case "Blocked":
                FeedbackUtil.showMessage(this, R.string.login_error_blocked);
                break;
            case "Throttled":
                FeedbackUtil.showMessage(this, R.string.login_error_throttled);
                break;
            default:
                FeedbackUtil.showMessage(this, R.string.login_error_unknown);
                Log.d("Wikipedia", "Login failed with result " + result);
                break;
        }
    }

    @Override
    public void onStop() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("loginShowing", true);
    }
}
