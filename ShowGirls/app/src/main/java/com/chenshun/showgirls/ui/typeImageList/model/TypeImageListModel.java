package com.chenshun.showgirls.ui.typeImageList.model;

/**
 * User: chenshun <p />
 * Time: 2016/7/8 10:09  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface TypeImageListModel
{
    void getTypeImageList(String url, TypeImageListModelImpl.OnGetTypeImageListener litener);
}
