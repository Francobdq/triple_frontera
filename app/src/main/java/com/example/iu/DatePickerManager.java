package com.example.iu;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class DatePickerManager extends AppCompatActivity {

    public void addListener(EditText et, AppCompatActivity act){
        et.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePickerDialog(et, act);
            }
        });

    }

    public void showDatePickerDialog(EditText et, AppCompatActivity act) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "/" + (month+1) + "/" + year;
                et.setText(selectedDate);
            }
        });

        newFragment.show(act.getSupportFragmentManager(), "datePicker");
    }
}
