package com.fladimir.worktime;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.fladimir.worktime.base.BaseFragmentAdapter;
import com.fladimir.worktime.pagers.ClockFragment;
import com.fladimir.worktime.pagers.SettingFragment;
import com.fladimir.worktime.pagers.TomatoFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TomatoFragment mTomatoFragment;
    private ClockFragment mClockFragment;
    private SettingFragment mSettingFragment;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);

        mTomatoFragment = new TomatoFragment();
        mClockFragment = new ClockFragment();
        mSettingFragment = new SettingFragment();

        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(mTomatoFragment);
        mFragments.add(mClockFragment);
        mFragments.add(mSettingFragment);
        mViewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), mFragments));
        mViewPager.setOffscreenPageLimit(4);
    }


    @Override
    public void onBackPressed() {
    }
}
