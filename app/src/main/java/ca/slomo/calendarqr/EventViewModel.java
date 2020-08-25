package ca.slomo.calendarqr;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.Calendar;

public class EventViewModel extends ViewModel {
    private Calendar startDate, endDate;
    private String description, location;

    public final int START_DATE = 0;
    public final int END_DATE = 1;

    private void setDefaultDateTime() {
        // Get the current time and round to the next hour
        startDate = Calendar.getInstance();
        startDate.set(Calendar.MINUTE, 0);
        startDate.add(Calendar.HOUR, 1);

        endDate = (Calendar) startDate.clone();
        endDate.add(Calendar.HOUR, 1);
    }

    public EventViewModel(){
        setDefaultDateTime();
        Log.e("EventViewModal", "New EventViewModal Created!");
    }

    // Check whether the endDate comes prior to startDate
    public boolean fixEndDate() {
        if (startDate.compareTo(endDate) <= 0) { return false; }
        endDate = (Calendar) startDate.clone();      // Move end time to 1 hour past
        endDate.add(Calendar.HOUR, 1);
        return true;
    }

    public Calendar getStartDate() { return startDate; }
    public Calendar getEndDate() { return endDate; }

    public void dateSet(int year, int monthOfYear, int dayOfMonth, int targetDate) {
        Calendar date;
        if (targetDate == START_DATE) { date = startDate; }
        else { date = endDate; }
        date.set(year, monthOfYear, dayOfMonth);
    }

    public void timeSet(int hour, int minute, int targetDate) {
        Calendar date;
        if (targetDate == START_DATE) { date = startDate; }
        else { date = endDate; }
        date.set(Calendar.HOUR_OF_DAY, hour);
        date.set(Calendar.MINUTE, minute);
    }
}
