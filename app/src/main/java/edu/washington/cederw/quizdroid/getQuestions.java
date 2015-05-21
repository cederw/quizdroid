package edu.washington.cederw.quizdroid;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.client.methods.HttpGet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

        try
        {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            cm.getActiveNetworkInfo().isConnectedOrConnecting();

            String message = intent.getStringExtra("url");

            dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(message));
            //request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "questions.json");
            enqueue = dm.enqueue(request);
        }
        catch (Exception e)
        {
            Log.e("Broadcast Reciver",e.toString());
            Toast.makeText(context, "It seems you are offline :(", Toast.LENGTH_LONG).show();
            if(Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0){
                Intent intent2 = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS );
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            }
        }

        //Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
