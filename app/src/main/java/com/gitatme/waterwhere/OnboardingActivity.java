package com.gitatme.waterwhere;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OnboardingActivity extends FragmentActivity {

    ViewPager viewPager;
    MyPagerAdapter pagerAdapter;
    Button forwardButton;
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        forwardButton = (Button) findViewById(R.id.forward_button);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == 3) {
                    Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(login);
                    finish();
                } else {
                    viewPager.setCurrentItem(3, true);
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 3:
                        forwardButton.setText(getString(R.string.forward_button_join));
                        break;
                    default:
                        forwardButton.setText(getString(R.string.forward_button_skip));
                }
                pos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
