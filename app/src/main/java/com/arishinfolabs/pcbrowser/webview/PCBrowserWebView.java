package com.arishinfolabs.pcbrowser.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by EE207823 on 2/22/2018.
 */

public class PCBrowserWebView extends WebView {

    public PCBrowserWebView(Context context) {
        super(context);
        initView();
    }

    public PCBrowserWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        setWebChromeClient(new WebChromeClient());
        WebSettings settings = getSettings();
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setAppCacheEnabled(false);
        settings.setDomStorageEnabled(true);
    }
}
