package com.gitatme.waterwhere.model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vishwa on 4/25/17.
 */

public class ReportList {

    private static FirebaseDatabase fbDb = FirebaseDatabase.getInstance();
    private static DatabaseReference dbRef = fbDb.getReference();
    private static List<WaterReport> list = new ArrayList<>();
    private static HashMap<String, WaterReport> map;

    public static List<WaterReport> getReports() {
        dbRef.child("waterReport").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<WaterReport>();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    list.add(child.getValue(WaterReport.class));
                }
                //GenericTypeIndicator<List<WaterReport>> genericTypeIndicator = new GenericTypeIndicator<List<WaterReport>>() {};
                //map = (HashMap<String, WaterReport>) dataSnapshot.getValue(genericTypeIndicator);
                //list = (List<WaterReport>) dataSnapshot.getValue(genericTypeIndicator);
                //notify();
            }
            public void onCancelled(DatabaseError error) {
                return;
            }
        });
        return list;
    }


}
