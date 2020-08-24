package ca.slomo.calendarqr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import ca.slomo.calendarqr.ui.main.DatePickerFragment;
import ca.slomo.calendarqr.ui.main.TimePickerFragment;

public class MainActivity extends AppCompatActivity {

    public Calendar startDate;
    public Calendar endDate;

    private void setDefaultDateTime() {
        // Get the current time and round to the next hour
        startDate = Calendar.getInstance();
        startDate.set(Calendar.MINUTE, 0);
        startDate.add(Calendar.HOUR, 1);

        endDate = (Calendar) startDate.clone();
        endDate.add(Calendar.HOUR, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        setDefaultDateTime();
    }

    public void createDatePickerDialog(View v, boolean startDate) {
        DatePickerFragment newFragment;
        if (startDate) { newFragment = new DatePickerFragment(true); }
        else           { newFragment = new DatePickerFragment(false); }

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void createTimePickerDialog(View v, boolean startDate) {
        TimePickerFragment newFragment;
        if (startDate) { newFragment = new TimePickerFragment(true); }
        else           { newFragment = new TimePickerFragment(false); }

        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
