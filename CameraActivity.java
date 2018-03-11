package com.example.user.trial_android_sql;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class CameraActivity extends AppCompatActivity {
    private ImageView Display_taken_image;
    private int camerarequest=432; //integer to tell you what activity was run. Use different codes for different parts! i.e. later when you upload your images.
    private int request_camera_permissions = 5050; //integer to tell me i did a permissions activity for the camera
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Requestpermissions();
        Display_taken_image = (ImageView) findViewById(R.id.camerapictureview); //the image to show picture taken.
        findViewById(R.id.take_a_pic).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                opencamera();
            }
        });
    }
    private void opencamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, this.request_camera_permissions); // i placed a random number here because we don't care what results from it..
    }

    private void Requestpermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                this.request_camera_permissions); //camerarequest is a request code. just to distinguish so that onActivityResult can distinguish
    }
    @Override
    //So this onActivityResult is so that you can get the image thumbnail and prepare it for sending.
    //I ended the code here on encoded_camera because i don't know what else you might need to do.
    //but anyway just replace the string face_encoded with encoded_camera when you want to send the POST request over.
    //face_encoded is for registration (although you can potentially use more face_encoded s when you need to send multiple face images.
    //You also need to tell me what you called each of the keys in params when you're sending the json file over.
    //or my server will reject it.
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        //STACK WITH YOUR OTHER IMAGE SELECTS, ETC.
        if(requestCode == this.camerarequest){ // i.e the request code was that of the camera...
            Bitmap item = (Bitmap) data.getExtras().get("data"); //so i make it a bitmap
            ByteArrayOutputStream bytes = new ByteArrayOutputStream(); //need this.
            item.compress(Bitmap.CompressFormat.JPEG, 100, bytes); //into bytes.
            byte[] imagebytes_camera = bytes.toByteArray();
            String encoded_camera = Base64.encodeToString(imagebytes_camera, Base64.DEFAULT);
            this.Display_taken_image.setImageBitmap(item);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == request_camera_permissions) { //indicate that it was camera request permissions. STACK WITH YOUR OTHER PERMISSIONS

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
    //PS this WILL crash the entire app if camera permissions weren't granted.