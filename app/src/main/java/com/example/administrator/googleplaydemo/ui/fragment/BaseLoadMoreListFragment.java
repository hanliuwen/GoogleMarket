package com.example.administrator.googleplaydemo.ui.fragment;

import android.widget.AbsListView;

/**
 * Created by Administrator on 2017/3/29.
 */

//首页,应用,游戏,专题都能够滚动到底部加载更多,都显示一个加载进度条.所以BaseLoadMoreListFragment封装了滚动到列表底部处 加载更多的逻辑
public abstract class BaseLoadMoreListFragment extends BaseListFragment {

    @Override
    protected void initListView() {
        super.initListView();
        //监听ListView滚动,判断是否滚动
        getListView().setOnScrollListener(mOnScrollListener);
    }

    private AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState == SCROLL_STATE_IDLE) {
                //getLastVisiblePosition会将头计算在内,所以getLoadMorePosition方法也加头的个数计算
                if (view.getLastVisiblePosition() == getLoadMorePosition()) {
                    onStartLoadMore();
                }
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    };

    private int getLoadMorePosition() {
        return getAdapter().getCount() - 1 + getListView().getHeaderViewsCount();
    }

    /**
     * 子类来实现加载更多数据的操作
     */
    protected abstract void onStartLoadMore();
}
