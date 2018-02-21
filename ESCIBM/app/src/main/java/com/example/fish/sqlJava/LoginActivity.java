//package com.example.fish.sqlJava;
//
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.example.fish.escpart1.R;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//public class LoginActivity extends AppCompatActivity {
//
//    EditText editTextUsername, editTextPassword;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
//        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
//
//
//        //login
//        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                userLogin();
//            }
//        });
//
//        //if user presses on not registered
//        findViewById(R.id.textViewRegister).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //open register screen
//                finish();
//                startActivity(new Intent(getApplicationContext(), com.example.user.trial_android_sql.MainActivity.class));
//            }
//        });
//    }
//
//
//}