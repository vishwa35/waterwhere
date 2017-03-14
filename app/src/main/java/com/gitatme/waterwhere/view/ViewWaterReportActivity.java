package com.gitatme.waterwhere.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
        WaterReport obj1 = gson.fromJson(json1, WaterReport.class);
//        Toast.makeText(this, obj1.toString(), Toast.LENGTH_SHORT).show();

        TextView reportText = (TextView)findViewById(R.id.existingReport);
        reportText.setText(obj1.toString());
    }

}
