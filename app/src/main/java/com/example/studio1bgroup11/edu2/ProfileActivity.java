package com.example.studio1bgroup11.edu2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfileActivity extends AppCompatActivity {

    TextView qualification, subjects, name, avail;
    String nameFromSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        qualification = findViewById(R.id.tvQualification);
        subjects = findViewById(R.id.tvRegisteredSubjects);
        name = findViewById(R.id.tvName);
        avail = findViewById(R.id.tvAvailability);

        nameFromSearch = "Nick Iacono";



        Intent intent = getIntent();
        if (intent != null) {
           nameFromSearch = intent.getStringExtra("Name");
            System.out.println("ID IN PROFILE: " + nameFromSearch);
        }

if (nameFromSearch != null ) {
    if (nameFromSearch.equals("Steve Lim")) {
        qualification.setText("Bachelor of IT UTS");
        subjects.setText("Maths and Java programming");
        name.setText("Steve Lim");
        avail.setText("Sunday, Wednesday, Friday");

    } else if (nameFromSearch.equals("Chinedu Alfaro")) {

        qualification.setText("Bachelor of Science UTS");
        subjects.setText("Chemistry and Science");
        name.setText("Chinedu Alfaro");
        avail.setText("Monday, Thursday, Saturday");


    } else if (nameFromSearch.equals("Gottem City")) {
        qualification.setText("Bachelor of Civil Engineering");
        subjects.setText("Physics and Maths");
        name.setText("Gottem City");
        avail.setText("Monday, Saturday, Sunday");


    } else if (nameFromSearch.equals("Fotis Espino")) {
        qualification.setText("Bachelor of Science UTS");
        subjects.setText("Chemistry and Science");
        name.setText("Fotis Espoino");
        avail.setText("Monday, Thursday, Saturday");


    } else if (nameFromSearch.equals("Kalliope Valeriana")) {
        qualification.setText("Bachelor of Software Engineering UTS");
        subjects.setText("Database programming and Maths");
        name.setText("Kalliope Valeriana");
        avail.setText("Monday, Thursday, Sunday");


    } else if (nameFromSearch.equals("Oof oiz")) {
        qualification.setText("Bachelor of Science UTS");
        subjects.setText("App programming and Real Time Operating Systems");
        name.setText("Ooof oiz");
        avail.setText("Sunday, Monday, Tuesday");


    }


}


    }



}
