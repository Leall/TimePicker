package com.example.timepicker;

import android.support.v4.app.FragmentManager;

import java.util.Date;

/**
 *  @author xuchen
 *  @time 2017/6/15  15:40
 *  @describe
 */

public class TimePicker {

    public static class Builder {
        private FragmentManager fm;
        private TimePickerListener listener;
        private Date minDate;
        private Date maxDate;
        private int year;
        private int month;
        private int day;

        public Builder(FragmentManager fm) {
            this.fm = fm;
        }

        public Builder setListener(TimePickerListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setMinDate(Date minDate) {
            this.minDate = minDate;
            return this;
        }

        public Builder setMaxDate(Date maxDate) {
            this.maxDate = maxDate;
            return this;
        }

        public Builder setStartTime(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
            return this;
        }

        public void show() {
            if (listener == null) {
                throw new NullPointerException("listener is null");
            }

            TimePickerDialogFragment dialogFragment = TimePickerDialogFragment.getInstance(listener, minDate, maxDate, year, month, day);

            dialogFragment.show(fm, TimePickerDialogFragment.TAG);
        }

    }
}
