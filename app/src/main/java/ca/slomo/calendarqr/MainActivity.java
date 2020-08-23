package ca.slomo.calendarqr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.Calendar;

import ca.slomo.calendarqr.ui.main.DatePickerFragment;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void startDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "startDatePicker");
    }
    public void endDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "endDatePicker");
    }

    // handle the date selected
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        // store the values selected into a Calendar instance
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String tag = (String) view.getTag();
        Button button;
        if (tag.equals("startDatePicker")) {
            button = view.findViewById(R.id.eventStartDate);
        } else {
            button = view.findViewById(R.id.eventEndDate);
        }

        DateFormat formatDate = DateFormat.getDateInstance(DateFormat.LONG);
        button.setText(formatDate.format(cal.getTime()));
    }
}
