package com.gitatme.waterwhere.model;

/**
 * Created by Binit Shah on 3/8/17.
 */

public class WaterReport {
    public String userID;
    public String reportID;
    public String datetime;
    public String location;
    public String waterType;
    public String waterCondition;

    public WaterReport(String userID, String reportID, String datetime, String location, String waterType, String waterCondition) {
        this.userID = userID;
        this.reportID = reportID;
        this.datetime = datetime;
        this.location = location;
        this.waterType = waterType;
        this.waterCondition = waterCondition;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        return "WaterReport{" +
                "userID='" + userID + '\'' +
                ", reportID='" + reportID + '\'' +
                ", datetime='" + datetime + '\'' +
                ", location='" + location + '\'' +
                ", waterType='" + waterType + '\'' +
                ", waterCondition='" + waterCondition + '\'' +
                '}';
    }
}
