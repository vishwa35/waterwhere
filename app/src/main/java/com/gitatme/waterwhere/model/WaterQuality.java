package com.gitatme.waterwhere.model;

/**
 * Created by vishwa on 3/28/17.
 */

public class WaterQuality extends WaterReport {

    private int virusPPM;
    private int containmentPPM;

    /**
     *
     * @param userID username
     * @param reportID report id
     * @param datetime date
     * @param latitude lat
     * @param longitude long
     * @param waterType type of water
     * @param waterCondition condition
     * default assigns -1 to virus and ocntainment
     */
    public WaterQuality(String userID, String reportID, String datetime,
                       double latitude, double longitude,
                       String waterType, String waterCondition) {
        super(userID, reportID, datetime, latitude, longitude, waterType, waterCondition);
        virusPPM = -1;
        containmentPPM = -1;

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
    /**
     * Used to view report, returns string form of report. If virus and containment
     * are -1 (empty submission), it will not show those fields
     */
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
