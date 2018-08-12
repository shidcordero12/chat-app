package com.application.chat.chatapplication.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.application.chat.chatapplication.R;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import dmax.dialog.SpotsDialog;

public class DefaultCallback<T> extends BackendlessCallback<T>
{
    private Context context;
    private AlertDialog alertDialog;

    public DefaultCallback(Context context ) {
        this.context = context;
        alertDialog = new SpotsDialog.Builder()
            .setContext(context)
            .setMessage(R.string.loading)
            .setCancelable(false)
            .build();
        alertDialog.show();
    }

    public DefaultCallback(Context context, String message )
    {
        this.context = context;
        alertDialog = new SpotsDialog.Builder()
                .setContext(context)
                .setMessage(message)
                .setCancelable(false)
                .build();
        alertDialog.show();
    }

    @Override
    public void handleResponse( T response )
    {
        alertDialog.cancel();
    }

    @Override
    public void handleFault( BackendlessFault fault )
    {
        alertDialog.cancel();
        if (fault.getCode().equals(Constants.Common.INTERNET_FAULT_CODE)){
            Toast.makeText( context, Constants.Message.OFFLINE_ERROR, Toast.LENGTH_LONG ).show();
        } else {
            Toast.makeText( context, fault.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }
}


