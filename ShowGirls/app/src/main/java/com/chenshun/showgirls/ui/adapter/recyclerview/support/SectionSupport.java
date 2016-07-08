package com.chenshun.showgirls.ui.adapter.recyclerview.support;

/**
 * User: chenshun <p />
 * Time: 2016/7/8 09:59  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface SectionSupport<T>
{
    public int sectionHeaderLayoutId();

    public int sectionTitleTextViewId();

    public String getTitle(T t);
}
