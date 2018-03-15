package com.example.fish.escpart1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {

    JSONObject payload, user;
    String jsonString;
    TextView accountNumberTextView, currentBalanceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        jsonString = (String) getIntent().getSerializableExtra("jsonString");
        accountNumberTextView = findViewById(R.id.accountnumber);
        currentBalanceTextView = findViewById(R.id.balanceamount);
        try {
            payload = new JSONObject(jsonString);
            user = payload.getJSONObject("user");
            accountNumberTextView.setText(padAccount(user.getString("accountnumber")));
            currentBalanceTextView.setText(user.getString("amount"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private String padAccount(String accountNumber){
        String base = "0000" +
                "0000" +
                "0000" +
                "0000";
        int length = accountNumber.length();
        length = 16 - length;
        accountNumber = base.substring(0,length) + accountNumber;
        String padded = accountNumber.substring(0,4) + "-" + accountNumber.substring(4,8)
                + "-" + accountNumber.substring(8,12) + "-" + accountNumber.substring(12,16);
        return padded;
    }
}
