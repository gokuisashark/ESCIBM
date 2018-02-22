package com.example.fish.escpart1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
            homeAddressEditText, postalCodeEditText, contactNumberEditText, emailEditText, accountNumberEditText;
    //TODO: add passwordEditText
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        designationSpinner = (Spinner) findViewById(R.id.designationSpinner);
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        Button saveregistrationdetailsbutton = (Button) findViewById(R.id.registerSaveButton);

        ArrayAdapter<String> designationSpinnerAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, designation_array);
        designationSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        designationSpinner.setAdapter(designationSpinnerAdapter);

        ArrayAdapter<String> genderSpinnerAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, genders_array);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderSpinnerAdapter);

        firstNameEditText = findViewById(R.id.firstNameEditView);
        lastNameEditText = findViewById(R.id.lastNameEditView);
        emailEditText = findViewById(R.id.emailEditView);
        password = "password"; //placeholder



        saveregistrationdetailsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("designation", designationSpinner.getSelectedItem().toString());
                params.put("gender",genderSpinner.getSelectedItem().toString());
                params.put("name", firstNameEditText.getText().toString() + " " + lastNameEditText.getText().toString());
                params.put("email", emailEditText.getText().toString());
                params.put("password", password);
                RegisterUser ru = new RegisterUser();
                ru.execute(params);
                Toast.makeText(RegisterActivity.this, "Details saved", Toast.LENGTH_SHORT).show();
            }
        });


    }
    class RegisterUser extends AsyncTask<HashMap<String,String>, Void, String> {

        private ProgressBar progressBar;

        @Override
        protected String doInBackground(HashMap<String,String>... inputParams) {
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();

            //creating request parameters
            HashMap<String, String> params = new HashMap<>();
            params.put("username",inputParams[0].get("name"));
            params.put("email", inputParams[0].get("password"));
            params.put("password", inputParams[0].get("password"));
            params.put("gender", inputParams[0].get("gender"));

            //returing the response
            return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
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
