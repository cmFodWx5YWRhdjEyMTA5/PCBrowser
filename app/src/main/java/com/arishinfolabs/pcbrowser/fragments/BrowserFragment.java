package com.arishinfolabs.pcbrowser.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
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
import com.arishinfolabs.pcbrowser.database.FilterData;
import com.arishinfolabs.pcbrowser.utils.Utils;
import com.arishinfolabs.pcbrowser.webview.PCBrowserWebView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish Chaudhary on 2/22/2018.
 */

public class BrowserFragment extends Fragment implements View.OnClickListener, View.OnKeyListener {

    private final String TAG = BrowserFragment.class.getSimpleName();

    private PCBrowserWebView pcBrowserWebView;
    private EditText browserUrlText;
    private ImageButton addNewTab;
    private ImageButton addFilters;
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
        setToolBar();
        return v;
    }

    private void setToolBar() {
        Toolbar tb = mActivity.findViewById(R.id.toolbar);
        mActivity.setSupportActionBar(tb);
    }

    private void loadViews(View v) {
        pcBrowserWebView = v.findViewById(R.id.browser_web_view);
        browserUrlText = v.findViewById(R.id.browser_url_text);
        addNewTab = v.findViewById(R.id.add_new_browser_tabs);
        addFilters = v.findViewById(R.id.add_browser_filters);
        pcBrowserWebViewClient = new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                browserUrlText.setText(url);
                Log.v(TAG, "Browser Url - "+url);
                if (checkForFilteredString(url)) {
                    Utils.showAlert(mActivity);
                    pcBrowserWebView.loadUrl("");
                    browserUrlText.setText("");
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        };
        pcBrowserWebView.setWebViewClient(pcBrowserWebViewClient);
        addFilters.setOnClickListener(this);
        handleLoadUrl();

        browserUrlText.setSelected(false);
        browserUrlText.setOnKeyListener(this);
    }

    private void handleLoadUrl() {
        String url = browserUrlText.getText().toString();

         if (checkForFilteredString(url)) {
             Utils.showAlert(mActivity);
         } else {
             if (url.startsWith("http://")) {
             } else if (url.startsWith("https://")) {
             } else {
                 url = String.format("http://%s", url);
             }
             pcBrowserWebView.loadUrl(url);
             InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
             imm.hideSoftInputFromWindow(browserUrlText.getWindowToken(), 0);
         }
    }

    private boolean checkForFilteredString(String enteredUrl) {

        List<FilterData> filterDataList =  FilterData.getFilterList();
        for (FilterData filterData : filterDataList) {
            if (enteredUrl.contains(filterData.filterString)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_browser_filters :
                mActivity.handleFilterListener();
                break;

            case R.id.add_new_browser_tabs :
                mActivity.handleBrowserListener();
                break;
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            handleLoadUrl();
            return true;
        } else {
            return false;
        }
    }
}
