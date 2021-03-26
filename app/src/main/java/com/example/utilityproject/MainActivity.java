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
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    Button timeset;
    TextView timesetTimeStampView;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeset = (Button) findViewById(R.id.buttonCLick);
        timesetTimeStampView = (TextView) findViewById(R.id.timeStamp_textView);
        getCurrentDate();
        timeset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mYear = year;
                        mMonth = month;
                        mDay = dayOfMonth;
                        show_TimePickerDialog();
                    }
                }, mYear, mMonth, mDay);


                Calendar c1 = Calendar.getInstance();

                //first day of week
                c1.set(Calendar.DAY_OF_WEEK, 1);

                int year1 = c1.get(Calendar.YEAR);
                int month1 = c1.get(Calendar.MONTH) + 1;
                int day1 = c1.get(Calendar.DAY_OF_MONTH);


                Calendar c2 = Calendar.getInstance();
                //last day of week
                c2.set(Calendar.DAY_OF_WEEK, 7);

                int year7 = c2.get(Calendar.YEAR);
                int month7 = c2.get(Calendar.MONTH) + 1;
                int day7 = c2.get(Calendar.DAY_OF_MONTH);


                datePickerDialog.getDatePicker().setMinDate(c1.getTimeInMillis());
                datePickerDialog.getDatePicker().setMaxDate(c2.getTimeInMillis());
                datePickerDialog.show();
            }
        });
        System.out.println("Binary Vlaue= "+hexToBin("127"));

    }

    static String hexToBin(String s) {
        return new BigInteger(s, 16).toString(2);
    }

    private void getCurrentDate() {
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        /**
         * Picking up time from android.
         */
        mHour = c.get(Calendar.HOUR);// 24 HOURS formatt--->HOUR_OF_DAY
        mMinute = c.get(Calendar.MINUTE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getDateFromTimeStamp(1615961388);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void show_TimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                System.out.println("TimeStamp_Data=  Hours= " + hourOfDay + " Minutes= " + minutes);
                convert_SelectedDate_Time_TimeStamp(mYear, mMonth, mDay, hourOfDay, minutes);
            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private long convert_SelectedDate_Time_TimeStamp(int year_input, int month_input, int date_input, int hour_input, int mins_input) {
        long timeStampConvertedData = 0;
        Calendar calendarLocal = new GregorianCalendar(TimeZone.getDefault());
        calendarLocal.set(year_input, month_input, date_input);
        calendarLocal.set(Calendar.HOUR_OF_DAY, hour_input);
        calendarLocal.set(Calendar.MINUTE, mins_input);
        calendarLocal.set(Calendar.SECOND, 0);
        timeStampConvertedData = calendarLocal.getTimeInMillis();
        System.out.println("TimeStamp_Data TimeInMilliSeconds= " + timeStampConvertedData);
        timesetTimeStampView.setText("Time = " + calendarLocal.get(Calendar.HOUR) + ":" + calendarLocal.get(Calendar.MINUTE) + "\n" + " TimeStamp = " + timeStampConvertedData);
        textViewSetDate_Time(timesetTimeStampView, calendarLocal);
        return timeStampConvertedData;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void textViewSetDate_Time(TextView date_Time, Calendar calendar) {
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
        // calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        String test = sdf.format(calendar.getTime());
        date_Time.setText(test);
        Log.e("TEST", test);
    }

    //TimeStamp to Time


    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getTimeFromTimeStamp(){
      /*  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(Long.parseLong("1615902512")));
        timesetTimeStampView.setText("Date----------->"+dateString);*/

        try{
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getDefault();
            calendar.setTimeInMillis(1615903584 * 1000);
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currenTimeZone = (Date) calendar.getTime();
            timesetTimeStampView.setText("Curent Time--->"+sdf.format(currenTimeZone));
            return sdf.format(currenTimeZone);
        }catch (Exception e) {
        }
        return "";
    }


    /**
     *
     * This method is used to get Date From TimeStamp.
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getDateFromTimeStamp(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);

        /**
         * Date
         */
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
       // timesetTimeStampView.setText("Curent Time--->"+date);

        /**
         * This logic is giving both Date and Time.
         */
       SimpleDateFormat Date_Time_both = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat time_12_hours = new SimpleDateFormat("hh.mm aa");
        Date currenTimeZone = (Date) cal.getTime();
        timesetTimeStampView.setText("Curent Time--->"+time_12_hours.format(currenTimeZone));
        return date;
    }
}

