package com.gitatme.waterwhere.model;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v7.widget.CardView;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gitatme.waterwhere.R;
import com.gitatme.waterwhere.view.ViewWaterReportActivity;

import java.util.List;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.LTGRAY;
import static android.graphics.Color.MAGENTA;
import static android.graphics.Color.WHITE;

/**
 * Created by vishwa on 4/25/17.
 */

public class Adapter extends RecyclerView.Adapter {

    List<WaterReport> reports;
    Context context;
    int position;

    public Adapter(Context c, List<WaterReport> list) {
        reports = list;
        context = c;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        v.setCardElevation(30);
        v.setPadding(50, 0, 50, 0);
        v.setCardBackgroundColor(WHITE);
        ViewHolder viewHolder = new ViewHolder(v);
        onBindViewHolder(viewHolder);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder) {
        TextView textView = new TextView(context);
        textView.setText(reports.get(position).toString());
        textView.setPadding(75, 75, 75, 75);
        if (position%2 == 0) {
            textView.setBackgroundColor(LTGRAY);
        } else {
            textView.setBackgroundColor(GRAY);
        }
        position++;
        holder.getmCardView().addView(textView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        return;
    }
    @Override
    public int getItemCount() {
        return reports.size();
    }
}
