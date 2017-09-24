package com.fladimir.worktime.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LanFl on 2017/9/24.
 */

public abstract class BaseFragment extends Fragment {
    public View mRoot;
    public Context mContex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContex = getActivity();
        mRoot = inflater.inflate(setView(), null, false);
        init();
        return mRoot;
    }

    protected abstract int setView();

    protected abstract void init();
}
