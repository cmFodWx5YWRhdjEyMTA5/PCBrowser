package com.arishinfolabs.pcbrowser.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arishinfolabs.pcbrowser.R;
import com.arishinfolabs.pcbrowser.fragments.AddFiltersFragment;
import com.arishinfolabs.pcbrowser.fragments.BrowserFragment;
import com.arishinfolabs.pcbrowser.listeners.ConnectivityReceiverListener;
import com.arishinfolabs.pcbrowser.listeners.CustomListeners;
import com.arishinfolabs.pcbrowser.receivers.ConnectivityReceiver;
import com.arishinfolabs.pcbrowser.utils.Utils;

import java.util.List;

public class PCBrowserActivity extends AppCompatActivity implements CustomListeners, ConnectivityReceiverListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private BrowserFragment browserFragment;
    private AddFiltersFragment addFiltersFragment;
    private ConnectivityReceiver connectivityReceiver;


    private final String BROWSER_FRAGMENT_STRING = "browser_fragment";
    private final String FILTER_FRAGMENT_STRING = "filter_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcbrowser);
        fragmentManager = getFragmentManager();
        loadBrowser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerConnectivityReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        deRegisterConnectivityReceiver();
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

    @Override
    public void handleBrowserListener() {
        loadBrowser();
    }

    private void loadFiltersFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        addFiltersFragment = new AddFiltersFragment();
        fragmentTransaction.replace(R.id.layout_container, addFiltersFragment, FILTER_FRAGMENT_STRING);
        fragmentTransaction.addToBackStack(FILTER_FRAGMENT_STRING);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void registerConnectivityReceiver() {
        connectivityReceiver = new ConnectivityReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectivityReceiver, intentFilter);
    }

    private void deRegisterConnectivityReceiver() {
        unregisterReceiver(connectivityReceiver);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            Utils.showAlert(this, "Turn on internet to browse");
        }
    }
}
