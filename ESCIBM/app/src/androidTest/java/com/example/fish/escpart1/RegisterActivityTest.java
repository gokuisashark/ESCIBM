package com.example.fish.escpart1;

import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by Li Xueqing on 15/3/2018.
 */
public class RegisterActivityTest extends ActivityInstrumentationTestCase2<RegisterActivity> {
    private RegisterActivity mActivity = null;


    @SuppressWarnings("deprecation")
    public RegisterActivityTest() {
        super("com.example.fish.RegisterActivity",RegisterActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();
    }

    public void testInvalidEmailAddress() {
        final EditText emailView = mActivity.findViewById(R.id.emailEditView);

        // set invalid email address
        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                emailView.setText("abcdefg@qqqqq");
                //emailView.setText("xueqing_li@mymail.sutd.edu.sg");
            }
        });
        String email = emailView.getText().toString();
        email = "abcdefg@qqqqq";

        // check whether the email address is in valid format
        String ePattern =
                "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        assertEquals(true,m.matches());
    }

    @Test
    public void testPasswordLength() {
        final EditText passwordView = mActivity.findViewById(R.id.passwordeditview);

        // set password in short length
        // password minimum length is 6
        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                passwordView.setText("123");
            }
        });
        String password = passwordView.getText().toString();
        password = "123";

        // check whether password has a short length
        String ePattern = "^(?![0-9]+$)(?![a-z]+$)[0-9a-z]{8,}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(password);
        assertEquals(true,m.matches());
    }

    @Test
    public void testWrongPasswordFormat() {
        final EditText passwordView = mActivity.findViewById(R.id.passwordeditview);

        // set password in wrong format
        // password format requirement: more than 6 numbers or characters
        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                passwordView.setText("#$$%@#$_123");
            }
        });
        String password = passwordView.getText().toString();
        password = "#$$%@#$_123";

        // check whether password is in correct format
        String ePattern = "^[a-zA-Z0-9]{6,}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(password);
        assertEquals(true,m.matches());

    }

    @Test
    public void testInvalidNRICFormat() {
        final EditText NRICView = mActivity.findViewById(R.id.nricfinEditView);

        // set invalid NRIC format
        // NRIC format: S/T/F/G + 7 numbers + 1 alphabet
        String NRIC = NRICView.getText().toString();
        NRIC = "E11111111";

        // check whether NRIC is valid or not
        String ePattern = "^[STFG]\\d{7}[A-Z]$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(NRIC);
        assertEquals(true, m.matches());
    }


    @Test
    public void testInvalidPostalCode() {
        final EditText postalCodeView = mActivity.findViewById(R.id.postalCodeEditView);

        // set invalid postal code
        // postal code format: 7 numbers
        String postalCode = postalCodeView.getText().toString();
        postalCode = "3940asedf";

        // check whether postal code is valid or not
        String ePattern = "^[0-9]{7}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(postalCode);
        assertEquals(true,m.matches());
    }

    @Test
    public void testInvalidPhoneNumber() throws InterruptedException {
        final EditText phoneNumberView = mActivity.findViewById(R.id.contactNumberEditView);
        // set invalid phone number
        // phone number format: 8 numbers
        String phoneNumber = phoneNumberView.getText().toString();
        phoneNumber = "111829302";

        // check whether phone number is valid or not
        String ePattern = "^[0-9]{8}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(phoneNumber);
        assertEquals(true,m.matches());
    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}