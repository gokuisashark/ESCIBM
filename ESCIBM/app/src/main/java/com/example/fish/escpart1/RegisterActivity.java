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

import com.microblink.activity.ScanCard;
import com.microblink.recognizers.BaseRecognitionResult;
import com.microblink.recognizers.RecognitionResults;
import com.microblink.recognizers.blinkid.singapore.front.SingaporeIDFrontRecognitionResult;
import com.microblink.recognizers.blinkid.singapore.front.SingaporeIDFrontRecognizerSettings;
import com.microblink.recognizers.settings.RecognitionSettings;
import com.microblink.recognizers.settings.RecognizerSettings;
import com.microblink.results.date.Date;
import com.microblink.view.recognition.ScanResultListener;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements ScanResultListener{
    String LICENSE_KEY = "D3X5YI3C-T7REK3K2-MBBKGCNV-4GABWQJL-NNQF7RXD-B5Q24HFI-7SY2FJY3-JE35FQPP";
    int BLINK_ID_REQUEST_CODE = 23;

    String[] designation_array = {"Sir", "Dr", "Mr", "Ms", "Mrs", "Mdm"};
    String[] genders_array = {"M", "F", "Others"};
    Spinner designationSpinner, genderSpinner;
    EditText firstNameEditText, lastNameEditText, nricEditText, dobEditText,
            homeAddressEditText, postalCodeEditText, contactNumberEditText, emailEditText, passwordEditText, verifyPasswordEditText, nationalityEditText;
    String password, verifyPassword;
    Button saveButton, scanNRICButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        scanNRICButton = findViewById(R.id.scanNRICButtonRegister);
        scanNRICButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // Intent for ScanCard Activity
                Intent intent = new Intent(getApplicationContext(), ScanCard.class);

                // set your licence key
                // obtain your licence key at http://microblink.com/login or
                // contact us at http://help.microblink.com
                intent.putExtra(ScanCard.EXTRAS_LICENSE_KEY, LICENSE_KEY);

                RecognitionSettings settings = new RecognitionSettings();
                // setup array of recognition settings (described in chapter "Recognition
                // settings and results")
                settings.setRecognizerSettingsArray(setupSettingsArray());
                intent.putExtra(ScanCard.EXTRAS_RECOGNITION_SETTINGS, settings);

                // Starting Activity
                startActivityForResult(intent, BLINK_ID_REQUEST_CODE);

            }
        });


        designationSpinner = (Spinner) findViewById(R.id.designationSpinner);
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
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
        nricEditText = findViewById(R.id.nricfinEditView);
        dobEditText = findViewById(R.id.dobEditView);
        homeAddressEditText = findViewById(R.id.homeAddressEditView);
        postalCodeEditText = findViewById(R.id.postalCodeEditView);
        contactNumberEditText = findViewById(R.id.contactNumberEditView);
        emailEditText = findViewById(R.id.emailEditView);
        nationalityEditText = findViewById(R.id.nationalityEditView);

        passwordEditText = findViewById(R.id.passwordEditView);
        verifyPasswordEditText = findViewById(R.id.verifyPasswordEditView);

        saveButton = findViewById(R.id.registerSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputChecker ic = new InputChecker(getApplicationContext());
                HashMap<String,String> inputFields = new HashMap<>();
                ArrayList<String> warnings = new ArrayList<>();

                inputFields.put("postalcode", postalCodeEditText.getText().toString().trim());
                inputFields.put("nric", nricEditText.getText().toString().trim());
                inputFields.put("contact", contactNumberEditText.getText().toString().trim());
                inputFields.put("email", emailEditText.getText().toString().trim());
                inputFields.put("password", passwordEditText.getText().toString().trim());
                inputFields.put("verifyPassword", verifyPasswordEditText.getText().toString().trim());
                warnings = ic.check(inputFields);
                if(!ic.isSuccess()){
                    for (String warning: warnings) {
                        Toast.makeText(getApplicationContext(), warning, Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

                password = passwordEditText.getText().toString().trim();
                verifyPassword = verifyPasswordEditText.getText().toString();
                HashMap<String,String> params = new HashMap<>();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == BLINK_ID_REQUEST_CODE) {
            if (resultCode == ScanCard.RESULT_OK && data != null) {
                // perform processing of the data here

                // for example, obtain parcelable recognition result
                Bundle extras = data.getExtras();
                RecognitionResults result = data.getParcelableExtra(ScanCard.EXTRAS_RECOGNITION_RESULTS);
                onScanningDone(result);
                // get array of recognition results
//                BaseRecognitionResult[] resultArray = result.getRecognitionResults();
                // Each element in resultArray inherits BaseRecognitionResult class and
                // represents the scan result of one of activated recognizers that have
                // been set up. More information about this can be found in
                // "Recognition settings and results" chapter

                // Or, you can pass the intent to another activity
//                data.setComponent(new ComponentName(this, ResultActivity.class));
//                startActivity(data);
            }
        }
    }

    private RecognizerSettings[] setupSettingsArray() {
        SingaporeIDFrontRecognizerSettings sett = new SingaporeIDFrontRecognizerSettings();

        // now add sett to recognizer settings array that is used to configure
        // recognition
        sett.setExtractDateOfBirth(true);
        sett.setExtractSex(true);
        sett.setExtractRace(true);
        sett.setExtractCountryOfBirth(true);
        return new RecognizerSettings[] { sett };
    }

    @Override
    public void onScanningDone(RecognitionResults results) {
        BaseRecognitionResult[] dataArray = results.getRecognitionResults();
        for(BaseRecognitionResult baseResult : dataArray) {
            if(baseResult instanceof SingaporeIDFrontRecognitionResult) {
                SingaporeIDFrontRecognitionResult result = (SingaporeIDFrontRecognitionResult) baseResult;

                // you can use getters of SingaporeIDFrontRecognitionResult class to
                // obtain scanned information
                if(result.isValid() && !result.isEmpty()) {
                    String name = result.getName();
                    String cardNumber = result.getCardNumber();
                    String sex = result.getSex();
                    Date dob = result.getDateOfBirth();
                    String race = result.getRace();
                    String countryOfBirth = result.getCountryOfBirth();

                    firstNameEditText.setText(name.substring(0, name.indexOf(' ')));
                    lastNameEditText.setText(name.substring(name.indexOf(' ') + 1));
                    nricEditText.setText(cardNumber);
                    if (sex.equals("M")){
                        genderSpinner.setSelection(0);
                    } else if (sex.equals("F")) {
                        genderSpinner.setSelection(1);
                    } else {
                        genderSpinner.setSelection(2);
                    }
                    dobEditText.setText("" + dob.getDay() + "-" + dob.getMonth() + "-" + dob.getYear());


                } else {
                    // not all relevant data was scanned, ask user
                    // to try again
                    Toast.makeText(getApplicationContext(),"Scan failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
