package com.example.studio1bgroup11.edu2;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class BookingActivity extends AppCompatActivity implements DatePickerDialog.DatePickerDialogListener, android.app.TimePickerDialog.OnTimeSetListener {

    Button bookingButton;
    Spinner tutorSpinner;
    Spinner subjectSpinner;
    TextView datePicker;
    TextView timePicker;
    Spinner durationSpinner;

    List<String> tutorsList;
    List<String> subjectList;
    List<String> durationList;

    Calendar currentBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        bookingButton = findViewById(R.id.bookingButton);
        bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Booking Created", Toast.LENGTH_LONG).show();
            }
        });

        //setup spinners
        tutorSpinner = findViewById(R.id.tutorSpinner);
        subjectSpinner = findViewById(R.id.subjectSpinner);
        durationSpinner = findViewById(R.id.bookingDuration);
        getLists();
        createSpinnerAdapters();

        datePicker = findViewById(R.id.bookingDate);
        setupDatePicker();

        timePicker = findViewById(R.id.bookingTime);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerDialog();
                timePicker.show(getSupportFragmentManager(), "Time Picker");
            }
        });

        //durationText = findViewById(R.id.bookingDuration);

        currentBooking = Calendar.getInstance();
    }

    private void getLists(){
        //temp for now - fill with static data
        tutorsList = new ArrayList<String>(Arrays.asList("Choose Tutor", "Jimmy", "Swarley", "Greg", "Molly", "Brock", "Oak", "Midoriya", "Hilter"));
        subjectList = new ArrayList<String>(Arrays.asList("Choose Subject", "Maths", "English", "Science", "German Revolution"));
        durationList = new ArrayList<String>(Arrays.asList("Choose Duration", "0.5", "1.0", "1.5", "2.0", "2.5", "3.0", "3.5", "4.0"));
    }

    private void createSpinnerAdapters(){
        //setup tutors list in spinner
        ArrayAdapter<String> tutorAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tutorsList);
        tutorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tutorSpinner.setAdapter(tutorAdapter);

        //setup subject list in spinner
        ArrayAdapter<String> subjectAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subjectList);
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(subjectAdapter);

        //setup duration list in spinner
        ArrayAdapter<String> durationAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, durationList);
        durationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durationSpinner.setAdapter(durationAdapter);
    }

    @Override
    public void returnDate(Calendar date){
        String dateString = date.get(Calendar.DAY_OF_MONTH) + "/"
                + date.get(Calendar.MONTH) + "/"
                + date.get(Calendar.YEAR);
        datePicker.setText(dateString);

        currentBooking = date;
    }

    private void setupDatePicker(){
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog();
                dialog.show(getSupportFragmentManager(), "DatePicker Dialog");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        currentBooking.set(Calendar.HOUR_OF_DAY, hourOfDay);
        currentBooking.set(Calendar.MINUTE, minute);

        timePicker.setText(hourOfDay + ":" + String.format("%02d", minute));
    }
}
