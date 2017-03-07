package com.gitatme.waterwhere.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gitatme.waterwhere.view.OnboardingFragment;

/**
 * Created by binitshah on 3/7/17.
 */

public class OnboardingPagerAdapter extends FragmentStatePagerAdapter {
    private final int NUM_PAGES = 5;

    public OnboardingPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public Fragment getItem(int position) {
        OnboardingFragment onboardingFragment = new OnboardingFragment();
        onboardingFragment.newInstance(position);
        return onboardingFragment;
    }
}
