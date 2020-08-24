package ca.slomo.calendarqr.ui.main;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

import ca.slomo.calendarqr.MainActivity;
import ca.slomo.calendarqr.R;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private boolean startDate;

    public DatePickerFragment(boolean startDate){
        this.startDate = startDate;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal;
        MainActivity mainActivity = (MainActivity) getActivity();

        // Use the currently set datetime from MainActivity
        if (startDate) { cal = mainActivity.startDate; }
        else           { cal = mainActivity.endDate; }
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog.OnDateSetListener listener = this;
        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }

    // handle the date selected
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        MainActivity mainActivity = (MainActivity) getActivity();
        Button button;
        Calendar cal;

        if (startDate) {
            cal = mainActivity.startDate;
            button = mainActivity.findViewById(R.id.eventStartDate);
        } else {
            cal = mainActivity.endDate;
            button = mainActivity.findViewById(R.id.eventEndDate);
        }

        DateFormat formatDate = DateFormat.getDateInstance(DateFormat.LONG);
        cal.set(year, monthOfYear, dayOfMonth);                 // store date in MainActivity
        button.setText(formatDate.format(cal.getTime()));       // change UI buttons to reflect updated date
        mainActivity.createTimePickerDialog(view, startDate);   // pull up a TimePicker for the user
    }
}
