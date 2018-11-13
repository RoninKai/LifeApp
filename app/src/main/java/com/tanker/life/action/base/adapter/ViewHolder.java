package com.tanker.life.action.base.adapter;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tanker.life.manager.imageload.ImageLoadManager;

/**
 * author : zhoukai
 * e-mail : zhoukai@zto.cn
 * time   : 2018/02/27
 * desc   : RecyclerView 的 ViewHolder
 */
public class ViewHolder extends BaseViewHolder {

    public ViewHolder(View view) {
        super(view);
    }

    /**
     * 加载网络图片
     *
     * @param id
     * @param url
     * @return
     */
    public ViewHolder loadImage(@IdRes int id, String url) {
        ImageView imageView = getView(id);
        Glide.with(imageView.getContext()).load(url).into(imageView);
        return this;
    }

    /**
     * 加载瀑布流网格图片
     *
     * @param id
     * @param url
     * @param itemWidth
     * @param picWidth
     * @param picHeight
     * @return
     */
    public ViewHolder loadStaggeredGridImage(@IdRes int id, String url,float itemWidth, int picWidth, int picHeight) {
        final ImageView imageView = getView(id);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width = (int) itemWidth;
        float scale = (itemWidth + 0f) / picWidth;
        layoutParams.height = (int) (picHeight * scale);
        imageView.setLayoutParams(layoutParams);
        ImageLoadManager.load(imageView.getContext(),url,ImageLoadManager.getOptions(layoutParams.width,layoutParams.height)).into(imageView);
        return this;
    }

    /**
     * 设置控件是否可见
     *
     * @param viewId
     * @param visibility
     * @return
     */
    public ViewHolder setVisibility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }

    /**
     * 设置控件是否可见
     *
     * @param viewId
     * @param isShow
     * @return
     */
    public ViewHolder setVisibility(int viewId, boolean isShow) {
        getView(viewId).setVisibility(isShow ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置TextView显示内容，加非空处理
     *
     * @param viewId
     * @param value
     * @return
     */
    public ViewHolder setText(@IdRes int viewId, String value) {
        if (value == null) {
            return this;
        }
        ((TextView) getView(viewId)).setText(value);
        return this;
    }

}