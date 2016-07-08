package com.chenshun.showgirls.ui.view;

import com.chenshun.showgirls.model.ImageListDomain;

import java.util.List;

/**
 * User: chenshun <p />
 * Time: 2016/7/8 09:18  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface ImageListView
{
    void showLaoding();

    void hideLoading();

    void showLoadFaild(Exception e);

    void receiveImageList(List<ImageListDomain> imageListDomains);
}
