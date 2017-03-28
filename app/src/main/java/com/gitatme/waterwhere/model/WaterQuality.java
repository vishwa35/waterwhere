package com.gitatme.waterwhere.model;

/**
 * Created by vishwa on 3/28/17.
 */

public class WaterQuality extends WaterReport {

    private int virusPPM;
    private int containmentPPM;

    public WaterQuality(String userID, String reportID, String datetime,
                       double latitude, double longitude,
                       String waterType, String waterCondition) {
        super(userID, reportID, datetime, latitude, longitude, waterType, waterCondition);

    }
    public WaterQuality(String userID, String reportID, String datetime,
                        double latitude, double longitude,
                        String waterType, String waterCondition,
                        int virusPPM, int containmentPPM) {
        super(userID, reportID, datetime, latitude, longitude, waterType, waterCondition);
        this.virusPPM = virusPPM;
        this.containmentPPM = containmentPPM;
    }

    @Override
    public String toString() {
        if (getVirusPPM() < 0 && getContainmentPPM() < 0) {
            return super.toString();
        }
        return super.toString() + "\n" + "Virus PPM: " + getVirusPPM() + "\n" +
                "Containment PPM: " + getContainmentPPM();
    }

    public int getVirusPPM() {
        return virusPPM;
    }

    public int getContainmentPPM() {
        return containmentPPM;
    }

    public void setVirusPPM(int virusPPM1) {
        virusPPM = virusPPM1;
    }

    public void setContainmentPPM(int containmentPPM1) {
        containmentPPM = containmentPPM1;
    }
}
