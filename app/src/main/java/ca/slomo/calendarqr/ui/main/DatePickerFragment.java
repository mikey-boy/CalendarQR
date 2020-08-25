package ca.slomo.calendarqr.ui.main;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.text.DateFormat;
import java.util.Calendar;

import ca.slomo.calendarqr.EventViewModel;
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
        EventViewModel eventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        // Use the currently set datetime from EventViewModel
        if (startDate) { cal = eventViewModel.getStartDate(); }
        else           { cal = eventViewModel.getEndDate(); }
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
        Button button;
        Calendar cal;
        MainActivity mainActivity = (MainActivity) getActivity();
        EventViewModel eventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        if (startDate) {
            cal = eventViewModel.getStartDate();
            eventViewModel.dateSet(year, monthOfYear, dayOfMonth, eventViewModel.START_DATE);
            button = mainActivity.findViewById(R.id.eventStartDate);
        } else {
            cal = eventViewModel.getEndDate();
            eventViewModel.dateSet(year, monthOfYear, dayOfMonth, eventViewModel.END_DATE);
            button = mainActivity.findViewById(R.id.eventEndDate);
        }

        DateFormat formatDate = DateFormat.getDateInstance(DateFormat.LONG);
        button.setText(formatDate.format(cal.getTime()));       // change UI buttons to reflect updated date
        if (eventViewModel.fixEndDate()) { mainActivity.updateDateTimeUI(); }    // adjust endDate if necessary
    }
}
