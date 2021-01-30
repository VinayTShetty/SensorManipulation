package com.example.utilityproject.Dataparsing;

public class SensorID_SensorType {
    private String sensorID;
    private String sensorType;


    public SensorID_SensorType(String sensorID, String sensorType) {
        this.sensorID = sensorID.substring(0,4);
        this.sensorType =sensorType.substring(4,6);
    }

    public String getSensorID() {
        return sensorID;
    }

    public void setSensorID(String stringInputPassed) {
        this.sensorID = stringInputPassed.substring(0,4);
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String stringInputToPassed) {
        this.sensorType = stringInputToPassed.substring(4,6);
    }
}
