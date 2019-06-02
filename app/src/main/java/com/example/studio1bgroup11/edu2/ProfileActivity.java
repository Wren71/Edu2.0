package com.example.studio1bgroup11.edu2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfileActivity extends AppCompatActivity {

    TextView qualification, subjects, name, avail, availHeading, subjectHeading;
    String nameFromSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        qualification = findViewById(R.id.tvQualification);
        subjects = findViewById(R.id.tvRegisteredSubjects);
        name = findViewById(R.id.tvName);
        avail = findViewById(R.id.tvAvailability);
        availHeading = findViewById(R.id.tvHeadingAvail);
        subjectHeading = findViewById(R.id.tvHeadingRegistered);



        nameFromSearch = "Mount Carmel High School";



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
        availHeading.setText("Availability");
        subjectHeading.setText("Subjects");


    } else if (nameFromSearch.equals("Chinedu Alfaro")) {

        qualification.setText("Bachelor of Science UTS");
        subjects.setText("Chemistry and Science");
        name.setText("Chinedu Alfaro");
        avail.setText("Monday, Thursday, Saturday");
        availHeading.setText("Availability");
        subjectHeading.setText("Subjects");



    } else if (nameFromSearch.equals("Gottem City")) {
        qualification.setText("Bachelor of Civil Engineering");
        subjects.setText("Physics and Maths");
        name.setText("Gottem City");
        avail.setText("Monday, Saturday, Sunday");
        availHeading.setText("Availability");
        subjectHeading.setText("Subjects");


    } else if (nameFromSearch.equals("Fotis Espino")) {
        qualification.setText("Bachelor of Science UTS");
        subjects.setText("Chemistry and Science");
        name.setText("Fotis Espoino");
        avail.setText("Monday, Thursday, Saturday");
        availHeading.setText("Availability");
        subjectHeading.setText("Subjects");


    } else if (nameFromSearch.equals("Kalliope Valeriana")) {
        qualification.setText("Bachelor of Software Engineering UTS");
        subjects.setText("Database programming and Maths");
        name.setText("Kalliope Valeriana");
        avail.setText("Monday, Thursday, Sunday");
        availHeading.setText("Availability");
        subjectHeading.setText("Subjects");


    } else if (nameFromSearch.equals("Jennie")) {
        qualification.setText("Masters of IT UTS");
        subjects.setText("Maths and Music");
        name.setText("Jennie");
        avail.setText("Sunday, Tuesday, Wednesday");
        availHeading.setText("Availability");
        subjectHeading.setText("Subjects");


    } else if (nameFromSearch.equals("Wren")) {
        qualification.setText("Bachelor of Engineering Software UTS");
        subjects.setText("Maths and Science oof");
        name.setText("Wren");
        avail.setText("Saturday, Sunday");
        availHeading.setText("Availability");
        subjectHeading.setText("Subjects");


    } else if (nameFromSearch.equals("Lachlan")) {
        qualification.setText("Bachelor of Engineering Software UTS");
        subjects.setText("History and Firebase");
        name.setText("Lachlan");
        avail.setText("Monday, Tuesday");
        availHeading.setText("Availability");
        subjectHeading.setText("Subjects");


    } else if (nameFromSearch.equals("Jerry")) {
        qualification.setText("Bachelor of Business UTS");
        subjects.setText("History and Science");
        name.setText("Jerry");
        avail.setText("Thursday, Friday");
        availHeading.setText("Availability");
        subjectHeading.setText("Subjects");


    }

    else if (nameFromSearch.equals("Lisa")) {
        qualification.setText("Bachelor of Science UTS");
        subjects.setText("Music and Science");
        name.setText("Lisa");
        avail.setText("Thursday, Friday");
        availHeading.setText("Availability");
        subjectHeading.setText("Subjects");


    }


    else if (nameFromSearch.equals("Abcde")) {
        qualification.setText("Bachelor of Business UTS");
        subjects.setText("History and Science");
        name.setText("Abcde");
        avail.setText("Thursday, Friday");
        availHeading.setText("Availability");
        subjectHeading.setText("Subjects");


    }

}


    }



}
