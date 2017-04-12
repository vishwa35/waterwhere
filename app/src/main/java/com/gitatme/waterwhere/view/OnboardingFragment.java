package com.gitatme.waterwhere.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gitatme.waterwhere.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OnboardingFragment extends Fragment {


    public OnboardingFragment() {}

    public void newInstance(int position){
        int position1 = position;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding, container, false);
    }

}
