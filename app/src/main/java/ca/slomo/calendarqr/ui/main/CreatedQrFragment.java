package ca.slomo.calendarqr.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.text.DateFormat;

import ca.slomo.calendarqr.EventViewModel;
import ca.slomo.calendarqr.R;

public class CreatedQrFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.created_qr_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        DateFormat formatDateTime = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
        EventViewModel eventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);

        TextView name = view.findViewById(R.id.createdEventName);
        TextView location = view.findViewById(R.id.createdLocation);
        TextView description = view.findViewById(R.id.createdDescription);
        TextView startDate = view.findViewById(R.id.createdStartDate);
        TextView endDate = view.findViewById(R.id.createdEndDate);

        name.append(eventViewModel.getName());
        location.append(eventViewModel.getLocation());
        description.append(eventViewModel.getDescription());

        String formattedStart = formatDateTime.format(eventViewModel.getStartDate().getTime());
        String formattedEnd = formatDateTime.format(eventViewModel.getEndDate().getTime());

        startDate.append(formattedStart);
        endDate.append(formattedEnd);
    }

}
