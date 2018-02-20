package com.example.fish.escpart1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.net.HttpRetryException;

public class StartUpActivity extends AppCompatActivity {

    Button loginbutton;
    Button registerbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        loginbutton = (Button) findViewById(R.id.loginbutton);
        registerbutton = (Button) findViewById(R.id.registerbutton);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent loginButtonIntent = new Intent(context, HomePageActivity.class);
                startActivity(loginButtonIntent);
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
}
