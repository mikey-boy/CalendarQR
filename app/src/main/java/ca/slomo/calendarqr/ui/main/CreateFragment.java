package ca.slomo.calendarqr.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.DateFormat;
import java.util.Calendar;

import ca.slomo.calendarqr.EventViewModel;
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

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final MainActivity mainActivity = (MainActivity) getActivity();

        mainActivity.updateDateTimeUI();

        // Set listeners for DatePicker and TimePicker
        view.findViewById(R.id.eventStartDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.createDatePickerDialog(view, true);
            }
        });
        view.findViewById(R.id.eventEndDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.createDatePickerDialog(view, false);
            }
        });
        view.findViewById(R.id.eventStartTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.createTimePickerDialog(view, true);
            }
        });
        view.findViewById(R.id.eventEndTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.createTimePickerDialog(view, false);
            }
        });

        // Set listener for generate and cancel buttons
        view.findViewById(R.id.generateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CreateFragment.this)
                        .navigate(R.id.action_createFragment_to_createdQrFragment);
            }
        });
        view.findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CreateFragment.this)
                        .navigate(R.id.action_createFragment_to_mainFragment);
            }
        });
    }
}
