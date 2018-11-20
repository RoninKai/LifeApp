package com.tanker.life.action.main;

import android.Manifest;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.base.view.tabpage.TabPageView;
import com.tanker.life.R;
import com.tanker.life.action.base.BaseActivity;
import com.tanker.life.action.motto.EditMottoActivity;
import com.tanker.life.common.CommonValues;
import com.tanker.life.eventbus.Event;
import com.tanker.life.manager.sharepre.SharePreManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主界面
 *
 * @author Tanker
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_motto)
    TextView tvMotto;
    @BindView(R.id.tpv_main)
    TabPageView tpvMain;

    @OnClick(R.id.tv_motto)
    public void onEditMotto() {
        toActivity(EditMottoActivity.class);
    }

    private List<String> tabNames;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            ViewGroup mContentView = getWindow().findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //fitsSystemWindow 为 false, 不预留系统栏位置.
                ViewCompat.setFitsSystemWindows(mChildView, false);
                ViewCompat.requestApplyInsets(mChildView);
            }
        }
        tpvMain.setPageAdapter(getSupportFragmentManager(),
                tabNames = Arrays.asList(getResources().getStringArray(R.array.main_tab_name_arr)),
                new ArrayList<Fragment>(2) {{
                    add(new MemoFragment());
                    add(new CircumFragment());
//                    add(new SettingFragment());
                }}).setCustomViewInterface(new TabPageView.TabLayoutItemTabCustomViewCreatCallBack() {
            @Override
            public void onCustomView(View tabItemView, int index) {
                ((TextView) tabItemView.findViewById(R.id.tv_tab_name)).setText(tabNames.get(index));
            }
        });
        initMotto();
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
    }

    @Override
    protected void receiveEvent(Event event) {
        super.receiveEvent(event);
        if (event.getCode() == Event.EventCode.UPDATE_MOTTOM_CONTENT) {
            initMotto();
        }
    }

    private void initMotto() {
        String motto = SharePreManager.getInstance().getString(CommonValues.SHAREPRE_SAVE_MOTTO);
        if (!TextUtils.isEmpty(motto)) {
            tvMotto.setText(motto);
        }
    }

}