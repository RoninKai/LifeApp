package com.tanker.life.action.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.tanker.base.util.ActivityCollector;
import com.tanker.base.util.LogUtils;
import com.tanker.life.R;
import com.tanker.life.action.base.adapter.BaseAdapter;
import com.tanker.life.eventbus.Event;
import com.tanker.life.eventbus.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/02
 * @describe : Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity  implements IBaseView {

    protected Activity thisActivity;
    private ViewHelper viewHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        this.thisActivity = this;
        ActivityCollector.addActivity(this);
        ButterKnife.bind(thisActivity);
        viewHelper = new ViewHelper(this);
        EventBusUtil.register(this);
        initSystemBar();
        initToolbar(getTitleText());
        initRecyclerView();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
        ActivityCollector.removeActivity(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuId = getMenuId();
        if (menuId > View.NO_ID) {
            getMenuInflater().inflate(menuId, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        menuSelected(item.getItemId());
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (onBack()) {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
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

    private void initContentView(){
        //方便支持注解设置
        int layoutId = getContentViewId();
        if(layoutId > 0){
            setContentView(layoutId);
        }
    }

    /**
     * 设置状态栏
     */
    private void initSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup mContentView = getWindow().findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, true);
                ViewCompat.requestApplyInsets(mChildView);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    /**
     * 初始化状态栏
     *
     * @param title
     */
    private void initToolbar(String title) {
        try {
            Toolbar toolbar = findViewById(R.id.tb_base_title);
            //设置返回按钮
            toolbar.setNavigationIcon(R.mipmap.back);
            //设置原本的标题为空，使用自定义的标题
            toolbar.setTitle("");
            //设置标题
            if (!TextUtils.isEmpty(title)) {
                ((TextView)findViewById(R.id.tv_toolbar_title)).setText(title);
            }
            //取代原本的actionbar
            setSupportActionBar(toolbar);
            //添加返回点击事件
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        } catch (Exception e) {
            LogUtils.d("No Find Toolbar");
        }
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
    protected void receiveEvent(Event event) {
    }

    /**
     * 获取menu布局id
     *
     * @return
     */
    protected int getMenuId() {
        return View.NO_ID;
    }

    /**
     * menuItem事件触发
     *
     * @param menuId
     */
    protected void menuSelected(int menuId) {
    }

    /**
     * 初始化RecyclerView（LayoutManager，ItemDecoration）
     */
    private void initRecyclerView() {
        RecyclerView recyclerView = viewHelper.initRecyclerView(getRecyclerViewId(),getRecyclerViewLayoutManager(), getItemDecoration(), getRecyclerViewAdapter());
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
     * layout管理
     *
     * @return
     */
    protected RecyclerView.LayoutManager getRecyclerViewLayoutManager(){
        return new LinearLayoutManager(thisActivity);
    }

    /**
     * 获取分割线
     *
     * @return
     */
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }

    /**
     * 重写执行操作
     *
     * @return
     */
    protected boolean onBack() {
        return true;
    }

    /**
     * 页面跳转
     *
     * @param cls
     */
    protected void toActivity(Class<?> cls) {
        startActivity(new Intent(thisActivity, cls));
    }

    protected String getTitleText(){
        return null;
    }

    protected abstract int getContentViewId();

    protected abstract void initView();

}