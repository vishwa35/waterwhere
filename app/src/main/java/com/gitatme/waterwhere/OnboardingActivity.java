package com.gitatme.waterwhere;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class OnboardingActivity extends FragmentActivity {

    RelativeLayout relativeLayout;
    ViewPager viewPager;
    MyPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        //setStatusBarColor(findViewById(R.id.statusBarBackground), ContextCompat.getColor(this, R.color.transparent));
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        private final int NUM_PAGES = 5;

        public MyPagerAdapter(FragmentManager fragmentManager) {
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
}
