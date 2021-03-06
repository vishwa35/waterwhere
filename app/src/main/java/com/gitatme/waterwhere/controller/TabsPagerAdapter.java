package com.gitatme.waterwhere.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gitatme.waterwhere.view.LoginFragment;
import com.gitatme.waterwhere.view.RegistrationFragment;

/**
 * The adapter for login tabs
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    /**
     * constructor
     * @param fm fragment manager
     */
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new LoginFragment();
        } else {
            return new RegistrationFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Login";
        } else {
            return "Registration";
        }
    }
}
