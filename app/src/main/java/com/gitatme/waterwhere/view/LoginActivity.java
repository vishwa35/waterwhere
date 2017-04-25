package com.gitatme.waterwhere.view;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gitatme.waterwhere.R;
import com.gitatme.waterwhere.controller.TabsPagerAdapter;

/**
 * This activity inflates Login Fragment
 */
public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager());
        findViewById(R.id.tabs).bringToFront();

        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);
    }
}
