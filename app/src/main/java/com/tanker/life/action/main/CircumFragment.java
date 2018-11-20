package com.tanker.life.action.main;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tanker.life.R;
import com.tanker.life.action.base.BaseFragment;
import com.tanker.life.action.base.adapter.BaseAdapter;
import com.tanker.life.action.main.adapter.CircumAdapter;
import com.tanker.life.action.web.WebActivity;
import com.tanker.life.net.api.BaiduApi;
import com.tanker.life.net.bean.baidu.BaiduResult;
import com.tanker.life.view.SimpleRefreshHeader2View;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/05
 * @describe : 周边功能
 */
public class CircumFragment extends BaseFragment {

    @BindView(R.id.erl_refresh_data)
    EasyRefreshLayout erlRefreshData;

    @Override
    protected int getContentView() {
        return R.layout.fragment_circum_layout;
    }

    private CircumAdapter adapter;

    @Override
    protected void initView() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WebActivity.launch((Activity) thisContext, "https://www.baidu.com/");
            }
        });
        erlRefreshData.setLoadMoreModel(LoadModel.NONE);
        erlRefreshData.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {

            }

            @Override
            public void onRefreshing() {
                getGril();
            }
        });
        erlRefreshData.setRefreshHeadView(new SimpleRefreshHeader2View(thisContext));
        getGril();
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.rcv_data;
    }

    @Override
    protected BaseAdapter getRecyclerViewAdapter() {
        return adapter = new CircumAdapter(thisContext);
    }

    @Override
    protected RecyclerView.LayoutManager getRecyclerViewLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        return layoutManager;
    }

    private void getGril() {
        BaiduApi.api().getBaiduGirl("美女", "全部")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BaiduResult>() {
                    @Override
                    public void onNext(BaiduResult result) {
                        List<BaiduResult.BaiduGirl> list = result.getImageData();
                        adapter.initData(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        erlRefreshData.refreshComplete();
                    }
                });
    }

}