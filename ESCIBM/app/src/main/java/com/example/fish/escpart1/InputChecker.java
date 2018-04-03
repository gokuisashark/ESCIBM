package com.example.fish.escpart1;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by setia on 3/16/2018.
 */

public class InputChecker {
    Context context;

    public boolean isSuccess() {
        return success;
    }

    boolean success;

    public InputChecker(Context context) {
        this.context = context;
        this.success = true;
    }

    public ArrayList<String> check(HashMap<String, String> inputFields) {
        ArrayList<String> warnings = new ArrayList<>();
        if (!emailChecker(inputFields.get("email"))){
            warnings.add("Invalid email");
            success = false;
        }
        if (!nricChecker(inputFields.get("nric"))) {
            warnings.add("Invalid NRIC");
            success = false;
        }
        if (!passwordChecker(inputFields.get("password"), inputFields.get("verifyPassword"))){
            success = false;
        }
        if (!postalCodeChecker(inputFields.get("postalcode"))) {
            warnings.add("Invalid postal code");
            success = false;
        }
        if (!phoneNumberChecker(inputFields.get("contact"))) {
            warnings.add("Invalid phone number");
            success = false;
        }
        return warnings;
    }



    boolean emailChecker(String email) {
        String ePattern =
                "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private boolean passwordChecker(String password, String verifyPassword) {
        if (!password.equals(verifyPassword)){
            Toast.makeText(context, "Password fields do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        String ePattern = "^[a-zA-Z0-9]{8,}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(password);
        if (!m.matches()){
            Toast.makeText(context, "Password has to be alphanumeric with at least one uppercase letter and more than 8 characters long", Toast.LENGTH_SHORT).show();
        }
        return m.matches();
    }

    private boolean nricChecker(String nric) {
        String ePattern = "^[STFG]\\d{7}[A-Z]$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(nric);
        return m.matches();
    }

    private boolean postalCodeChecker(String postalCode) {
        String ePattern = "^[0-9]{6}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(postalCode);
        return m.matches();
    }

    private boolean phoneNumberChecker(String phoneNumber) {
        String ePattern = "^[0-9]{8}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }





}
