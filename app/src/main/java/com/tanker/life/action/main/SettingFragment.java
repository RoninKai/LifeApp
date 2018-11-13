package com.tanker.life.action.main;

import android.widget.ImageView;

import com.tanker.life.R;
import com.tanker.life.action.base.BaseFragment;

import butterknife.BindView;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/05
 * @describe : 设置页
 */
public class SettingFragment extends BaseFragment {

    @BindView(R.id.iv_pic)
    ImageView ivPic;

    @Override
    protected int getContentView() {
        return R.layout.fragment_setting_layout;
    }

    @Override
    protected void initView() {

    }

}