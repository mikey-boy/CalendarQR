package ca.slomo.calendarqr.ui.main;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

import ca.slomo.calendarqr.R;
import ca.slomo.calendarqr.MainActivity;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private boolean startDate;

    public TimePickerFragment(boolean startDate){
        this.startDate = startDate;
    }

    @Override
    public TimePickerDialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal;
        MainActivity mainActivity = (MainActivity) getActivity();

        // Use the currently set datetime from MainActivity
        if (startDate) { cal = mainActivity.startDate; }
        else           { cal = mainActivity.endDate; }
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        // Create a new instance of DatePickerDialog and return it
        TimePickerDialog.OnTimeSetListener listener = this;
        return new TimePickerDialog(getActivity(), listener, hour, minute, android.text.format.DateFormat.is24HourFormat(getActivity()));
    }

    // handle the date selected
    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        MainActivity mainActivity = (MainActivity) getActivity();
        Calendar cal;
        Button button;

        if (startDate) {
            cal = mainActivity.startDate;
            button = mainActivity.findViewById(R.id.eventStartTime);
        } else {
            cal = mainActivity.endDate;
            button = mainActivity.findViewById(R.id.eventEndTime);
        }

        DateFormat formatDate = DateFormat.getTimeInstance(DateFormat.SHORT);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        button.setText(formatDate.format(cal.getTime()));
    }
}
