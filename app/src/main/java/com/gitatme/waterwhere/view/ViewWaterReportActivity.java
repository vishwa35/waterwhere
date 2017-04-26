package com.gitatme.waterwhere.view;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gitatme.waterwhere.R;
import com.gitatme.waterwhere.model.Adapter;
import com.gitatme.waterwhere.model.ReportList;
import com.gitatme.waterwhere.model.ViewHolder;
import com.gitatme.waterwhere.model.WaterReport;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ViewWaterReportActivity extends AppCompatActivity {

    private FirebaseDatabase fbDB;
    private List<WaterReport> reports;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reports = ReportList.getReports();
        setContentView(R.layout.activity_view_water_report);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        Adapter mAdapter = new Adapter(this, reports);
        TextView t = new TextView(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(mLayoutManager);


    }

}
