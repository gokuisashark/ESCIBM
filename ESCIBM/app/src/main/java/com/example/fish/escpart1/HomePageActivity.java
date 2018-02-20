package com.example.fish.escpart1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity {

    Button viewaccountbutton;
    Button maketransactionbutton;
    Button opencamerabutton;
    Button editdatabutton;
    Button logoutbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        viewaccountbutton = (Button) findViewById(R.id.viewaccountbutton);
        maketransactionbutton = (Button) findViewById(R.id.maketransactionbutton);
        opencamerabutton = (Button) findViewById(R.id.opencamerabutton);
        editdatabutton = (Button) findViewById(R.id.editdatabutton);
        logoutbutton = (Button) findViewById(R.id.loginbutton);

        viewaccountbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent viewAccountButtonIntent = new Intent(context, ViewAccountSummaryActivity.class);
                startActivity(viewAccountButtonIntent);
            }
        });

        maketransactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent makeTransactionButtonIntent = new Intent(context, MakeTransactionActivity.class);
                startActivity(makeTransactionButtonIntent);
            }
        });

        opencamerabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent openCameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(openCameraIntent);
            }
        });


        editdatabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent editDataButtonIntent = new Intent(context, EditDataActivity.class);
                startActivity(editDataButtonIntent);
            }
        });

//        logoutbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Context context = v.getContext();
//                Intent logoutButtonIntent = new Intent(context, StartUpActivity.class);
//                startActivity(logoutButtonIntent);
//            }
//        });


    }
}
