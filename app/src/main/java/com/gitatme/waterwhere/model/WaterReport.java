package com.gitatme.waterwhere.model;

/**
 * Created by Binit Shah on 3/8/17.
 */

public class WaterReport {
    private String userID;
    private String reportID;
    private String datetime;
    private double latitude;
    private double longitude;
    private String waterType;
    private String waterCondition;

    public WaterReport(String userID, String reportID, String datetime,
                       double latitude, double longitude,
                       String waterType, String waterCondition) {
        this.userID = userID;
        this.reportID = reportID;
        this.datetime = datetime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.waterType = waterType;
        this.waterCondition = waterCondition;
    }
    public WaterReport() {}

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.latitude = longitude;
    }

    public String getWaterType() {
        return waterType;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public String getWaterCondition() {
        return waterCondition;
    }

    public void setWaterCondition(String waterCondition) {
        this.waterCondition = waterCondition;
    }

    @Override
    public String toString() {
        return "UserID: " + userID + "\n" +
                "ReportID: " + reportID + "\n" +
                "Datetime: " + datetime + "\n" +
                "Latitude: " + latitude + "\n" +
                "Longitude: " + longitude + "\n" +
                "WaterType: " + waterType + "\n" +
                "WaterCondition: " + waterCondition;
    }
}