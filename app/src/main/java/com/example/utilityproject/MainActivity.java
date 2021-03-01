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

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity   {
    Button timeset;
    TextView timesetTimeStampView;
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
             DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                 @Override
                 public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       mYear=year;
                       mMonth=month;
                       mDay=dayOfMonth;
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
                convert_SelectedDate_Time_TimeStamp(mYear,mMonth,mDay,hourOfDay,minutes);
            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private long convert_SelectedDate_Time_TimeStamp(int year_input, int month_input, int date_input, int hour_input, int mins_input){
        long timeStampConvertedData=0;
        Calendar calendarLocal = new GregorianCalendar(TimeZone.getDefault());
        calendarLocal.set(year_input, month_input, date_input);
        calendarLocal.set(Calendar.HOUR_OF_DAY, hour_input);
        calendarLocal.set(Calendar.MINUTE, mins_input);
        timeStampConvertedData= calendarLocal.getTimeInMillis();
        System.out.println("TimeStamp_Data TimeInMilliSeconds= "+timeStampConvertedData);
        timesetTimeStampView.setText("Time = "+calendarLocal.get(Calendar.HOUR)+":"+calendarLocal.get(Calendar.MINUTE)+"\n"+" TimeStamp = "+timeStampConvertedData);
        textViewSetDate_Time(timesetTimeStampView,calendarLocal);
        return timeStampConvertedData;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void textViewSetDate_Time(TextView date_Time, Calendar calendar){
        /**
         * 24 hours Formatt
         */
/*        calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        String test = sdf.format(calendar.getTime());
        date_Time.setText(test);
        Log.e("TEST", test);*/

        /**
         * 12 hours formatt
         */
       /* calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        String test = sdf.format(calendar.getTime());
        date_Time.setText(test);
        Log.e("TEST", test);*/

        /**
         * 12 hours formatt discarding mins.
         */
        calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        String test = sdf.format(calendar.getTime());
        date_Time.setText(test);
        Log.e("TEST", test);

    }
    }

