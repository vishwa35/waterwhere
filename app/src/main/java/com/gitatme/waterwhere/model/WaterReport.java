package com.gitatme.waterwhere.model;

/**
 * creates a water report with data
 */
public class WaterReport {
    private String userID;
    private String reportID;
    private String datetime;
    private double latitude;
    private final double longitude;
    private String waterType;
    private String waterCondition;
    private boolean flaggedForDanger;

    /**
     * initializes a water report
     * @param userID user id of submitter
     * @param reportID hash for identifying report
     * @param datetime time of recording
     * @param latitude latitude of location
     * @param longitude longitude of location
     * @param waterType type of water
     * @param waterCondition condition of water
     */
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
        flaggedForDanger = "Waste".equals(waterCondition.toLowerCase().trim());
    }

    /**
     * getter
     * @return user ID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * setter
     * @param userID userid
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * getter
     * @return report ID
     */
    public String getReportID() {
        return reportID;
    }

    /**
     * setter
     * @param reportID reportID
     */
    public void setReportID(String reportID) {
        this.reportID = reportID;
    }
    /**
     * getter
     * @return datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * setter
     * @param datetime date/time
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * getter
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * getter
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * setter
     * @param latitude latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * setter
     * @param longitude longitude
     */
    public void setLongitude(double longitude) {
        this.latitude = longitude;
    }

    /**
     * getter
     * @return watertype
     */
    public String getWaterType() {
        return waterType;
    }

    /**
     * setter
     * @param waterType water type from spinner
     */
    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    /**
     * getter
     * @return watercondition
     */
    public String getWaterCondition() {
        return waterCondition;
    }

    /**
     * setter
     * @param waterCondition condition from spinner
     */
    public void setWaterCondition(String waterCondition) {
        this.waterCondition = waterCondition;
        flaggedForDanger = "waste".equals(waterCondition.toLowerCase().trim());
    }

    /**
     * getter
     * @return whether or not flagged for danger
     */
    public boolean isFlagged() {
        return flaggedForDanger;
    }

    /**
     * toString method
     * @return string representation of user
     */
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