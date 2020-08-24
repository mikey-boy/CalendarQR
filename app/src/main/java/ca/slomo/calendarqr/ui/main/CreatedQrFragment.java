package ca.slomo.calendarqr.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;

import ca.slomo.calendarqr.MainActivity;
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
        MainActivity mainActivity = (MainActivity) getActivity();
        DateFormat formatDateTime = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);

        TextView startDate = view.findViewById(R.id.createdStartDate);
        TextView endDate   = view.findViewById(R.id.createdEndDate);

        startDate.append(formatDateTime.format(mainActivity.startDate.getTime()));
        endDate.append(formatDateTime.format(mainActivity.endDate.getTime()));
    }

}
