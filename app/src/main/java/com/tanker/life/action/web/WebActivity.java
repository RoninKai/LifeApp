package com.tanker.life.action.web;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.tanker.life.R;
import com.tanker.life.action.base.BaseActivity;

import butterknife.BindView;

@SuppressLint("JavascriptInterface")
public class WebActivity extends BaseActivity {

    private static final String WEB_URL = "web_to_load_url";
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void launch(Activity activity, String url) {
        Intent intent = new Intent(activity, WebActivity.class);
        intent.putExtra(WEB_URL, url);
        activity.startActivity(intent);
    }

    @BindView(R.id.wv_load_url)
    WebView wvLoadUrl;
    @BindView(R.id.pb_load_plan)
    ProgressBar pbLoadPlan;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_web;
    }


    @Override
    protected void initView() {
        wvLoadUrl.loadUrl(getIntent().getStringExtra(WEB_URL));
        wvLoadUrl.addJavascriptInterface(new JsonObject(), "android");
        wvLoadUrl.setWebChromeClient(webChromeClient);
        wvLoadUrl.setWebViewClient(webViewClient);
        WebSettings webSettings = wvLoadUrl.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            pbLoadPlan.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            pbLoadPlan.setVisibility(View.VISIBLE);
        }
    };

    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定", null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();

            result.confirm();
            return true;
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            tvTitle.setText(title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            pbLoadPlan.setProgress(newProgress);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (wvLoadUrl.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            wvLoadUrl.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wvLoadUrl.destroy();
        wvLoadUrl = null;
    }

}