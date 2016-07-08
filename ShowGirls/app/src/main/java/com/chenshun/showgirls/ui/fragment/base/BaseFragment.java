package com.chenshun.showgirls.ui.fragment.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * User: chenshun <p />
 * Time: 2016/7/8 09:15  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public abstract class BaseFragment extends Fragment
{
    public Activity context;
    public View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (rootView == null)
        {
            rootView = inflater.inflate(getLayoutResource(), container, false);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        initView();
        initData();
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract int getLayoutResource();

    protected abstract void initData();

    protected abstract void initView();

    @Override
    public void onAttach(Activity activity)
    {
        this.context = activity;
        super.onAttach(activity);
    }
}
