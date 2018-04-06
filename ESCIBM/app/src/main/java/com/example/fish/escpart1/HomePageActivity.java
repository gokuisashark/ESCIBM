package com.example.fish.escpart1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class HomePageActivity extends AppCompatActivity {

    GridLayout mainGrid;
    JSONObject payload, user;
    TextView welcome;
    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        welcome = findViewById(R.id.headertext);

        jsonString = (String) getIntent().getSerializableExtra("jsonString");
        try {
            payload = new JSONObject(jsonString);
            user = payload.getJSONObject("user");
            welcome.setText("Welcome, " + user.getString("firstname") + " " + user.getString("lastname"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Set Event
        setSingleEvent (mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child items of mainGrid
        for (int i = 0; i < mainGrid.getChildCount(); i++)
        {
            //all child items are CardView, so cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int index = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //for the below codes, change all placeholderactivity to corresponding activity
                    if (index == 0) {
//                        Toast.makeText(HomePageActivity.this, "Personal profile selected",
//                                Toast.LENGTH_SHORT).show();
                        Context context = view.getContext();
                        Intent personalProfileIntent = new Intent(context, ProfileActivity.class);
                        personalProfileIntent.putExtra("jsonString", jsonString);
                        startActivity(personalProfileIntent);
                    } else if (index == 1) {
//                        Toast.makeText(HomePageActivity.this, "Edit personal data selected",
//                                Toast.LENGTH_SHORT).show();
                        Context context = view.getContext();
                        Intent editDataIntent = new Intent(context, PlaceholderActivity.class);
                        startActivity(editDataIntent);
                    } else if (index == 2) {
//                        Toast.makeText(HomePageActivity.this, "Transactions selected",
//                                Toast.LENGTH_SHORT).show();
                        Context context = view.getContext();
                        Intent transactionsIntent = new Intent(context, PlaceholderActivity.class);
                        startActivity(transactionsIntent);
                    } else if (index == 3) {
//                        Toast.makeText(HomePageActivity.this, "Camera selected",
//                                Toast.LENGTH_SHORT).show();
                        Context context = view.getContext();
                        Intent cameraIntent = new Intent(context, PlaceholderActivity.class);
                        startActivity(cameraIntent);
                    } else if (index == 4) {
//                        Toast.makeText(HomePageActivity.this, "Settings selected",
//                                Toast.LENGTH_SHORT).show();
                        Context context = view.getContext();
                        Intent settingsIntent = new Intent(context, PlaceholderActivity.class);
                        startActivity(settingsIntent);
                    } else if (index == 5) {
//                        Toast.makeText(HomePageActivity.this, "Logout selected",
//                                Toast.LENGTH_SHORT).show();
                        //include code to logout of the app successfully
                    }
                }
            });
        }
    }
}
