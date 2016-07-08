package com.chenshun.showgirls.ui.typeImageList.view;

import com.chenshun.showgirls.ui.typeImageList.domain.TypeImageDomain;

import java.util.List;

/**
 * User: chenshun <p />
 * Time: 2016/7/8 10:12  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface TypeImageListView
{
    void showLaoding();

    void hideLoading();

    void showLoadFaild(Exception e);

    void receiveImageList(List<TypeImageDomain> typeImageDomains);
}
