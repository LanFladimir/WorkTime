package com.fladimir.worktime.pagers;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fladimir.worktime.MainActivity;
import com.fladimir.worktime.R;
import com.fladimir.worktime.base.BaseFragment;

/**
 * Created by LanFl on 2017/9/24.
 */

public class ClockFragment extends BaseFragment {
    private TextView main_current;
    private static PowerManager.WakeLock wakeLock;

    @Override
    protected int setView() {
        return R.layout.fragment_clock;
    }

    @Override
    protected void init() {
        main_current = mRoot.findViewById(R.id.main_current);
        main_current.setOnLongClickListener(view -> {
            getActivity().finish();
            return false;
        });
        setTime.start();
        keepScreenOn(mContex, true);
    }

    Thread setTime = new Thread() {
        @Override
        public void run() {
            super.run();
            while (true) {
                getActivity().runOnUiThread(() -> {
                    String[] time = getCurrent();
                    main_current.setText(time[0] + " : " + time[1]);
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * 当前时间
     *
     * @return
     */
    private static String[] getCurrent() throws NumberFormatException {
        long current = System.currentTimeMillis();
        String date = (String) DateFormat.format(":mm:ss", current);
        String[] dateS = date.split(":");
        Log.e("当前时间", dateS[0] + ":" + dateS[1] + ":" + dateS[2]);
        return new String[]{dateS[0], dateS[1]};
    }


    public static void keepScreenOn(Context context, boolean on) {
        if (on) {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
                    | PowerManager.ON_AFTER_RELEASE, "==KeepScreenOn==");
            wakeLock.acquire();
        } else {
            if (wakeLock != null) {
                wakeLock.release();
                wakeLock = null;
            }
        }
    }
}
