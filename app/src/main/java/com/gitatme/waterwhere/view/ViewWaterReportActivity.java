package com.gitatme.waterwhere.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gitatme.waterwhere.R;
import com.gitatme.waterwhere.model.WaterReport;
import com.google.gson.Gson;

public class ViewWaterReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_water_report);
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String json1 = sharedPreferences.getString("waterReport", "");
        WaterReport report = gson.fromJson(json1, WaterReport.class);
//        Toast.makeText(this, obj1.toString(), Toast.LENGTH_SHORT).show();

        TextView reportText = (TextView)findViewById(R.id.existingReport);

        if (report != null) {
            reportText.setText(report.toString());
        } else {
            reportText.setText("You have not yet added any reports");
        }

    }

}
