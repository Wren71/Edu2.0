package com.example.studio1bgroup11.edu2;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    String currentTutor;
    String currentSubject;
    String currentDuration;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        bookingButton = findViewById(R.id.bookingButton);
        bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(datePicker.getText().equals("Choose Date") || timePicker.getText().equals("Choose Time") ||
                        tutorSpinner.getSelectedItemPosition() == 0 ||
                        subjectSpinner.getSelectedItemPosition() == 0 ||
                        durationSpinner.getSelectedItemPosition() == 0){
                    Toast.makeText(v.getContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(v.getContext(), "Booking Created with : " + currentTutor + ", " + currentSubject + ", " + currentDuration + "h", Toast.LENGTH_LONG).show();

                    Map<String, Object> dbBookings = new HashMap<>();

                    dbBookings.put("tutor", currentTutor);
                    dbBookings.put("subject", currentSubject);
                    dbBookings.put("duration", currentDuration);

                    Date date = currentBooking.getTime();
                    Timestamp dbTime = new Timestamp(date);
                    dbBookings.put("date", dbTime);

                    db.collection("bookings").add(dbBookings);



                    //finish();
                }
            }
        });

        //setup spinners
        tutorSpinner = findViewById(R.id.tutorSpinner);
        subjectSpinner = findViewById(R.id.subjectSpinner);
        durationSpinner = findViewById(R.id.bookingDuration);
        getLists();
        createSpinnerAdapters();
        spinnerAdapterListener();

        //setup date picker
        datePicker = findViewById(R.id.bookingDate);
        setupDatePicker();

        //setup time picker
        timePicker = findViewById(R.id.bookingTime);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerDialog();
                timePicker.show(getSupportFragmentManager(), "Time Picker");
            }
        });

        currentBooking = Calendar.getInstance();
    }

    private void getLists(){
        //initialise lists
        tutorsList = new ArrayList<>();
        subjectList = new ArrayList<>();
        durationList = new ArrayList<String>(Arrays.asList("0.5", "1.0", "1.5", "2.0", "2.5", "3.0", "3.5", "4.0"));

        //get displayname from users in database and add to tutorsList
        CollectionReference docRef = db.collection("users");
        docRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot docSnap : queryDocumentSnapshots.getDocuments()){
                    //Log.d("Users", docSnap.get("displayName").toString());
                    tutorsList.add(docSnap.get("displayName").toString());
                }
            }
        });

        //temp for now - fill with static data
        //tutorsList = new ArrayList<String>(Arrays.asList("Jimmy", "Swarley", "Greg", "Molly", "Brock", "Oak", "Midoriya", "Hilter"));
        subjectList = new ArrayList<String>(Arrays.asList("Maths", "English", "Science", "History"));

        //add hints to the front of list
        tutorsList.add(0, "Choose Tutor");
        subjectList.add(0, "Choose Subject");
        durationList.add(0, "Choose Duration");
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

    private void spinnerAdapterListener(){
        tutorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentTutor = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentSubject = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        durationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentDuration = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
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
