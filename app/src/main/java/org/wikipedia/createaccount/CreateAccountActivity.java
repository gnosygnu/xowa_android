package org.wikipedia.createaccount;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import org.mediawiki.api.json.Api;
import org.mediawiki.api.json.ApiException;
import org.mediawiki.api.json.RequestBuilder;
import org.wikipedia.*;
import org.wikipedia.activity.ActivityUtil;
import org.wikipedia.activity.ThemedActionBarActivity;
import org.wikipedia.analytics.CreateAccountFunnel;
import org.wikipedia.editing.CaptchaHandler;
import org.wikipedia.util.FeedbackUtil;
import org.wikipedia.views.PasswordTextInput;

import static org.wikipedia.util.FeedbackUtil.showMessage;
import static org.wikipedia.util.FeedbackUtil.showError;
import static org.wikipedia.util.FeedbackUtil.setErrorPopup;
import static org.wikipedia.util.DeviceUtil.hideSoftKeyboard;

public class CreateAccountActivity extends ThemedActionBarActivity {
    public static final int RESULT_ACCOUNT_CREATED = 1;
    public static final int RESULT_ACCOUNT_NOT_CREATED = 2;

    public static final int ACTION_CREATE_ACCOUNT = 1;

    public static final String LOGIN_REQUEST_SOURCE = "login_request_source";
    public static final String LOGIN_SESSION_TOKEN = "login_session_token";

    @Required(order = 1)
    private EditText usernameEdit;
    @Required(order = 2)
    @Password(order = 3)
    private EditText passwordEdit;
    @ConfirmPassword(order = 4, messageResId = R.string.create_account_passwords_mismatch_error)
    private EditText passwordRepeatEdit;
    @Email(order = 5, messageResId = R.string.create_account_email_error)
    private EditText emailEdit;

    private Button createAccountButton;
    private Button createAccountButtonCaptcha;

    private WikipediaApp app;

    private ProgressDialog progressDialog;

    private CaptchaHandler captchaHandler;

    private CreateAccountResult createAccountResult;

    private Validator validator;

    private CreateAccountFunnel funnel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (WikipediaApp) getApplicationContext();

        setContentView(R.layout.activity_create_account);

        usernameEdit = (EditText) findViewById(R.id.create_account_username);
        passwordRepeatEdit = (EditText) findViewById(R.id.create_account_password_repeat);
        emailEdit = (EditText) findViewById(R.id.create_account_email);
        createAccountButton = (Button) findViewById(R.id.create_account_submit_button);
        createAccountButtonCaptcha = (Button) findViewById(R.id.captcha_submit_button);
        EditText captchaText = (EditText) findViewById(R.id.captcha_text);
        View primaryContainer = findViewById(R.id.create_account_primary_container);
        PasswordTextInput passwordInput = (PasswordTextInput) findViewById(R.id.create_account_password_input);
        passwordEdit = passwordInput.getEditText();

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.dialog_create_account_checking_progress));

        captchaHandler = new CaptchaHandler(this, app.getPrimarySite(), progressDialog, primaryContainer,
                                            getString(R.string.create_account_activity_title),
                                            getString(R.string.create_account_button));

        // We enable the menu item as soon as the username and password fields are filled
        // Tapping does further validation
        validator = new Validator(this);
        validator.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                doCreateAccount();
            }

            @Override
            public void onValidationFailed(View view, Rule<?> rule) {
                if (view instanceof EditText) {
                    //Request focus on the EditText before setting error, so that error is visible
                    view.requestFocus();
                    setErrorPopup((EditText) view, rule.getFailureMessage());
                } else {
                    throw new RuntimeException("This should not be happening");
                }
            }
        });

        // Don't allow user to submit registration unless they've put in a username and password
        new NonEmptyValidator(new NonEmptyValidator.ValidationChangedCallback() {
            @Override
            public void onValidationChanged(boolean isValid) {
                createAccountButton.setEnabled(isValid);
            }
        }, usernameEdit, passwordEdit, passwordRepeatEdit);

        // Don't allow user to continue when they're shown a captcha until they fill it in
        new NonEmptyValidator(new NonEmptyValidator.ValidationChangedCallback() {
            @Override
            public void onValidationChanged(boolean isValid) {
                createAccountButtonCaptcha.setEnabled(isValid);
            }
        }, captchaText);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

        createAccountButtonCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

        passwordInput.setOnShowPasswordListener(new PasswordTextInput.OnShowPasswordListener() {
            @Override
            public void onShowPasswordChecked(boolean checked) {
                if (checked) {
                    ViewAnimations.slideOutRight(passwordRepeatEdit, new ViewPropertyAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(View view) {
                            // give it nonempty text, to appease NonEmptyValidator
                            passwordRepeatEdit.setText(" ");
                            passwordRepeatEdit.setVisibility(View.GONE);
                        }
                    });
                } else {
                    ViewAnimations.slideIn(passwordRepeatEdit, new ViewPropertyAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(View view) {
                            passwordRepeatEdit.setText("");
                            passwordRepeatEdit.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        });

        // Add listener so that when the user taps enter, it submits the captcha
        captchaText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    validator.validate();
                    return true;
                }
                return false;
            }
        });

        if (savedInstanceState != null && savedInstanceState.containsKey("result")) {
            createAccountResult = savedInstanceState.getParcelable("result");
            if (createAccountResult instanceof CreateAccountCaptchaResult) {
                captchaHandler.handleCaptcha(((CreateAccountCaptchaResult) createAccountResult).getCaptchaResult());
            }
        }

        findViewById(R.id.create_account_login_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // already coming from LoginActivity
                finish();
            }
        });

        funnel = new CreateAccountFunnel(app, getIntent().getStringExtra(LOGIN_REQUEST_SOURCE));

        // Only send the editing start log event if the activity is created for the first time
        if (savedInstanceState == null) {
            funnel.logStart(getIntent().getStringExtra(LOGIN_SESSION_TOKEN));
        }
        // Set default result to failed, so we can override if it did not
        setResult(RESULT_ACCOUNT_NOT_CREATED);
    }

    @Override
    protected void setTheme() {
        setActionBarTheme();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("result", createAccountResult);
    }

    public void showPrivacyPolicy(View v) {
        FeedbackUtil.showPrivacyPolicy(this);
    }

    public void handleError(@NonNull Throwable e) {
        if (e instanceof ApiException && ((ApiException) e).getCode() != null) {
            String errorCode = ((ApiException) e).getCode();
            funnel.logError(errorCode);
            switch (errorCode) {
                case "blocked":
                    if (app.getUserInfoStorage().isLoggedIn()) {
                        showMessage(this, R.string.create_account_blocked_error);
                    } else {
                        showMessage(this, R.string.create_account_blocked_anon_error);
                    }
                    break;
                case "acct_creation_throttle_hit":
                    showMessage(this, R.string.create_account_ip_throttle_error);
                    break;
                case "sorbs_create_account_reason":
                    showMessage(this, R.string.create_account_open_proxy_error);
                    break;
                case "userexists":
                    //Request focus before displaying error message, so that it pops up on its own
                    usernameEdit.requestFocus();
                    setErrorPopup(usernameEdit, getString(R.string.create_account_username_exists_error));
                    break;
                case "noname":
                    showMessage(this, R.string.create_account_noname_error);
                    break;
                case "invalidemailaddress":
                    showMessage(this, R.string.create_account_invalid_email_error);
                    break;
                case "passwordtooshort":
                    //FIXME: Find the value of $wgMinimalPasswordLength and tell the user the minimum pwd length
                    showMessage(this, R.string.create_account_password_too_short_error);
                    break;
                case "password-name-match":
                    showMessage(this, R.string.create_account_password_name_match_error);
                    break;
                case "createaccount-hook-aborted":
                    // show the actual message provided by the hook
                    showMessage(this, Html.fromHtml(((ApiException) e).getInfo()),
                            FeedbackUtil.LENGTH_DEFAULT);
                    break;
                default:
                    showMessage(this, R.string.create_account_generic_error);
                    break;
            }
        } else {
            showError(this, e);
        }
    }

    public void doCreateAccount() {
        String email = null;
        if (emailEdit.getText().length() != 0) {
            email = emailEdit.getText().toString();
        }
        new CreateAccountTask(this, usernameEdit.getText().toString(), passwordEdit.getText().toString(), email) {
            @Override
            public void onBeforeExecute() {
                progressDialog.show();
            }

            @Override
            public RequestBuilder buildRequest(Api api) {
                if (createAccountResult != null && createAccountResult instanceof CreateAccountCaptchaResult) {
                   return captchaHandler.populateBuilder(super.buildRequest(api));
                }
                return super.buildRequest(api);
            }

            @Override
            public void onCatch(Throwable caught) {
                Log.d("Wikipedia", "Caught " + caught.toString());
                if (!progressDialog.isShowing()) {
                    // no longer attached to activity!
                    return;
                }
                progressDialog.dismiss();
                handleError(caught);
            }

            @Override
            public void onFinish(final CreateAccountResult result) {
                if (!progressDialog.isShowing()) {
                    // no longer attached to activity!
                    return;
                }
                createAccountResult = result;
                if (result instanceof CreateAccountCaptchaResult) {
                    if (captchaHandler.isActive()) {
                        funnel.logCaptchaFailure();
                    } else {
                        funnel.logCaptchaShown();
                    }
                    captchaHandler.handleCaptcha(((CreateAccountCaptchaResult)result).getCaptchaResult());
                } else if (result instanceof CreateAccountSuccessResult) {
                    progressDialog.dismiss();
                    captchaHandler.cancelCaptcha();
                    funnel.logSuccess();
                    hideSoftKeyboard(CreateAccountActivity.this);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("username", ((CreateAccountSuccessResult) result).getUsername());
                    resultIntent.putExtra("password", passwordEdit.getText().toString());
                    setResult(RESULT_ACCOUNT_CREATED, resultIntent);
                    finish();
                } else {
                    progressDialog.dismiss();
                    captchaHandler.cancelCaptcha();
                    if (result.getResult().equals("captcha-createaccount-fail")) {
                        // So for now we just need to do the entire set of requests again. sigh
                        // Eventually this should be fixed to have the new captcha info come back.
                        createAccountResult = null;
                        doCreateAccount();
                    }
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

    @Override
    public void onStop() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        super.onStop();
    }
}
