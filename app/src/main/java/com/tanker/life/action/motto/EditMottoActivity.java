package com.tanker.life.action.motto;

import android.text.TextUtils;
import android.widget.EditText;

import com.tanker.life.R;
import com.tanker.life.action.base.BaseActivity;
import com.tanker.life.common.CommonValues;
import com.tanker.life.eventbus.Event;
import com.tanker.life.eventbus.EventBusUtil;
import com.tanker.life.manager.sharepre.SharePreManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑座右铭的页面
 *
 * @author zhoukai
 */
public class EditMottoActivity extends BaseActivity {

    @BindView(R.id.et_motto_input)
    EditText etMottoInput;

    @OnClick(R.id.bt_motto_save)
    public void onSaveMotto() {
        String motto = etMottoInput.getText().toString().trim();
        if (!TextUtils.isEmpty(motto)) {
            SharePreManager.getInstance().putString(CommonValues.SHAREPRE_SAVE_MOTTO, motto);
            EventBusUtil.sendEvent(new Event(Event.EventCode.UPDATE_MOTTOM_CONTENT));
        }
        finish();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_edit_motto;
    }

    @Override
    protected void initView() {

    }

}