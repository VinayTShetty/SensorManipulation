package com.example.utilityproject.Dataparsing;

import java.io.Serializable;
import java.util.List;

public class SessionCompletedData implements Serializable {


    /**
     * Session info..
     */
    private String sessionId;
    private String playerId;

    private String timeStamp;
    private String sessionReadInterval;
    private String numberOfSensors;

    private String sensorCompletedDataConcatenated;

    public String getSensorCompletedDataConcatenated() {
        return sensorCompletedDataConcatenated;
    }

    public void setSensorCompletedDataConcatenated(String sensorCompletedDataConcatenated) {
        this.sensorCompletedDataConcatenated = sensorCompletedDataConcatenated;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId.substring(6,8);
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId.substring(8,16);
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp.substring(16,24);
    }

    public String getSessionReadInterval() {
        return sessionReadInterval;
    }

    public void setSessionReadInterval(String sessionReadInterval) {
        this.sessionReadInterval = sessionReadInterval.substring(24,28);
    }

    public String getNumberOfSensors() {
        return numberOfSensors;
    }

    public void setNumberOfSensors(String numberOfSensors) {
        this.numberOfSensors = numberOfSensors.substring(28,30);
    }


    /**
     * Session Player Name.
     */
    private String sessionPlayerName;

    public String getSessionPlayerName() {
        return sessionPlayerName;
    }

    public void setSessionPlayerName(String sessionPlayerName) {
        this.sessionPlayerName = sessionPlayerName.substring(6,sessionPlayerName.length());
    }

    /**
     * Session info.
     */

   public List<SensorID_SensorType> sensorID_sensorTypeList;

    public List<SensorID_SensorType> getSensorID_sensorTypeList() {
        return sensorID_sensorTypeList;
    }

    public void setSensorID_sensorTypeList(List<SensorID_SensorType> sensorID_sensorTypeList) {
        this.sensorID_sensorTypeList = sensorID_sensorTypeList;

    }



    /**
     * Session Data
     */

    public  List<SequenceNumber_SensorData> sequenceNumberSensorData;

    public List<SequenceNumber_SensorData> getSequenceNumberSensorData() {
        return sequenceNumberSensorData;
    }

    public void setSequenceNumberSensorData(List<SequenceNumber_SensorData> sequenceNumberSensorData) {
        this.sequenceNumberSensorData = sequenceNumberSensorData;
    }


}
