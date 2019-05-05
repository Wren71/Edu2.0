package com.example.studio1bgroup11.edu2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.Calendar;

public class DatePickerDialog extends AppCompatDialogFragment {

    private CalendarView calendarView;
    private DatePickerDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //initial setup
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_date_picker, null);

        //initialize
        calendarView = view.findViewById(R.id.dialogCalendar);
        try{
            //set current day
            Calendar currentDate = Calendar.getInstance();
            calendarView.setDate(currentDate);
        }
        catch (Exception e){
            Log.d("exception", e.getMessage());
        }

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                //return date clicked and close dialog
                listener.returnDate(eventDay.getCalendar());
                getDialog().dismiss();
            }
        });

        //dialog attributes and buttons
        builder.setView(view).setTitle("Choose Date");
        /*builder.setView(view)
                .setTitle("Login")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });*/

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DatePickerDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "Must Implement DatePickerDialogListener");
        }
    }

    public interface DatePickerDialogListener{
        void returnDate(Calendar date);
    }
}
