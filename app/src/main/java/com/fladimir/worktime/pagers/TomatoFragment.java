package com.fladimir.worktime.pagers;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fladimir.worktime.R;
import com.fladimir.worktime.base.BaseFragment;

/**
 * Created by LanFl on 2017/9/24.
 * 番茄时间
 */

public class TomatoFragment extends BaseFragment implements View.OnClickListener {
    TextView tomato_time;
    int status = 0;//0  未开始  1进行中  2 暂停
    private Runnable startTomato;
    private int mWorkTime = 20 * 60;

    @Override
    protected int setView() {
        return R.layout.fragment_tomato;
    }

    @Override
    protected void init() {
        tomato_time = mRoot.findViewById(R.id.tomato_time);
        tomato_time.setOnClickListener(this);

        startTomato = () -> {
            while (true) {
                if (status == 1) {
                    mWorkTime--;
                    getActivity().runOnUiThread(() -> {
                        if (mWorkTime == 0) {
                            Toast.makeText(mContex, "Time is Coming!", Toast.LENGTH_LONG).show();
                            status = 0;
                            mWorkTime = 20 * 60;
                        } else
                            setText(mWorkTime);
                    });
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
    }

    @Override
    public void onClick(View view) {
        if (status == 0) {
            new Thread(startTomato).start();
            status = 1;
        } else if (status == 1)
            status = 2;
        else status = 1;
    }

    /**
     * 通过秒数
     *
     * @param mWorkTime
     */
    public void setText(int mWorkTime) {
        int min = mWorkTime / 60;
        int sec = mWorkTime % 60;
        tomato_time.setText(toTens(min) + " : " + toTens(sec));
    }

    /**
     * 转换两位数
     *
     * @param num
     * @return
     */
    private String toTens(int num) {
        if (num > 9)
            return num + "";
        else
            return "0" + num;
    }
}
