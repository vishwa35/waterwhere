package com.gitatme.waterwhere.model;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by vishwa on 4/25/17.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private CardView mCardView;

    public ViewHolder(CardView view) {
        super(view);
        mCardView = view;
    }

    public CardView getmCardView() {
        return mCardView;
    }
}
