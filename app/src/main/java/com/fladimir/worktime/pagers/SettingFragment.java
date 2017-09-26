package com.fladimir.worktime.pagers;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fladimir.worktime.R;
import com.fladimir.worktime.base.BaseFragment;

/**
 * Created by LanFl on 2017/9/24.
 * Setting fragment
 */

public class SettingFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    SwitchCompat setting_notify;
    SwitchCompat setting_shock;
    LinearLayout setting_tomato;
    TextView setting_tomato_time;

    @Override
    protected int setView() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void init() {
        setting_notify = mRoot.findViewById(R.id.setting_notify);
        setting_shock = mRoot.findViewById(R.id.setting_shock);
        setting_tomato = mRoot.findViewById(R.id.setting_tomato);
        setting_tomato_time = mRoot.findViewById(R.id.setting_tomato_time);

        setting_notify.setOnCheckedChangeListener(this);
        setting_shock.setOnCheckedChangeListener(this);
        setting_tomato.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.setting_notify:
                if (b) {
                    setting_shock.setVisibility(View.VISIBLE);
                } else {
                    setting_shock.setVisibility(View.GONE);
                }
                break;
            case R.id.setting_shock:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_tomato:
                Snackbar.make(setting_tomato, "Set Tomato Time", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
