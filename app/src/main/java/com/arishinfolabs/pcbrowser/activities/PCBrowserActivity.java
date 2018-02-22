package com.arishinfolabs.pcbrowser.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arishinfolabs.pcbrowser.R;
import com.arishinfolabs.pcbrowser.fragments.BrowserFragment;

public class PCBrowserActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private BrowserFragment browserFragment;


    private final String BROWSER_FRAGMENT_STRING = "browser_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcbrowser);
        loadBrowser();
    }

    private void loadBrowser() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        browserFragment = new BrowserFragment();
        fragmentTransaction.add(R.id.layout_container, browserFragment, BROWSER_FRAGMENT_STRING);
        fragmentTransaction.commit();
    }
}
