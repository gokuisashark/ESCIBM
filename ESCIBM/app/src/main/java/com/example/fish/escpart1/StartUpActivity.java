package com.example.fish.escpart1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fish.sqlJava.RequestHandler;
import com.example.fish.sqlJava.SharedPrefManager;
import com.example.fish.sqlJava.URLs;
import com.example.fish.sqlJava.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class StartUpActivity extends AppCompatActivity {

    Button loginbutton;
    Button registerbutton;
    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        loginbutton = (Button) findViewById(R.id.loginbutton);
        registerbutton = (Button) findViewById(R.id.registerbutton);
        editTextUsername = findViewById(R.id.loginusereditview);
        editTextPassword = findViewById(R.id.passwordeditview);


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent registerButtonIntent = new Intent(context, RegisterActivity.class);
                startActivity(registerButtonIntent);
            }
        });
    }

    private void userLogin() {
        //first getting the values
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();
        //validating inputs

        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Please enter your username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        //if everything is fine


        UserLogin ul = new UserLogin();
        ul.execute(new String[]{username,password});
    }
}
