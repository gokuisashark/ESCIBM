package com.example.fish.escpart1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class StartUpActivity extends AppCompatActivity {

    private Button loginbutton;
    private Button registerbutton;
    private EditText editTextUsername, editTextPassword;
    private HashMap<String,String> params;
    private int CAMERA_REQUEST =432; //integer to tell you what activity was run. Use different codes for different parts! i.e. later when you upload your images.
    private int CAMERA_PERMISSION_REQUEST = 5050; //integer to tell me i did a permissions activity for the camera
    private String username, password, encodedImage;
    private ImageView captcha_image;
    private EditText captcha_ans;
    private Text texttest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        captcha_image = (ImageView) findViewById(R.id.textimage);
        captcha_ans = (EditText) findViewById(R.id.textans) ;
        loginbutton = (Button) findViewById(R.id.loginbutton);
        registerbutton = (Button) findViewById(R.id.registerbutton);
        editTextUsername = findViewById(R.id.loginusereditview);
        editTextPassword = findViewById(R.id.passwordeditview);
        texttest = new Text(600, 150, 4, Text.TextOptions.UPPERCASE_ONLY);
        captcha_image.setImageBitmap(texttest.getImage());
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
        params = new HashMap<>();
        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();
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
        if (!texttest.checkAnswer(captcha_ans.getText().toString().trim())) {
            captcha_ans.setError("Incorrect Captcha entry.");
        } else { //passed the check.
            Requestpermissions();
            openCamera();
        }
    }

    private void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, this.CAMERA_REQUEST); // i placed a random number here because we don't care what results from it..
    }

    private void Requestpermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                this.CAMERA_PERMISSION_REQUEST); //CAMERA_REQUEST is a request code. just to distinguish so that onActivityResult can distinguish
    }
    @Override
    //So this onActivityResult is so that you can get the image thumbnail and prepare it for sending.
    //I ended the code here on encoded_Cmera because i don't know what else you might need to do.
    //but anyway just replace the string face_encoded with encoded_Cmera when you want to send the POST request over.
    //face_encoded is for registration (although you can potentially use more face_encoded s when you need to send multiple face images.
    //You also need to tell me what you called each of the keys in params when you're sending the json file over.
    //or my server will reject it.
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        //STACK WITH YOUR OTHER IMAGE SELECTS, ETC.
        if(requestCode == this.CAMERA_REQUEST){ // i.e the request code was that of the camera...
            Bitmap item = (Bitmap) data.getExtras().get("data"); //so i make it a bitmap
            ByteArrayOutputStream bytes = new ByteArrayOutputStream(); //need this.
            item.compress(Bitmap.CompressFormat.JPEG, 100, bytes); //into bytes.
            byte[] imagebytes_camera = bytes.toByteArray();
            encodedImage= Base64.encodeToString(imagebytes_camera, Base64.DEFAULT);

            //if everything is fine
            params.put("password", password);
            params.put("username", username);
            params.put("face", encodedImage);
            UserLogin ul = new UserLogin(getApplicationContext());
            ul.execute(params);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST) { //indicate that it was camera request permissions. STACK WITH YOUR OTHER PERMISSIONS

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //If permission is granted
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Permission was denied for camera.", Toast.LENGTH_LONG).show();
                //Requestpermissions();
                //TODO: UNCOMMENT THE ABOVE LINE TO ANNOY THE FUCK OUT OF SOMEONE IT'S WONDERFUL
                // TODO: DELETE THE ABOVE LINE
                //TODO: DELETE THE ABOVE COMMENT AND THIS ONE
            }

        }
    }
}
