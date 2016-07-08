package com.chenshun.showgirls.persenter;

import com.chenshun.showgirls.model.ImageListDomain;
import com.chenshun.showgirls.model.ImageListModel;
import com.chenshun.showgirls.model.ImageListModelImpl;
import com.chenshun.showgirls.ui.view.ImageListView;

import java.util.List;

/**
 * User: chenshun <p />
 * Time: 2016/7/8 09:21  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ImageListPersenterImpl implements ImageListModelImpl.GetImageListenter, ImageListPersenter
{
    private ImageListView imageListView;
    private ImageListModel imageListModel;

    public ImageListPersenterImpl(ImageListView imageListView)
    {
        this.imageListView = imageListView;
        this.imageListModel = new ImageListModelImpl();
    }

    @Override
    public void onSuccess(List<ImageListDomain> imageList)
    {
        imageListView.receiveImageList(imageList);
        imageListView.hideLoading();
    }

    @Override
    public void OnError(Exception e)
    {
        imageListView.showLoadFaild(e);
        imageListView.hideLoading();
    }

    @Override
    public void startGetImageList(String type, int page)
    {
        imageListView.showLaoding();
        imageListModel.GetImageList(type, page, this);
    }
}

