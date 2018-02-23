package com.arishinfolabs.pcbrowser.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arishinfolabs.pcbrowser.R;
import com.arishinfolabs.pcbrowser.fragments.AddFiltersFragment;
import com.arishinfolabs.pcbrowser.fragments.BrowserFragment;
import com.arishinfolabs.pcbrowser.listeners.CustomListeners;

public class PCBrowserActivity extends AppCompatActivity implements CustomListeners {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private BrowserFragment browserFragment;
    private AddFiltersFragment addFiltersFragment;


    private final String BROWSER_FRAGMENT_STRING = "browser_fragment";
    private final String FILTER_FRAGMENT_STRING = "filter_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcbrowser);
        fragmentManager = getFragmentManager();
        loadBrowser();
    }

    private void loadBrowser() {
        fragmentTransaction = fragmentManager.beginTransaction();
        browserFragment = new BrowserFragment();
        fragmentTransaction.add(R.id.layout_container, browserFragment, BROWSER_FRAGMENT_STRING);
        fragmentTransaction.commit();
    }

    @Override
    public void handleFilterListener() {
        loadFiltersFragment();
    }

    private void loadFiltersFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        addFiltersFragment = new AddFiltersFragment();
        fragmentTransaction.replace(R.id.layout_container, addFiltersFragment, FILTER_FRAGMENT_STRING);
        fragmentTransaction.commit();
    }
}
