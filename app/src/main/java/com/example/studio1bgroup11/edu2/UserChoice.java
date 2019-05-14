package com.example.studio1bgroup11.edu2;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class UserChoice extends AppCompatActivity {



    private Button tutor;
    private Button centre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice);

        tutor = findViewById(R.id.tutor_button);
        centre = findViewById(R.id.centre_button);

        buttonSetup();

    }


    private void buttonSetup() {

        tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserChoice.this, LoginMainActivity.class);
                intent.putExtra("TutorChoice", "Tutor");
                startActivity(intent);
                                     }
                                 }
        );

        centre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserChoice.this, LoginMainActivity.class);
                intent.putExtra("CentreChoice", "Centre");
                startActivity(intent);
            }
        });
    }



}