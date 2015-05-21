//package edu.washington.cederw.quizdroid;
//
//import android.app.AlertDialog;
//import android.app.DownloadManager;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.database.Cursor;
//import android.os.ParcelFileDescriptor;
//import android.util.Log;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//public class downLoadReceiver extends BroadcastReceiver {
//    private static DownloadManager dm;
//    private long enqueue;
//    public downLoadReceiver() {
//    }
//
//    public void onReceive(Context context, Intent intent) {
//        String action = intent.getAction();
//        dm = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
//        Log.i("MyApp BroadcastReceiver", "onReceive of registered download reciever");
//        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
//            Log.i("MyApp BroadcastReceiver", "download complete!");
//            long downloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
//// if the downloadID exists
//            if (downloadID != 0) {
//// Check status
//                DownloadManager.Query query = new DownloadManager.Query();
//                query.setFilterById(downloadID);
//                Cursor c = dm.query(query);
//                if(c.moveToFirst()) {
//                    int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
//                    Log.d("DM Sample","Status Check: "+status);
//                    switch(status) {
//                        case DownloadManager.STATUS_PAUSED:
//                        case DownloadManager.STATUS_PENDING:
//                        case DownloadManager.STATUS_RUNNING:
//                            break;
//                        case DownloadManager.STATUS_SUCCESSFUL:
//// The download-complete message said the download was "successfu" then run this code
//                            ParcelFileDescriptor file;
//                            StringBuffer strContent = new StringBuffer("");
//                            try {
//// Get file from Download Manager (which is a system service as explained in the onCreate)
//                                file = dm.openDownloadedFile(downloadID);
//                                FileInputStream fis = new FileInputStream(file.getFileDescriptor());
//                                StringBuilder builder = new StringBuilder();
//                                int ch;
//                                while((ch = fis.read()) != -1){
//                                    builder.append((char)ch);
//                                }
//                                ((QuizDroid) context).writeToFile(builder.toString());
//
//
//                            } catch (FileNotFoundException e) {
//                                e.printStackTrace();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            break;
//                        case DownloadManager.STATUS_FAILED:
//                            new AlertDialog.Builder(context)
//                                    .setTitle("Error")
//                                    .setMessage("The download failed")
//                            .setPositiveButton("Retry",new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Log.d( "AlertDialog", "Positive" );
//                                }
//                            })
//                            .setNegativeButton("Quit and try again later",new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Log.d( "AlertDialog", "Positive" );
//                                }
//                            }).show();
//
//
//                            break;
//                    }
//                }
//            }
//        }
//    }
//}
