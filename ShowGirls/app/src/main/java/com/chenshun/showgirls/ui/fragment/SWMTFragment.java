package com.chenshun.showgirls.ui.fragment;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chenshun.showgirls.Constant;
import com.chenshun.showgirls.R;
import com.chenshun.showgirls.model.ImageListDomain;
import com.chenshun.showgirls.persenter.ImageListPersenter;
import com.chenshun.showgirls.persenter.ImageListPersenterImpl;
import com.chenshun.showgirls.ui.adapter.ViewHolder;
import com.chenshun.showgirls.ui.adapter.recyclerview.CommonAdapter;
import com.chenshun.showgirls.ui.fragment.base.BaseFragment;
import com.chenshun.showgirls.ui.typeImageList.widget.TypeImageActivity;
import com.chenshun.showgirls.ui.view.ImageListView;

import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * User: chenshun <p />
 * Time: 2016/7/8 10:27  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class SWMTFragment extends BaseFragment implements ImageListView, SwipeRefreshLayout.OnRefreshListener
{
    private ImageListPersenter mPersenter;
    private String mType;
    private int mCurrentPage = 1;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private CommonAdapter mAdapter;
    private List<ImageListDomain> mImageListDomains;

    @Override
    protected int getLayoutResource()
    {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData()
    {
        mPersenter = new ImageListPersenterImpl(this);
        switch (getClass().getSimpleName())
        {
            case "NewFragment":
                mType = Constant.NEW;
                break;
            case "XinGanFragment":
                mType = Constant.XINGGAN;
                break;
            case "ShaoNvFragment":
                mType = Constant.SHAONV;
                break;
            case "MRXTFragment":
                mType = Constant.MRXT;
                break;
            case "SWMTFragment":
                mType = Constant.SWMT;
                break;
            case "WMXZFragment":
                mType = Constant.WMXZ;
                break;
            case "WALLPAPERFragment":
                mType = Constant.WALLPAPER;
                break;
            default:
                mType = Constant.NEW;
                break;
        }
        mPersenter.startGetImageList(mType, mCurrentPage);
    }

    @Override
    protected void initView()
    {
        mSwipeRefreshLayout = (SwipeRefreshLayout) context.findViewById(R.id.sw_layout);
        mRecyclerView = (RecyclerView) context.findViewById(R.id.receiverview);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void showLaoding()
    {
        mSwipeRefreshLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading()
    {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadFaild(Exception e)
    {
        if (mCurrentPage != 1)
        {
            Toast.makeText(context, "你太贪心了，已经没有更多图片了", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void receiveImageList(List<ImageListDomain> imageListDomains)
    {
        if (null == imageListDomains || imageListDomains.size() == 0)
        {
            Toast.makeText(context, "没有发现更多数据", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mCurrentPage == 1)
        {
            mImageListDomains = imageListDomains;
            mAdapter = new CommonAdapter<ImageListDomain>(context, R.layout.view_item_fragment_main, mImageListDomains)
            {
                @Override
                public void convert(ViewHolder holder, final ImageListDomain imageListDomain)
                {
                    holder.setText(R.id.tv_title, imageListDomain.getImgaeTitle());
                    holder.setImageWithUrl(R.id.iv_cover, imageListDomain.getImageUrl());
                    holder.setOnClickListener(R.id.iv_cover, new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            Intent intent = new Intent(context, TypeImageActivity.class);
                            intent.putExtra("linkUrl", imageListDomain.getLinkUrl());
                            intent.putExtra("title", imageListDomain.getImgaeTitle());
                            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(context);
                            ActivityCompat.startActivity(context, intent, options.toBundle());
                        }
                    });
                }
            };
            mLayoutManager = new LinearLayoutManager(context);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setItemAnimator(new SlideInLeftAnimator());
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.addOnScrollListener(mOnScrollListener);
        }
        else
        {
            int lastPosition = mImageListDomains.size() - 1;
            mImageListDomains.addAll(imageListDomains);
            mAdapter.notifyItemRangeInserted(lastPosition, imageListDomains.size());
        }
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener()
    {
        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy)
        {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState)
        {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount())
            {
                mCurrentPage++;
                mPersenter.startGetImageList(mType, mCurrentPage);
            }
        }
    };

    @Override
    public void onRefresh()
    {
        mCurrentPage = 1;
        mPersenter.startGetImageList(mType, mCurrentPage);
    }
}

