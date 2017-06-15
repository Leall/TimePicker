package com.example.timepicker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

import java.util.Date;
/**
 *  @author xuchen
 *  @time 2017/6/15  15:41
 *  @describe
 */

public class TimePickerFragment extends Fragment {

    public static final String TAG = "timepickerfragment";

    interface DateChangedListener {
        void onDateChanged(int year, int month, int day);
    }

    private DateChangedListener mCallback;
    private DatePicker mDatePicker;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mCallback = (DateChangedListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("fragment must implement DateChangedListener interface");
        }
    }

    public static TimePickerFragment getInstance(Date minDate, Date maxDate, int year, int month, int day) {

        TimePickerFragment fragment = new TimePickerFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("year", year);
        bundle.putInt("month", month);
        bundle.putInt("day", day);
        bundle.putSerializable("minDate", minDate);
        bundle.putSerializable("maxDate", maxDate);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int year = getArguments().getInt("year");
        int month = getArguments().getInt("month");
        int day = getArguments().getInt("day");
        Date minDate = (Date) getArguments().getSerializable("minDate");
        Date maxDate = (Date) getArguments().getSerializable("maxDate");

        LayoutInflater localInflater = inflater.cloneInContext(new ContextThemeWrapper(getActivity(), android.R.style.Theme_Holo_Light));
        View view = localInflater.inflate(R.layout.fragment_timepicker, container, false);

        mDatePicker = (DatePicker) view.findViewById(R.id.datePicker);
        mDatePicker.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);

        if (year < 1900 || month < 0 || day < 0) {
            year = 2000;
            month = 1;
            day = 1;
            Log.e(TAG, "Time not between Mon Jan 01 00:00:00 GMT+08:00 1900 and Fri Dec 31 00:00:00 GMT+08:00 2100");
        }

        mDatePicker.init(year, month, day, new OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                mCallback.onDateChanged(year, monthOfYear, dayOfMonth);
            }
        });

        if (minDate != null)
            mDatePicker.setMinDate(minDate.getTime());

        if (maxDate != null)
            mDatePicker.setMaxDate(maxDate.getTime());

        return view;
    }
}
