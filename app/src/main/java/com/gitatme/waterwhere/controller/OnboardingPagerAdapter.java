package com.gitatme.waterwhere.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gitatme.waterwhere.view.OnboardingFragment;


public class OnboardingPagerAdapter extends FragmentStatePagerAdapter {

    public OnboardingPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        int NUM_PAGES = 5;
        return NUM_PAGES;
    }

    @Override
    public Fragment getItem(int position) {
        OnboardingFragment onboardingFragment = new OnboardingFragment();
        onboardingFragment.newInstance(position);
        return onboardingFragment;
    }
}
