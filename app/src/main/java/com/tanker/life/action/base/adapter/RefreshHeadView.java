package com.tanker.life.action.base.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.ajguan.library.IRefreshHeader;
import com.ajguan.library.State;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/08
 * @describe : 自定义下拉刷新布局
 */
public class RefreshHeadView extends FrameLayout implements IRefreshHeader {

    public RefreshHeadView(Context context) {
        super(context);
    }

    public RefreshHeadView(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshHeadView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void reset() {

    }

    @Override
    public void pull() {

    }

    @Override
    public void refreshing() {

    }

    @Override
    public void onPositionChange(float v, float v1, float v2, boolean b, State state) {

    }

    @Override
    public void complete() {

    }

}