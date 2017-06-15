package com.example.timepicker;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Calendar;
import java.util.Date;

/**
 *  @author xuchen
 *  @time 2017/6/15  15:40
 *  @describe
 */

public class TimePickerDialogFragment extends DialogFragment implements TimePickerFragment.DateChangedListener {

    public static final String TAG = "timepickerdialogfragment";
    private static TimePickerListener mListener;
    private Date mMinDate;
    private Date mMaxDate;
    private Calendar mCalendar;
    private int mYear;
    private int mMonth;
    private int mDay;

    public static TimePickerDialogFragment getInstance(TimePickerListener listener, Date minDate, Date maxDate, int year, int month, int day) {

        mListener = listener;
        TimePickerDialogFragment dialogFragment = new TimePickerDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("minDate", minDate);
        bundle.putSerializable("maxDate", maxDate);
        bundle.putInt("year", year);
        bundle.putInt("month", month);
        bundle.putInt("day", day);
        dialogFragment.setArguments(bundle);

        return dialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        getArg();
        mCalendar = Calendar.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dia_timepicker, container);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Button btnOk = (Button) view.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        TimePickerFragment timePickerFragment = TimePickerFragment.getInstance(mMinDate, mMaxDate, mYear, mMonth - 1, mDay);
        timePickerFragment.setTargetFragment(TimePickerDialogFragment.this, 100);
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().add(R.id.rl_container, timePickerFragment).commit();

        btnOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onGetDate(new Date(mCalendar.getTimeInMillis()));
                }

                dismiss();
            }
        });

        btnCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onCancel();
                }

                dismiss();
            }
        });
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }

        super.onDestroyView();
    }

    private void getArg() {
        Bundle args = getArguments();
        mMinDate = (Date) args.getSerializable("minDate");
        mMaxDate = (Date) args.getSerializable("maxDate");
        mYear = args.getInt("year");
        mMonth = args.getInt("month");
        mDay = args.getInt("day");
    }


    @Override
    public void onDateChanged(int year, int month, int day) {
        mCalendar.set(year, month, day);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

        if(mListener!=null) {
            mListener.onCancel();
        }

    }

}
