package com.chenshun.showgirls.ui.adapter.recyclerview;

import android.view.View;
import android.view.ViewGroup;

/**
 * User: chenshun <p />
 * Time: 2016/7/8 09:54  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface OnItemClickListener<T>
{
    void onItemClick(ViewGroup parent, View view, T t, int position);

    boolean onItemLongClick(ViewGroup parent, View view, T t, int position);
}
