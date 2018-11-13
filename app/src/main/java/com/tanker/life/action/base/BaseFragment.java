package com.tanker.life.action.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanker.life.action.base.adapter.BaseAdapter;
import com.tanker.life.eventbus.Event;
import com.tanker.life.eventbus.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/08/21
 * @describe : Fragment基类
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    private boolean isVisible;
    private boolean isViewCreated;

    private View fragmentView;
    protected Context thisContext;

    private Unbinder unbinder;
    private ViewHelper viewHelper;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.thisContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(getContentView(), null);
        unbinder = ButterKnife.bind(this, fragmentView);
        viewHelper = new ViewHelper((Activity) thisContext, fragmentView);
        //标记View加载完成
        isViewCreated = true;
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBusUtil.register(this);
        initRecyclerView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusUtil.unregister(this);
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isVisible) {
            isVisible = true;
            initView();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            isVisible = true;
            initView();
        }
    }

    @Override
    public void toast(@StringRes int resId) {
        viewHelper.toast(resId);
    }

    @Override
    public void toast(String text) {
        viewHelper.toast(text);
    }

    @Override
    public ProgressDialog showWaiting(@StringRes int resId) {
        return viewHelper.showWaiting(resId);
    }

    @Override
    public AlertDialog showConfirmation(@StringRes int titleId, @StringRes int messageId, CustomListener.ConfimationOnListener confimationOnListener, @StringRes int negativeId, @StringRes int positiveId) {
        return viewHelper.showConfirmation(titleId, messageId, confimationOnListener, negativeId, positiveId);
    }

    @Override
    public AlertDialog showConfirmation(@StringRes int messageId, CustomListener.ConfimationInfoOnListener confimationInfoOnListener, @StringRes int positiveId) {
        return viewHelper.showConfirmation(messageId, confimationInfoOnListener, positiveId);
    }

    @Override
    public AlertDialog showConfirmation(@StringRes int titleId, @StringRes int messageId, CustomListener.ConfimationInfoOnListener confimationInfoOnListener, @StringRes int positiveId) {
        return viewHelper.showConfirmation(titleId, messageId, confimationInfoOnListener, positiveId);
    }

    @Override
    public void dismiss() {
        viewHelper.dismiss();
    }

    /**
     * 页面跳转
     *
     * @param cls
     */
    protected void toActivity(Class<?> cls) {
        startActivity(new Intent(thisContext, cls));
    }

    protected <T extends View> T findView(@IdRes int resId) {
        return fragmentView.findViewById(resId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStickyEventBusCome(Event event) {
        if (event == null) {
            return;
        }
        receiveEvent(event);
    }

    /**
     * 子类重写执行事件处理
     *
     * @param event
     */
    public void receiveEvent(Event event) {
    }

    /**
     * 初始化RecyclerView（LayoutManager，ItemDecoration）
     */
    private void initRecyclerView() {
        RecyclerView recyclerView = viewHelper.initRecyclerView(getRecyclerViewId(), getRecyclerViewLayoutManager(),getItemDecoration(), getRecyclerViewAdapter());
        if (recyclerView == null) {
            return;
        }
        setRecyclerView(recyclerView);
    }

    /**
     * 设置RecyclerView的拓展属性
     *
     * @param recyclerView
     */
    protected void setRecyclerView(RecyclerView recyclerView) {
    }

    /**
     * layout管理
     *
     * @return
     */
    protected RecyclerView.LayoutManager getRecyclerViewLayoutManager(){
        return new LinearLayoutManager(thisContext,LinearLayoutManager.VERTICAL,false);
    }

    /**
     * 获取RecyclerView适配器
     *
     * @return
     */
    protected BaseAdapter getRecyclerViewAdapter() {
        return null;
    }

    /**
     * 获取RecyclerView控件id
     *
     * @return
     */
    protected int getRecyclerViewId() {
        return View.NO_ID;
    }

    /**
     * 获取分割线
     *
     * @return
     */
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }

    protected abstract int getContentView();

    protected abstract void initView();

}