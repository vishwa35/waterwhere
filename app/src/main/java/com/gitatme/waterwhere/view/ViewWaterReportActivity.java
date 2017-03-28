package com.gitatme.waterwhere.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gitatme.waterwhere.R;
import com.gitatme.waterwhere.model.WaterQuality;
import com.gitatme.waterwhere.model.WaterReport;
import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

public class ViewWaterReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_water_report);
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        /*LinearLayout linearLayout = (LinearLayout) this.findViewById(R.id.linearLayout);
        Set<String> list;
        list = sharedPreferences.getStringSet("waterReport", null);*/
        /**try {
            list = sharedPreferences.getStringSet("waterReport", set);
        } catch (Exception e) {
            System.out.println("ERRORROROR " + e.getMessage());
        }*/
        /*if (list.size() > 0) {
            for (String s : list) {
                WaterReport report = gson.fromJson(s, WaterQuality.class);
                TextView reportText = new TextView(this);
                if (report != null) {
                    reportText.setText(report.toString());
                } else {
                    reportText.setText("You have not yet added any reports");
                }
                linearLayout.addView(reportText);
            }
        } else {
            TextView reportText = new TextView(this);
        }
        for (String s : list) {
            WaterReport report = gson.fromJson(s, WaterQuality.class);
            TextView reportText = new TextView(this);
            if (report != null) {
                reportText.setText(report.toString());
            } else {
                reportText.setText("You have not yet added any reports");
            }
            linearLayout.addView(reportText);
        }*/
        String json1 = sharedPreferences.getString("waterReport", "");
        WaterReport report = gson.fromJson(json1, WaterQuality.class);
//        Toast.makeText(this, obj1.toString(), Toast.LENGTH_SHORT).show();

        TextView reportText = (TextView)findViewById(R.id.existingReport);

        if (report != null) {
            reportText.setText(report.toString());
        } else {
            reportText.setText("You have not yet added any reports");
        }

    }
}
