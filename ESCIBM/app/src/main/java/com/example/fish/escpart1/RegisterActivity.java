package com.example.fish.escpart1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    String[] designation_array = {"Sir", "Dr", "Mr", "Ms", "Mrs", "Mdm"};
    String[] genders_array = {"M", "F", "Others"};
    Spinner designationSpinner, genderSpinner;
    EditText firstNameEditText, lastNameEditText, nricEditText, dobEditText,
            homeAddressEditText, postalCodeEditText, contactNumberEditText, emailEditText, passwordEditText, verifyPasswordEditText, nationalityEditText;
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
        nationalityEditText = findViewById(R.id.nationalityEditView);

        genderSpinner.setAdapter(genderSpinnerAdapter);
        passwordEditText = findViewById(R.id.passwordEditView);
        verifyPasswordEditText = findViewById(R.id.verifyPasswordEditView);

        saveregistrationdetailsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> params = new HashMap<>();
                password = passwordEditText.getText().toString().trim();
                verifyPassword = verifyPasswordEditText.getText().toString();
                if (!verifyPassword(password, verifyPassword)){
                    return;
                }
                params.put("username", emailEditText.getText().toString().trim());
                params.put("firstname", firstNameEditText.getText().toString().trim());
                params.put("lastname", lastNameEditText.getText().toString().trim());
                params.put("NRIC", nricEditText.getText().toString().trim());
                params.put("DOB", dobEditText.getText().toString().trim());
                params.put("Address", homeAddressEditText.getText().toString().trim());
                params.put("postalcode", postalCodeEditText.getText().toString().trim());
                params.put("contact", contactNumberEditText.getText().toString().trim());
                params.put("email", emailEditText.getText().toString().trim());
                params.put("gender",genderSpinner.getSelectedItem().toString());
                params.put("password", password);
                params.put("Designation", designationSpinner.getSelectedItem().toString());
                params.put("Nationality", nationalityEditText.getText().toString().trim());
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                intent.putExtra("params", params);
                startActivity(intent);

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
}
