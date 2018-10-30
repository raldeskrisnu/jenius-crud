package com.id.jenius.jenius.util;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogBar {

    private ProgressDialog dialog;

    public void ProgressDialogBar(Context ctx){
        dialog = new ProgressDialog(ctx);
        dialog.setIndeterminate(true);
        dialog.show();
    }

    public void HideProgressDialogBar(){
        if(dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
