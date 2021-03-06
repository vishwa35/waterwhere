package com.gitatme.waterwhere.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gitatme.waterwhere.view.OnboardingFragment;

/**
 * Adapter for onboarding page
 */
public class OnboardingPagerAdapter extends FragmentStatePagerAdapter {
    /**
     * constructor for onBoarding
     * @param fragmentManager the fragment manager
     */
    public OnboardingPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int position) {
        OnboardingFragment onboardingFragment = new OnboardingFragment();
        onboardingFragment.newInstance(position);
        return onboardingFragment;
    }
}
