package ca.slomo.calendarqr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.util.Calendar;

import ca.slomo.calendarqr.ui.main.DatePickerFragment;
import ca.slomo.calendarqr.ui.main.TimePickerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    // Check whether the endDate comes prior to startDate
    public void updateDateTimeUI() {
        // Use DateFormat class to ... well format the current date
        DateFormat formatDate = DateFormat.getDateInstance(DateFormat.LONG);
        DateFormat formatTime = DateFormat.getTimeInstance(DateFormat.SHORT);
        final EventViewModel eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        Calendar startDate = eventViewModel.getStartDate();
        Calendar endDate = eventViewModel.getEndDate();

        // Alter the event buttons accordingly (start time/date)
        Button startDateButton = this.findViewById(R.id.eventStartDate);
        Button startTimeButton = this.findViewById(R.id.eventStartTime);
        startDateButton.setText(formatDate.format(startDate.getTime()));
        startTimeButton.setText(formatTime.format(startDate.getTime()));

        // Alter the event buttons accordingly (end time/date)
        Button endDateButton = this.findViewById(R.id.eventEndDate);
        Button endTimeButton = this.findViewById(R.id.eventEndTime);
        endDateButton.setText(formatDate.format(endDate.getTime()));
        endTimeButton.setText(formatTime.format(endDate.getTime()));
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
