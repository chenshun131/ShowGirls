package com.chenshun.showgirls.model;

/**
 * User: chenshun <p />
 * Time: 2016/7/8 09:22  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface ImageListModel
{
    void GetImageList(String type, int page, ImageListModelImpl.GetImageListenter listener);
}
