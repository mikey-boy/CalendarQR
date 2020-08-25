package ca.slomo.calendarqr.ui.main;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.text.DateFormat;
import java.util.Calendar;

import ca.slomo.calendarqr.EventViewModel;
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
        EventViewModel eventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        // Use the currently set datetime from MainActivity
        if (startDate) { cal = eventViewModel.getStartDate(); }
        else           { cal = eventViewModel.getEndDate(); }
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        // Create a new instance of DatePickerDialog and return it
        TimePickerDialog.OnTimeSetListener listener = this;
        return new TimePickerDialog(getActivity(), listener, hour, minute, android.text.format.DateFormat.is24HourFormat(getActivity()));
    }

    // handle the date selected
    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        Calendar cal;
        Button button;
        MainActivity mainActivity = (MainActivity) getActivity();
        EventViewModel eventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        if (startDate) {
            cal = eventViewModel.getStartDate();
            eventViewModel.timeSet(hour, minute, eventViewModel.START_DATE);
            button = mainActivity.findViewById(R.id.eventStartTime);
        } else {
            cal = eventViewModel.getEndDate();
            eventViewModel.timeSet(hour, minute, eventViewModel.END_DATE);
            button = mainActivity.findViewById(R.id.eventEndTime);
        }

        DateFormat formatDate = DateFormat.getTimeInstance(DateFormat.SHORT);
        button.setText(formatDate.format(cal.getTime()));   // adjust UI button
        if (eventViewModel.fixEndDate()) { mainActivity.updateDateTimeUI(); } // adjust endDate if necessary
    }
}
