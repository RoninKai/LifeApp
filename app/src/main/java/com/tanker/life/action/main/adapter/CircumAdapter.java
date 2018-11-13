package com.tanker.life.action.main.adapter;

import android.content.Context;

import com.tanker.base.util.ScreenUtils;
import com.tanker.life.R;
import com.tanker.life.action.base.adapter.BaseAdapter;
import com.tanker.life.action.base.adapter.ViewHolder;
import com.tanker.life.net.bean.baidu.BaiduResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/08
 * @describe : TestAdapter
 */
public class CircumAdapter extends BaseAdapter<BaiduResult.BaiduGirl> {

    private final float width;

    public CircumAdapter(Context context) {
        super(R.layout.adapter_data_item_layout, new ArrayList<BaiduResult.BaiduGirl>());
        this.width = ScreenUtils.getScreenWidth(context) / 2;
    }

    public void initData(List<BaiduResult.BaiduGirl> list) {
        getData().clear();
        CircumAdapter.this.addData(list);
        notifyDataSetChanged();
    }

    @Override
    public void converts(ViewHolder holder, BaiduResult.BaiduGirl baiduGirl) {
        holder.loadStaggeredGridImage(R.id.iv_girl,baiduGirl.getImageUrl(),width ,baiduGirl.getImageWidth(),baiduGirl.getImageHeight());
    }

}