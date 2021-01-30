package com.example.utilityproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.utilityproject.Dataparsing.SensorID_SensorType;
import com.example.utilityproject.Dataparsing.SequenceNumber_SensorData;
import com.example.utilityproject.Dataparsing.SessionCompletedData;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;

import static com.example.utilityproject.UtilityClass.getOnlySensorDataPackets;
import static com.example.utilityproject.UtilityClass.getStringOfParticularLength;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    final Handler handler = new Handler(Looper.getMainLooper());
    public static final String TAG=MainActivity.class.getName()+"SESSION_DATA_PARSING";
    private static String COMPLETE_SENSOR_DATA="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            String data="100FFC00050A280A290A280A2B0A2D0A2C00";
        System.out.println("------------------>"+data.substring(10,24+10));
        processData();

    }

    private void processData(){
        ArrayList<String> hexStringList=new ArrayList<String>();
        hexStringList.add("100DFF02000000016c45324500020B000000");
        hexStringList.add("1005FE546573740000000000000000000000");
        hexStringList.add("1010FD014D03015C04012D03019E03016603");
        hexStringList.add("1010FD014E03015D04012E03019F03016703");
        hexStringList.add("1004FD014F03000000000000000000000000");
        hexStringList.add("100FFC00010A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00020A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00030A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00040A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00050A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00060A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00070A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00080A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00090A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC000A0A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC000B0A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC000C0A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC000D0A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC000E0A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC000F0A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00100A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00110A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00120A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00130A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00140A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00150A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00160A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00170A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00180A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100FFC00190A280A290A280A2B0A2D0A2C00");
        hexStringList.add("100BFC001A0A280A290A280A2B0000000000");
        hexStringList.add("1002FB02");

        SessionCompletedData sessionCompletedData =new SessionCompletedData();
        sessionCompletedData.setSensorCompletedDataConcatenated("");
        sessionCompletedData.sensorID_sensorTypeList=new ArrayList<>();
        COMPLETE_SENSOR_DATA="";
        for (int i = 0; i <hexStringList.size() ; i++) {
            if(hexStringList.get(i).substring(4,6).equalsIgnoreCase("FF")){
                /**
                 * Starting packet is FF.
                 */
                String packForthisValriable=hexStringList.get(i).toString();
                sessionCompletedData.setSessionId(packForthisValriable);
                sessionCompletedData.setPlayerId(packForthisValriable);
                sessionCompletedData.setTimeStamp(packForthisValriable);
                sessionCompletedData.setNumberOfSensors(packForthisValriable);
            }else if(hexStringList.get(i).substring(4,6).equalsIgnoreCase("FE")){
                String packForthisValriable=hexStringList.get(i).toString();
                sessionCompletedData.setSessionPlayerName(packForthisValriable);
            }else if(hexStringList.get(i).substring(4,6).equalsIgnoreCase("FD")){
                String stringValueForthisBlock=hexStringList.get(i);
                String particularPacektToProcess=getStringOfParticularLength(stringValueForthisBlock);
                while (particularPacektToProcess.length()>0){
                    sessionCompletedData.sensorID_sensorTypeList.add(new SensorID_SensorType(particularPacektToProcess,particularPacektToProcess));
                    particularPacektToProcess=particularPacektToProcess.substring(6);
                }
            }else if(hexStringList.get(i).substring(4,6).equalsIgnoreCase("FC")){
                /**
                 * Complete Sensor Data.
                 */
                String stringValueForthisBlock=hexStringList.get(i);

                COMPLETE_SENSOR_DATA=COMPLETE_SENSOR_DATA+getOnlySensorDataPackets(stringValueForthisBlock);
                Log.d(TAG, "processData: COMPLETE SENSOR DATA= "+COMPLETE_SENSOR_DATA);


            }else if(hexStringList.get(i).substring(4,6).equalsIgnoreCase("FB")){
                Log.d(TAG, " SESSION ID= "+sessionCompletedData.getSessionId()+
                        "\n PLAYER ID "+sessionCompletedData.getPlayerId()+
                        "\n TIME STAMP= "+sessionCompletedData.getTimeStamp()+
                        "\n NUMBER OF SENSOR= "+sessionCompletedData.getNumberOfSensors()+
                        "\n Session Player Name= "+sessionCompletedData.getSessionPlayerName());
                Log.d(TAG, "onCreate: SENSOR TYPE SENSOR ID SIZE LIST= "+sessionCompletedData.sensorID_sensorTypeList.size());
                for (int counter = 0; counter < sessionCompletedData.sensorID_sensorTypeList.size(); counter++) {
                    Log.d(TAG, "onCreate: SENSOR_ID_TYPES "+" SENSOR ID= "+sessionCompletedData.sensorID_sensorTypeList.get(counter).getSensorID()+" SENSOR TYPE= "+sessionCompletedData.sensorID_sensorTypeList.get(counter).getSensorType());
                }
                Log.d(TAG, "onCreate: COMPLETE_SENSOR_DATA= "+COMPLETE_SENSOR_DATA+" Length= \n"+COMPLETE_SENSOR_DATA.length());
            }
        }
    }


    public byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }



    private void setTimeTextVisibility(Date presentDate, Date previousDate, TextView timeText){
        if(presentDate.compareTo(previousDate)==0){
            System.out.println("same date ");
            timeText.setVisibility(View.GONE);
        }else if(presentDate.compareTo(previousDate)==1){
            System.out.println("1");
            timeText.setVisibility(View.VISIBLE);
            timeText.setText(presentDate.toString());
            System.out.println("Demo application = "+presentDate.toString());
            String dateWithoutTime = presentDate.toString().substring(0, 10);
            System.out.println(" Demo application = "+dateWithoutTime);

        }else if(presentDate.compareTo(previousDate)==-1){
            System.out.println("-1");
        }
    }

    private static String convertHexStringToString(String hexStringInput) {
        String result = new String();
        char[] charArray = hexStringInput.toCharArray();
        for(int i = 0; i < charArray.length; i=i+2) {
            String st = ""+charArray[i]+""+charArray[i+1];
            char ch = (char)Integer.parseInt(st, 16);
            result = result + ch;
        }
        return result;
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