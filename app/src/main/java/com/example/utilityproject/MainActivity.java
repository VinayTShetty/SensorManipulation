package com.example.utilityproject;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity   {
    Button timeset;
    TextView timesetTimeStampView;
    int hour,minutes,day;
    DatePickerDialog datepicker;
    final Calendar myCalendar = Calendar.getInstance();

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeset=(Button)findViewById(R.id.buttonCLick);
        timesetTimeStampView=(TextView)findViewById(R.id.timeStamp_textView);
        getCurrentDate();
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

        timeset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                 @Override
                 public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                     /**
                      * Time Picker Dialog is showing onclick of the Button.
                      */
                     month=month+1;
                     System.out.println("TimeStamp_Data=  year= "+year+" Month= "+month+" Day of Month= "+dayOfMonth);
                     show_TimePickerDialog();
                 }
             },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

    }

   private void getCurrentDate(){
       Calendar c = Calendar.getInstance();
       mYear = c.get(Calendar.YEAR);
       mMonth = c.get(Calendar.MONTH);
       mDay = c.get(Calendar.DAY_OF_MONTH);
       /**
        * Picking up time from android.
        */
       mHour= c.get(Calendar.HOUR);// 24 HOURS formatt--->HOUR_OF_DAY
       mMinute=c.get(Calendar.MINUTE);
   }

   private void getCurrentTime(){

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


    private void show_TimePickerDialog(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                System.out.println("TimeStamp_Data=  Hours= "+hourOfDay+" Minutes= "+minutes);
            }


        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

}