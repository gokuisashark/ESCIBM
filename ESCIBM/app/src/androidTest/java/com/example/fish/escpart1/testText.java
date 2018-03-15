package com.example.fish.escpart1;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import org.junit.Before;

/**
 * Created by Li Xueqing on 15/3/2018.
 */

public class testText  extends ActivityInstrumentationTestCase2<RegisterActivity> {
    private RegisterActivity mActivity = null;

    @SuppressWarnings("deprecation")
    public testText()
    {
        super("com.example.fish.RegisterActivity",RegisterActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();
    }

    public void testText() {
        // simulate user action to input some value into EditText:
        final EditText mUsername = (EditText) mActivity.findViewById(R.id.contactNumberEditView);
        final EditText mPassword = (EditText) mActivity.findViewById(R.id.postalCodeEditView);
        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                mUsername.setText("hello");
                mPassword.setText("hello123");
            }
        });

        // Check if the EditText is properly set:
        assertEquals("hello", mUsername.getText());
        assertEquals("hello123", mPassword.getText());
    }

}
