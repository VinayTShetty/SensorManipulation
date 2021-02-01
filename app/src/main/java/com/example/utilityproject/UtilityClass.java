package com.example.utilityproject;

public class UtilityClass {

    public static String getStringOfParticularLength(String hexStringValuePassed){
        String getLengthToExtract=hexStringValuePassed.substring(2,4);
        int convertedHexValue=Integer.parseInt(getLengthToExtract,16);
        convertedHexValue=convertedHexValue*2;
        String extractedToReadValue=hexStringValuePassed.substring(6,hexStringValuePassed.length());
        String stringToExtract=extractedToReadValue.substring(0,convertedHexValue-2);
        return stringToExtract;
    }

    public static String getOnlySensorDataPackets(String hexValueInput){
        String getLengthToExtract=hexValueInput.substring(2,4);
        int convertedHexValue=Integer.parseInt(getLengthToExtract,16);
        convertedHexValue=convertedHexValue-1;
        convertedHexValue=convertedHexValue*2;
        System.out.println(" Value Returened "+hexValueInput.substring(10,convertedHexValue+6));
       return hexValueInput.substring(10,convertedHexValue+6);

    }
}
