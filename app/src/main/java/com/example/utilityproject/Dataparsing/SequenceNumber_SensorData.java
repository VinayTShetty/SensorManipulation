package com.example.utilityproject.Dataparsing;

public class SequenceNumber_SensorData {
    private String sequenceNumber;
    private String sensorData;

    public SequenceNumber_SensorData(String sequenceNumber, String sensorData) {
        this.sequenceNumber = sequenceNumber;
        this.sensorData = sensorData;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getSensorData() {
        return sensorData;
    }

    public void setSensorData(String sensorData) {
        this.sensorData = sensorData;
    }
}
