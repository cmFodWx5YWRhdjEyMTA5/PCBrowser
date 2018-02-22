package com.arishinfolabs.pcbrowser.fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;

import com.arishinfolabs.pcbrowser.R;
import com.arishinfolabs.pcbrowser.activities.PCBrowserActivity;
import com.arishinfolabs.pcbrowser.webview.PCBrowserWebView;

/**
 * Created by EE207823 on 2/22/2018.
 */

public class BrowserFragment extends Fragment {

    private PCBrowserWebView pcBrowserWebView;
    private EditText browserUrlText;
    private ImageButton addNewTab;
    private PCBrowserActivity mActivity;
    private WebViewClient pcBrowserWebViewClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (PCBrowserActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_browser, container, false);
        loadViews(v);
        return v;
    }

    private void loadViews(View v) {
        pcBrowserWebView = v.findViewById(R.id.browser_web_view);
        browserUrlText = v.findViewById(R.id.browser_url_text);
        addNewTab = v.findViewById(R.id.add_new_browser_tabs);
        pcBrowserWebViewClient = new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                browserUrlText.setText(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        };
        pcBrowserWebView.setWebViewClient(pcBrowserWebViewClient);
        handleLoadUrl();
    }

    private void handleLoadUrl() {
        String url = browserUrlText.getText().toString();
        if (url.startsWith("http://")) {
        } else if (url.startsWith("https://")) {
        } else {
            url = String.format("http://google.com/%s", url);
        }
        pcBrowserWebView.loadUrl(url);
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(browserUrlText.getWindowToken(), 0);
    }
}
