package edu.washington.cederw.quizdroid;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.client.methods.HttpGet;

public class getQuestions extends BroadcastReceiver {
    private static DownloadManager dm;
    private long enqueue;
    public getQuestions() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
// TODO: This method is called when the BroadcastReceiver is receiving
// an Intent broadcast.
        Log.i("uhh", "got it");
        String message = intent.getStringExtra("url");

        dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(message));
        enqueue = dm.enqueue(request);
        //Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
