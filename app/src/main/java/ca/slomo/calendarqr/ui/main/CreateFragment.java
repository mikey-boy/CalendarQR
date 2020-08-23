package ca.slomo.calendarqr.ui.main;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import ca.slomo.calendarqr.MainActivity;
import ca.slomo.calendarqr.R;


///**
// * A simple {@link Fragment} subclass.
// * Use the {@link CreateFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class CreateFragment extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public CreateFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment CreateFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static CreateFragment newInstance(String param1, String param2) {
//        CreateFragment fragment = new CreateFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.create_fragment, container, false);
    }

    private void setDefaultDateTime(@NonNull View view) {
        // Use DateFormat class to ... well format the current date
        DateFormat formatDate = DateFormat.getDateInstance(DateFormat.LONG);
        DateFormat formatTime = DateFormat.getTimeInstance(DateFormat.SHORT);

        // Get the current time and round to the next hour
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, 0);
        cal.add(Calendar.HOUR, 1);
        Date firstDefaultTime = cal.getTime();
        cal.add(Calendar.HOUR, 1);
        Date secondDefaultTime = cal.getTime();

        // Alter the event buttons accordingly (start time/date)
        Button startDate = view.findViewById(R.id.eventStartDate);
        Button startTime = view.findViewById(R.id.eventStartTime);
        startDate.setText(formatDate.format(firstDefaultTime));
        startTime.setText(formatTime.format(firstDefaultTime));

        // Alter the event buttons accordingly (end time/date)
        Button endDate = view.findViewById(R.id.eventEndDate);
        Button endTime = view.findViewById(R.id.eventEndTime);
        endDate.setText(formatDate.format(secondDefaultTime));
        endTime.setText(formatTime.format(secondDefaultTime));
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setDefaultDateTime(view);

        view.findViewById(R.id.eventStartDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).startDatePickerDialog(view);
            }
        });

        view.findViewById(R.id.eventEndDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).endDatePickerDialog(view);
            }
        });
    }
}
