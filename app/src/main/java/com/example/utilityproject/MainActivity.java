package com.example.utilityproject;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    Button timeset;
    TextView timesetTimeStampView;
    int hour,minutes,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeset=(Button)findViewById(R.id.buttonCLick);
        timesetTimeStampView=(TextView)findViewById(R.id.timeStamp_textView);

        timeset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        Calendar calendarLocal = new GregorianCalendar(TimeZone.getDefault());
                        SimpleDateFormat month_nameFormateer = new SimpleDateFormat("MM");
                        String month = month_nameFormateer.format(calendarLocal.getTime());
                        System.out.println("---> "+month);
                        calendarLocal.set(2021, 11, 1);
                        calendarLocal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendarLocal.set(Calendar.MINUTE, minutes);
                        Long timestamp = calendarLocal.getTimeInMillis();
                        timesetTimeStampView.setText(hourOfDay+":"+minutes+"\n"+"TimeStamp= "+timestamp);
                        System.out.println("TimeStamp= "+timestamp);
                    }


                }, 0, 0, false);
                timePickerDialog.show();
            }


        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}