package com.gitatme.waterwhere;

import com.gitatme.waterwhere.model.WaterReport;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Shukan Shah
 */

public class WaterReportFlaggedUnitTest {
    private WaterReport mockReport;

    @Before
    public void setUp() {
        mockReport = new WaterReport("shukan@gmail.com", "45657", "5/06/17",
                103.56, 56.78, "Lake", "Potable");
    }

    @Test
    public void testFlagged() {
        assertEquals("The report should not be flagged for danger!",
                mockReport.isFlagged(), false);
        mockReport.setWaterCondition("Waste");
        assertEquals("The report should be flagged for danger!",
                mockReport.isFlagged(), true);
        mockReport.setWaterCondition("Treatable-Clear");
        assertEquals("The report should not be flagged for danger!",
                mockReport.isFlagged(), false);
        mockReport.setWaterCondition("Treatable-Muddy");
        assertEquals("The report should not be flagged for danger!",
                mockReport.isFlagged(), false);
    }

}
