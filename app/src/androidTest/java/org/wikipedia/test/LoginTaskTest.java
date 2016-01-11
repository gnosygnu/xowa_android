package org.wikipedia.test;

import android.support.annotation.StringRes;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.wikipedia.Site;
import org.wikipedia.WikipediaApp;
import org.wikipedia.editing.EditTokenStorage;
import org.wikipedia.login.LoginResult;
import org.wikipedia.login.LoginTask;
import org.wikipedia.testlib.TestLatch;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class LoginTaskTest {
    private static final Site TEST_WIKI_SITE = new Site("test.wikipedia.org");
    private static final String SUCCESS = "Success";
    private static final String USERNAME = getString(R.string.test_username);
    private static final String PASSWORD = getString(R.string.test_password);

    private final WikipediaApp app = WikipediaApp.getInstance();
    private final TestLatch completionLatch = new TestLatch();

    @Test
    public void testLogin() throws Throwable {
        runOnMainSync(new Runnable() {
            @Override
            public void run() {
                loginTestTask.execute();
            }
        });
        completionLatch.await();
    }

    private LoginTask loginTestTask = new LoginTask(app, TEST_WIKI_SITE, USERNAME, PASSWORD) {
        @Override
        public void onFinish(LoginResult result) {
            super.onFinish(result);
            assertThat(result.getCode(), equalTo(SUCCESS));
            app.getEditTokenStorage().get(TEST_WIKI_SITE, callback);
        }
    };

    private EditTokenStorage.TokenRetrievedCallback callback = new EditTokenStorage.TokenRetrievedCallback() {
        @Override
        public void onTokenRetrieved(String token) {
            assertThat(token.equals("+\\"), is(false));
            completionLatch.countDown();
        }

        @Override
        public void onTokenFailed(Throwable caught) {
            throw new RuntimeException(caught);
        }
    };

    private void runOnMainSync(Runnable r) {
        getInstrumentation().runOnMainSync(r);
    }

    private static String getString(@StringRes int id) {
        return getInstrumentation().getContext().getString(id);
    }
}