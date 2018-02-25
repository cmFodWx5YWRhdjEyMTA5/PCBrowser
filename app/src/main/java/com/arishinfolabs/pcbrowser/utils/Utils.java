package com.arishinfolabs.pcbrowser.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.arishinfolabs.pcbrowser.R;

/**
 * Created by EE207823 on 2/26/2018.
 */

public class Utils {

    public static void showAlert(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
    // Add the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        builder.setMessage(R.string.alertMessage);
    // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
