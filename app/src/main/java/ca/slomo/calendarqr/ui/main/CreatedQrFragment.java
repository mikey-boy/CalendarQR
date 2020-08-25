package ca.slomo.calendarqr.ui.main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONObject;

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

    private void setEventFields(View view){
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

    private void setQRBitmap(View view) {
        JSONObject job = new JSONObject();
        EventViewModel eventViewModel = new ViewModelProvider(requireActivity()).get(EventViewModel.class);
        DateFormat formatDateTime = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

        String startDate = formatDateTime.format(eventViewModel.getStartDate().getTime());
        String endDate = formatDateTime.format(eventViewModel.getEndDate().getTime());

        try {
            job.put("name", eventViewModel.getName());
            job.put("location", eventViewModel.getLocation());
            job.put("description", eventViewModel.getDescription());
            job.put("startDate", startDate);
            job.put("endDate", endDate);
        } catch (Exception e) {

        }

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(job.toString(), BarcodeFormat.QR_CODE, 600, 600);
            ImageView imageViewQrCode = (ImageView) view.findViewById(R.id.qrCode);
            imageViewQrCode.setImageBitmap(bitmap);
        } catch(Exception e) {

        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        setEventFields(view);
        setQRBitmap(view);
    }

}
