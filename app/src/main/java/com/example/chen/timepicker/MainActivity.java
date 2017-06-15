package com.example.chen.timepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.timepicker.TimePicker;
import com.example.timepicker.TimePickerListener;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new TimePicker.Builder(getSupportFragmentManager())
                .setListener(new TimePickerListener() {
                    @Override
                    public void onGetDate(Date date) {
                        Toast.makeText(MainActivity.this, "onGetDate", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "onCancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .setMaxDate(new Date(System.currentTimeMillis()))
                .setStartTime(2010,5,5)
                .show();

    }
}
