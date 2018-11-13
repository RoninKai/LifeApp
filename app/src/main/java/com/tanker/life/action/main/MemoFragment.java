package com.tanker.life.action.main;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tanker.base.util.DensityUtils;
import com.tanker.life.R;
import com.tanker.life.action.base.BaseFragment;
import com.tanker.life.action.base.adapter.BaseAdapter;
import com.tanker.life.action.base.adapter.GridSpacingItemDecoration;
import com.tanker.life.action.main.adapter.MemoAdapter;
import com.tanker.life.action.motto.AddMemoPopWindow;
import com.tanker.life.common.CommonValues;
import com.tanker.life.db.DBHelper;
import com.tanker.life.eventbus.Event;
import com.tanker.life.manager.imageload.ImageLoadManager;
import com.tanker.life.manager.location.LocationCallBack;
import com.tanker.life.manager.location.LocationManager;
import com.tanker.life.manager.sharepre.SharePreManager;
import com.tanker.life.net.api.WeatherApi;
import com.tanker.life.net.bean.weather.Result;
import com.tanker.life.net.bean.weather.WeatherResult;
import com.tanker.life.net.callback.WeatherCallBack;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/05
 * @describe : 预办页
 */
public class MemoFragment extends BaseFragment {

    @BindView(R.id.tv_wether_info)
    TextView tvWetherInfo;
    @BindView(R.id.iv_current_city_pic)
    ImageView ivCurrentCityPic;

    @OnClick(R.id.fab_add_memo)
    public void onViewClicked(View view) {
//        toActivity(AddMemoActivity.class);
        new AddMemoPopWindow(thisContext).showAsScreenBottom(view);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_memo_layout;
    }

    private MemoAdapter adapter;

    @Override
    protected void initView() {
        ImageLoadManager.load(thisContext, SharePreManager.getInstance().getString(CommonValues.SHAREPRE_ADDRESS_PIC)).into(ivCurrentCityPic);
        LocationManager.getInstance().getLocationCity(thisContext.getApplicationContext(), new LocationCallBack.GetAddressCallBack() {
            @Override
            public void onCallaBack(String cityCode, String cityName) {
                WeatherApi.api().getWeather(cityName, new WeatherCallBack<Result<WeatherResult>>() {
                    @Override
                    public void onSuccess(Result<WeatherResult> result) {
                        WeatherResult weatherResult = result.getResult();
                        tvWetherInfo.setText(weatherResult.toString());
                        ImageLoadManager.load(thisContext, weatherResult.getCurrentCityImage()).into(ivCurrentCityPic);
                        SharePreManager.getInstance().putString(CommonValues.SHAREPRE_ADDRESS_PIC,weatherResult.getCurrentCityImage());
                    }

                    @Override
                    public void onFailure(String errCode, String errorMsg) {

                    }
                });
            }
        });
        initAdapterData();
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.rcv_memo;
    }

    @Override
    protected BaseAdapter getRecyclerViewAdapter() {
        adapter = new MemoAdapter();
        adapter.setEmptyView(getEmptyView());
        return adapter;
    }

    private View getEmptyView() {
        return LinearLayout.inflate(thisContext, R.layout.empty_memo_layout, null);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new GridSpacingItemDecoration(2, DensityUtils.dp2px(thisContext, 20), true);
    }

    @Override
    protected RecyclerView.LayoutManager getRecyclerViewLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        return layoutManager;
    }

    @Override
    public void receiveEvent(Event event) {
        super.receiveEvent(event);
        if (event.getCode() == Event.EventCode.MEMO_CHANGE) {
            initAdapterData();
        }
    }

    private void initAdapterData() {
        adapter.initData(DBHelper.getInstance().getMemo().getMemoData());
    }

}