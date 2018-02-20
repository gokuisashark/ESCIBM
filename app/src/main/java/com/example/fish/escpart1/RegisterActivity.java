package com.example.fish.escpart1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    String[] designation_array = {"Sir", "Dr", "Mr", "Ms", "Mrs", "Mdm"};
    String[] genders_array = {"M", "F", "Others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner designationSpinner = (Spinner) findViewById(R.id.designationSpinner);
        Spinner genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        Button saveregistrationdetailsbutton = (Button) findViewById(R.id.registerSaveButton);

        ArrayAdapter<String> designationSpinnerAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, designation_array);
        designationSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        designationSpinner.setAdapter(designationSpinnerAdapter);

        ArrayAdapter<String> genderSpinnerAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, genders_array);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderSpinnerAdapter);

        saveregistrationdetailsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "Details saved", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
