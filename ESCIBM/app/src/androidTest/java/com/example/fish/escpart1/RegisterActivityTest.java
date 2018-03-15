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
    private EditText emailView;


    public RegisterActivityTest() {
        super("com.example.fish.RegisterActivity",RegisterActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();

        emailView = mActivity.findViewById(R.id.emailEditView);
        //Button saveRegistrationButton = (Button) mActivity.findViewById(R.id.registerSaveButton);
    }

    public void testPreconditions() {
        assertNotNull(emailView);
    }


    public void testInvalidEmailAddress() throws InterruptedException {


        String email = emailView.getText().toString();
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        assertEquals(true,m.matches());

        //Thread.sleep(10000);
//        saveRegistrationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = emailView.getText().toString();
//                String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
//                java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
//                java.util.regex.Matcher m = p.matcher(email);
//                assertEquals(true,m.matches());
//
//            }
//        });
    }

    @Test
    public void testTooShortPassword() throws InterruptedException {
        final EditText passwordView = mActivity.findViewById(R.id.passwordeditview);
        Button saveRegistrationButton = (Button) mActivity.findViewById(R.id.registerSaveButton);

        Thread.sleep(10000);
        saveRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordView.getText().toString();
                assertTrue(password.length() > 10);
            }
        });
    }

    @Test
    public void testWrongPasswordFormat() throws InterruptedException {
        final EditText passwordView = mActivity.findViewById(R.id.passwordeditview);
        Button saveRegistrationButton = (Button) mActivity.findViewById(R.id.registerSaveButton);

        Thread.sleep(10000);
        saveRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordView.getText().toString();
                String ePattern = "";
                java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
                java.util.regex.Matcher m = p.matcher(password);
                assertEquals(true,m.matches());
            }
        });
    }

    @Test
    public void testInvalidNRICFormat() throws InterruptedException {
        final EditText NRICView = mActivity.findViewById(R.id.nricfinEditView);
        Button saveRegistrationButton = (Button) mActivity.findViewById(R.id.registerSaveButton);

        Thread.sleep(10000);
        saveRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NRIC = NRICView.getText().toString();
                String ePattern = "^[STFG]\\d{7}[A-Z]$";
                java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
                java.util.regex.Matcher m = p.matcher(NRIC);
                assertEquals(true,m.matches());
            }
        });
    }

    @Test
    public void testInvalidPostalCode() throws InterruptedException {
        final EditText postalCodeView = mActivity.findViewById(R.id.postalCodeEditView);
        Button saveRegistrationButton = (Button) mActivity.findViewById(R.id.registerSaveButton);

        Thread.sleep(10000);
        saveRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postalCode = postalCodeView.getText().toString();
                String ePattern = "^[0-9]{7}$";
                java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
                java.util.regex.Matcher m = p.matcher(postalCode);
                assertEquals(true,m.matches());
            }
        });
    }

    @Test
    public void testInvalidPhoneNumber() throws InterruptedException {
        final EditText phoneNumberView = mActivity.findViewById(R.id.contactNumberEditView);
        Button saveRegistrationButton = (Button) mActivity.findViewById(R.id.registerSaveButton);

        Thread.sleep(10000);
        saveRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberView.getText().toString();
                String ePattern = "^[0-9]{8}$";
                java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
                java.util.regex.Matcher m = p.matcher(phoneNumber);
                assertEquals(true,m.matches());
            }
        });
    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}