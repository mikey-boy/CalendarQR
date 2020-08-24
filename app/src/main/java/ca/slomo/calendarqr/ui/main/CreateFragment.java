package ca.slomo.calendarqr.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.DateFormat;

import ca.slomo.calendarqr.MainActivity;
import ca.slomo.calendarqr.R;

public class CreateFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.create_fragment, container, false);
    }

    // Change button text to reflect default date and time
    private void setDefaultDateTime(@NonNull View view) {
        // Use DateFormat class to ... well format the current date
        DateFormat formatDate = DateFormat.getDateInstance(DateFormat.LONG);
        DateFormat formatTime = DateFormat.getTimeInstance(DateFormat.SHORT);
        MainActivity mainActivity = (MainActivity) getActivity();

        // Alter the event buttons accordingly (start time/date)
        Button startDate = view.findViewById(R.id.eventStartDate);
        Button startTime = view.findViewById(R.id.eventStartTime);
        startDate.setText(formatDate.format(mainActivity.startDate.getTime()));
        startTime.setText(formatTime.format(mainActivity.startDate.getTime()));

        // Alter the event buttons accordingly (end time/date)
        Button endDate = view.findViewById(R.id.eventEndDate);
        Button endTime = view.findViewById(R.id.eventEndTime);
        endDate.setText(formatDate.format(mainActivity.endDate.getTime()));
        endTime.setText(formatTime.format(mainActivity.endDate.getTime()));
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setDefaultDateTime(view);

        // Set listeners for DatePicker and TimePicker
        view.findViewById(R.id.eventStartDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).createDatePickerDialog(view, true);
            }
        });
        view.findViewById(R.id.eventEndDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).createDatePickerDialog(view, false);
            }
        });
        view.findViewById(R.id.eventStartTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).createTimePickerDialog(view, true);
            }
        });
        view.findViewById(R.id.eventEndTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).createTimePickerDialog(view, false);
            }
        });
    }
}
