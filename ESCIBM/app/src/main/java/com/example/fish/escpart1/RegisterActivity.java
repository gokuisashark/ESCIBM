package com.example.fish.escpart1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fish.sqlJava.RequestHandler;
import com.example.fish.sqlJava.SharedPrefManager;
import com.example.fish.sqlJava.URLs;
import com.example.fish.sqlJava.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    String[] designation_array = {"Sir", "Dr", "Mr", "Ms", "Mrs", "Mdm"};
    String[] genders_array = {"M", "F", "Others"};
    Spinner designationSpinner, genderSpinner;
    EditText firstNameEditText, lastNameEditText, nricEditText, dobEditText,
            homeAddressEditText, postalCodeEditText, contactNumberEditText, emailEditText, passwordEditText, verifyPasswordEditText;
    String password, verifyPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button saveregistrationdetailsbutton = (Button) findViewById(R.id.registerSaveButton);
        designationSpinner = (Spinner) findViewById(R.id.designationSpinner);
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);

        ArrayAdapter<String> designationSpinnerAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, designation_array);
        designationSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        designationSpinner.setAdapter(designationSpinnerAdapter);

        ArrayAdapter<String> genderSpinnerAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, genders_array);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        firstNameEditText = findViewById(R.id.firstNameEditView);
        lastNameEditText = findViewById(R.id.lastNameEditView);
        nricEditText = findViewById(R.id.nricfinEditView);
        dobEditText = findViewById(R.id.dobEditView);
        homeAddressEditText = findViewById(R.id.homeAddressEditView);
        postalCodeEditText = findViewById(R.id.postalCodeEditView);
        contactNumberEditText = findViewById(R.id.contactNumberEditView);
        emailEditText = findViewById(R.id.emailEditView);

        genderSpinner.setAdapter(genderSpinnerAdapter);
        passwordEditText = findViewById(R.id.passwordEditView);
        verifyPasswordEditText = findViewById(R.id.verifyPasswordEditView);

        saveregistrationdetailsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> params = new HashMap<>();
                password = passwordEditText.getText().toString();
                verifyPassword = verifyPasswordEditText.getText().toString();
                if (!verifyPassword(password, verifyPassword)){
                    return;
                }
                params.put("username", emailEditText.getText().toString());
                params.put("firstname", firstNameEditText.getText().toString());
                params.put("lastname", lastNameEditText.getText().toString());
                params.put("NRIC", nricEditText.getText().toString());
                params.put("DOB", dobEditText.getText().toString());
                params.put("Address", homeAddressEditText.getText().toString());
                params.put("postalcode", postalCodeEditText.getText().toString());
                params.put("contact", contactNumberEditText.getText().toString());
                params.put("email", emailEditText.getText().toString());
                params.put("accountnumber", "1234"); //TODO: make this autoincrement in server
//                params.put("", designationSpinner.getSelectedItem().toString());
                params.put("amount", "0"); //TODO: make this initialised to zero in server
                params.put("gender",genderSpinner.getSelectedItem().toString());
                params.put("password", password);


                RegisterUser ru = new RegisterUser();
                ru.execute(params);
                Toast.makeText(RegisterActivity.this, "Details saved", Toast.LENGTH_SHORT).show();
            }
        });


    }
    boolean verifyPassword(String password, String verifyPassword) {
        if (!password.equals(verifyPassword)){
            return false;
        } else if (password.length() < 8) {
            Toast.makeText(this.getApplicationContext(), "Password must be longer than 8 characters long", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    class RegisterUser extends AsyncTask<HashMap<String,String>, Void, String> {

        private ProgressBar progressBar;

        @Override
        protected String doInBackground(HashMap<String,String>... inputParams) {
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();

            //returning the response
            return requestHandler.sendPostRequest(URLs.URL_REGISTER, inputParams[0]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //displaying the progress bar while user registers on the server
//            progressBar = (ProgressBar) findViewById(R.id.progressBar);
//            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //hiding the progressbar after completion
//            progressBar.setVisibility(View.GONE);

            try {
                //converting response to json object
                JSONObject obj = new JSONObject(s);
                Log.i("Register",s);
                //if no error in response
                if (!obj.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                    //getting the user from the response
                    JSONObject userJson = obj.getJSONObject("user");

                    //creating a new user object
                    User user = new User(
                            userJson.getString("username"),
                            userJson.getString("email"),
                            userJson.getString("gender")
                    );

                    //storing the user in shared preferences
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                    //starting the profile activity
                    finish();
//                    TODO: Make a Profile Activity and start it after registration
//                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //executing the async task

}
