package com.arishinfolabs.pcbrowser.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.arishinfolabs.pcbrowser.listeners.ConnectivityReceiverListener;

/**
 * Created by EE207823 on 2/26/2018.
 */

public class ConnectivityReceiver extends BroadcastReceiver {

    public ConnectivityReceiverListener connectivityReceiverListener;

    public ConnectivityReceiver(ConnectivityReceiverListener listener) {
        super();
        connectivityReceiverListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent arg1) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        Log.v("Connectivity Receiver", "Network State Called :- "+isConnected);
        if (connectivityReceiverListener != null) {
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }
    }
}
