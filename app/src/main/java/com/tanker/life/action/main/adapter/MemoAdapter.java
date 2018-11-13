package com.tanker.life.action.main.adapter;

import com.tanker.life.R;
import com.tanker.life.action.base.adapter.BaseAdapter;
import com.tanker.life.action.base.adapter.ViewHolder;
import com.tanker.life.bean.MemoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/12
 * @describe : 预办的adapter
 */
public class MemoAdapter extends BaseAdapter<MemoBean> {

    public MemoAdapter() {
        super(R.layout.adapter_memo_layout, new ArrayList<MemoBean>());
    }

    @Override
    public void converts(ViewHolder holder, MemoBean memoBean) {
        holder.setText(R.id.tv_title,memoBean.getTitle())
                .setText(R.id.tv_content,memoBean.getContent());
    }

    public void initData(List<MemoBean> list){
        setNewData(list);
    }

}