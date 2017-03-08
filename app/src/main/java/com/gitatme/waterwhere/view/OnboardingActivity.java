package com.gitatme.waterwhere.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.gitatme.waterwhere.R;
import com.gitatme.waterwhere.controller.OnboardingPagerAdapter;

public class OnboardingActivity extends FragmentActivity {

    ViewPager viewPager;
    OnboardingPagerAdapter pagerAdapter;
    LottieAnimationView onboardingAnimationView;
    Button forwardButton;
    int pos = 0;
    float progress = 0.0f;

    private static final float[] ANIMATION_TIMES = new float[]{
            0.0f,
            1.0f,
            0.0f,
            1.0f,
            0.0f,
            1.0f,
            0.0f,
            1.0f,
            0.0f,
            1.0f,
            0.0f,
            1.0f
    };

    public final String TAG = "SHIT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new OnboardingPagerAdapter(getSupportFragmentManager());
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
        onboardingAnimationView = (LottieAnimationView) findViewById(R.id.onboarding_animationview);
        onboardingAnimationView.setAnimation("v2.json");
        idealOnPage(pos, true);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //transitionAnimationToPage(position, positionOffset);
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
                idealOnPage(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void transitionAnimationToPage(int position, float positionOffset) {
        float startProgress = ANIMATION_TIMES[position];
        float endProgress = ANIMATION_TIMES[position + 1];
        onboardingAnimationView.setProgress(lerp(startProgress, endProgress, positionOffset));
    }

    private float lerp(float startValue, float endValue, float f) {
        return startValue + f * (endValue - startValue);
    }

    private void idealOnPage(int position, final boolean forward) {
        final ValueAnimator anim = ValueAnimator.ofFloat(ANIMATION_TIMES[(position * 2)], ANIMATION_TIMES[(position * 2) + 1]).setDuration(6000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float frac = animation.getAnimatedFraction();
                if (!forward) {
                    frac = 1 - frac;
                }
                onboardingAnimationView.setProgress(frac);
                Log.d(TAG, "FRACTION: " + frac);
            }
        });
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (forward) {
                    idealOnPage(pos, false);
                } else {
                    idealOnPage(pos, true);
                }
            }
        });
        anim.start();
    }
}
