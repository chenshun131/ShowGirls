package com.chenshun.showgirls.ui.typeImageList.persenter;

import com.chenshun.showgirls.ui.typeImageList.domain.TypeImageDomain;
import com.chenshun.showgirls.ui.typeImageList.model.TypeImageListModel;
import com.chenshun.showgirls.ui.typeImageList.model.TypeImageListModelImpl;
import com.chenshun.showgirls.ui.typeImageList.view.TypeImageListView;

import java.util.List;

/**
 * User: chenshun <p />
 * Time: 2016/7/8 10:11  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TypeImageListPersenterImpl implements TypeImageListModelImpl.OnGetTypeImageListener, TypeImageListPersenter
{
    private TypeImageListView mTypeImageListView;
    private TypeImageListModel mTypeImageListModel;

    public TypeImageListPersenterImpl(TypeImageListView typeImageListView)
    {
        mTypeImageListView = typeImageListView;
        mTypeImageListModel = new TypeImageListModelImpl();
    }

    @Override
    public void onSuccess(List<TypeImageDomain> typeImageDomains)
    {
        mTypeImageListView.hideLoading();
        mTypeImageListView.receiveImageList(typeImageDomains);
    }

    @Override
    public void OnError(Exception e)
    {
        mTypeImageListView.hideLoading();
    }

    @Override
    public void startGetImageList(String url)
    {
        mTypeImageListModel.getTypeImageList(url, this);
        mTypeImageListView.showLaoding();
    }
}
