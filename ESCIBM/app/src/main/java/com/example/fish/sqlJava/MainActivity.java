//package com.example.fish.sqlJava;
//
//
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Toast;
//
//import com.example.fish.escpart1.R;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//
//public class MainActivity extends AppCompatActivity {
//
//    EditText editTextUsername, editTextEmail, editTextPassword;
//    RadioGroup radioGroupGender;
//    //private Button buttonchooseimage;
//    //private ImageView selecteduploadimage;
//    //private static final int STORAGE_PERMISSION_CODE = 123; //Don't touch. no idea what it does
//    //private int PICK_IMAGE_REQUEST =1; //image request code. don't touch. don't know what it does.
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //if loggedin is true. but i haven't done the stupid shared pref thing either...
//        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
//            finish();
//            startActivity(new Intent(this, ProfileActivity.class));
//            return;
//        }
//
//        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
//        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
//        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
//        radioGroupGender = (RadioGroup) findViewById(R.id.radioGender);
//
//
//
//        findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //if user pressed on button register
//                registerUser();
//            }
//        });
//
//        findViewById(R.id.textViewLogin).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //if user pressed on login
//                finish();
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            }
//        });
//
//    }
//
//
//
//
//
//
//    /*private void Requestpermissions(){
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)==1){
//            return; //ie permission was already granted
//        }
//        else{
//            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
//                //if permission getting forced out
//            }
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
//        }
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
//        if(requestCode==STORAGE_PERMISSION_CODE){
//
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                //If permission is granted
//            } else {
//                //Displaying another toast if permission is not granted
//                Toast.makeText(this, "Permission was denied.", Toast.LENGTH_LONG).show();
//            }
//
//        }
//    }*/
//
//    private void registerUser() {
//        final String username = editTextUsername.getText().toString().trim();
//        final String email = editTextEmail.getText().toString().trim();
//        final String password = editTextPassword.getText().toString().trim();
//
//        final String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();
//
//        //first we will do the validations
//
//        if (TextUtils.isEmpty(username)) {
//            editTextUsername.setError("Please enter username");
//            editTextUsername.requestFocus();
//            return;
//        }
//
//        if (TextUtils.isEmpty(email)) {
//            editTextEmail.setError("Please enter your email");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            editTextEmail.setError("Enter a valid email");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if (TextUtils.isEmpty(password)) {
//            editTextPassword.setError("Enter a password");
//            editTextPassword.requestFocus();
//            return;
//        }
//
//        //if it passes all the validations
//
//
//    }
//
//
//    //obscure method to get a file path from uri. You get a nice string.
//    /*public String getPath(Uri uri){
//        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
//        cursor.moveToFirst();
//        String document_id = cursor.getString(0);
//        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
//        cursor.close();
//
//        cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,MediaStore.Images.Media._ID + " = ? ", new String[]{document_id},null);
//        cursor.moveToFirst();
//        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//        cursor.close();
//        return path;
//
//
//
//    }
//
//    public void uploadMultipart(){
//        //getting name for the image
//        String name = editText.getText().toString().trim();
//
//        //getting the actual path of the image
//        String path = getPath(filePath);
//
//        //Uploading code
//        try {
//            String uploadId = UUID.randomUUID().toString();
//
//            //Creating a multi part request
//            new MultipartUploadRequest(this, uploadId, URLs.URL_REGISTER)
//                    .addFileToUpload(path, "image") //Adding file
//                    .addParameter("name", name) //Adding text parameter to the request
//                    .setNotificationConfig(new UploadNotificationConfig())
//                    .setMaxRetries(2)
//                    .startUpload(); //Starting the upload
//
//        } catch (Exception exc) {
//            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }*/
//
//}