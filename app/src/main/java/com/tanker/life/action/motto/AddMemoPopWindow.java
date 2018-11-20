package com.tanker.life.action.motto;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tanker.base.util.ScreenUtils;
import com.tanker.life.R;
import com.tanker.life.action.base.BasePopWindow;
import com.tanker.life.db.DBHelper;
import com.tanker.life.eventbus.Event;
import com.tanker.life.eventbus.EventBusUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : Tanker
 * @email :zhoukai@zto.cn
 * @date : 2018/11/12
 * @describe : 添加待办事项
 */
public class AddMemoPopWindow extends BasePopWindow {

    @BindView(R.id.et_motto_title)
    EditText etMottoTitle;
    @BindView(R.id.et_motto_content)
    EditText etMottoContent;

    @OnClick(R.id.bt_memo_save)
    void onViewClicked() {
        String title = etMottoTitle.getText().toString();
        String content = etMottoContent.getText().toString();
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
            return;
        }
        DBHelper.getInstance().getMemo().addMemo(title, content);
        EventBusUtil.sendEvent(new Event(Event.EventCode.MEMO_CHANGE));
        dismiss();
    }

    public AddMemoPopWindow(Context context) {
        super(context);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.pop_add_memo_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getWidthSize() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    @Override
    public int getHeightSize() {
        return ScreenUtils.getScreenHeight(mContext) / 10 * 7;
    }

    @Override
    protected int getAnimaStyle() {
        return R.style.pop_down_to_up_anim;
    }

    @Override
    public void showAsScreenBottom(View parent) {
        super.showAsScreenBottom(parent);
        modifyScreenBrightness(0.2f);
    }

    @Override
    public void onDismiss() {
        super.onDismiss();
        modifyScreenBrightness(1.0f);
    }

}