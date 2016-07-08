package com.chenshun.showgirls.ui.adapter.recyclerview;

/**
 * User: chenshun <p />
 * Time: 2016/7/8 09:57  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface MultiItemTypeSupport<T>
{
    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}
