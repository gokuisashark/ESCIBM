package com.example.fish.escpart1;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Li Xueqing on 15/3/2018.
 */
public class RegisterActivityTest extends ActivityInstrumentationTestCase2<RegisterActivity> {
    private RegisterActivity mActivity = null;
    private InputChecker inputChecker;


    @SuppressWarnings("deprecation")
    public RegisterActivityTest() {
        super("com.example.fish.RegisterActivity",RegisterActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();
        inputChecker = new InputChecker(mActivity);
    }

    public void testInvalidEmailAddress() {
        final EditText emailView = mActivity.findViewById(R.id.emailEditView);
        String email;

        // set invalid email address
//        mActivity.runOnUiThread(new Runnable() {
//            public void run() {
//                emailView.setText("abcdefg@qqqqq");
//                //emailView.setText("xueqing_li@mymail.sutd.edu.sg");
//            }
//        });
//        email = emailView.getText().toString();
        email = "abcdefg@qqqqq";

        // check whether the email address is in valid format
        boolean checkEmail = inputChecker.emailChecker(email);
        assertEquals(true,checkEmail);
    }

    @Test
    public void testPasswordLength() {
        final EditText passwordView = mActivity.findViewById(R.id.pinedittext);

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
        final EditText passwordView = mActivity.findViewById(R.id.pinedittext);
        final EditText verifyPasswordView = mActivity.findViewById(R.id.verifyPasswordEditView);

        // set password in wrong format
        // password format requirement: more than 6 numbers or characters
        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                passwordView.setText("#$$%@#$_123");
            }
        });
        String password = passwordView.getText().toString();
        String verify_password = verifyPasswordView.getText().toString();
        password = "#$$%@#$_123";
        verify_password = "#$$%@#$_123";

        // check whether password is in correct format
        boolean checkPassword = inputChecker.passwordChecker(password,verify_password);
        assertEquals(true,checkPassword);

    }

    @Test
    public void testInvalidNRICFormat() {
        final EditText NRICView = mActivity.findViewById(R.id.nricfinEditView);

        // set invalid NRIC format
        // NRIC format: S/T/F/G + 7 numbers + 1 alphabet
        String NRIC = NRICView.getText().toString();
        NRIC = "E11111111";

        // check whether NRIC is valid or not
        boolean checkNRIC = inputChecker.nricChecker(NRIC);
        assertEquals(true,checkNRIC);
    }


    @Test
    public void testInvalidPostalCode() {
        final EditText postalCodeView = mActivity.findViewById(R.id.postalCodeEditView);

        // set invalid postal code
        // postal code format: 7 numbers
        String postalCode = postalCodeView.getText().toString();
        postalCode = "3940asedf";

        // check whether postal code is valid or not
        boolean checkPostalCode = inputChecker.postalCodeChecker(postalCode);
        assertEquals(true,checkPostalCode);
    }

    @Test
    public void testInvalidPhoneNumber() throws InterruptedException {
        final EditText phoneNumberView = mActivity.findViewById(R.id.contactNumberEditView);
        // set invalid phone number
        // phone number format: 8 numbers
        String phoneNumber = phoneNumberView.getText().toString();
        phoneNumber = "111829302";

        // check whether phone number is valid or not
        boolean checkPhoneNumber = inputChecker.phoneNumberChecker(phoneNumber);
        assertEquals(true,checkPhoneNumber);
    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}