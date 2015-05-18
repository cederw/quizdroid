package edu.washington.cederw.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class getQuestions extends BroadcastReceiver {
    public getQuestions() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
// TODO: This method is called when the BroadcastReceiver is receiving
// an Intent broadcast.
        Log.i("uhh", "got it");
        String message = intent.getStringExtra("url");
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
