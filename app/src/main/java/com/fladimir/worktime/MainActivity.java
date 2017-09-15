package com.fladimir.worktime;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView main_current;
    private static PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_current = (TextView) findViewById(R.id.main_current);
        main_current.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                finish();
                return false;
            }
        });
        setTime.start();
        keepScreenOn(MainActivity.this, true);
    }

    Thread setTime = new Thread() {
        @Override
        public void run() {
            super.run();
            while (true) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] time = getCurrent();
                        main_current.setText(time[0] + " : " + time[1]);
                    }
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
        String date = (String) DateFormat.format("HH:MM", current);
        String[] dateS = date.split(":");
        return new String[]{dateS[0], dateS[1]};
    }

    @Override
    public void onBackPressed() {
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
