package edu.washington.cederw.quizdroid;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



public class MainActivity extends ActionBarActivity {

    private static DownloadManager dm;
    private long enqueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE); // Add more filters here that you want the receiver to listen to
        registerReceiver(receiver, filter);
        QuizDroid qd = (QuizDroid) getApplication();
        qd.makeIntent("http://tednewardsandbox.site44.com/questions.json","1");
        /*
        Get the buttons, add text and pictures to them

         */

        final Intent next = new Intent(this, topicOverview.class);
        final Button math = (Button) findViewById(R.id.button);
        math.setText(qd.topics.get(0).getTitle()+" - "+qd.topics.get(0).getDesc());
        math.setCompoundDrawablesWithIntrinsicBounds(qd.topics.get(0).draw(),0,0,0);
        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.putExtra("topic", 0);
                startActivity(next);
            }
        });
        final Button phyB = (Button) findViewById(R.id.button2);
        phyB.setText(qd.topics.get(1).getTitle()+" - "+qd.topics.get(1).getDesc());
        phyB.setCompoundDrawablesWithIntrinsicBounds(qd.topics.get(1).draw(),0,0,0);
        phyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.putExtra("topic",1);
                startActivity(next);
            }
        });
        final Button heroB = (Button) findViewById(R.id.button3);
        heroB.setText(qd.topics.get(2).getTitle()+" - "+qd.topics.get(2).getDesc());
        heroB.setCompoundDrawablesWithIntrinsicBounds(qd.topics.get(2).draw(),0,0,0);
        heroB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.putExtra("topic",2);
                startActivity(next);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.preferences) {
            Intent intent = new Intent(this, Preferences.class);
            startActivity(intent);
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            dm = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
            Log.i("MyApp BroadcastReceiver", "onReceive of registered download reciever");
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                Log.i("MyApp BroadcastReceiver", "download complete!");
                long downloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
// if the downloadID exists
                if (downloadID != 0) {
// Check status
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(downloadID);
                    Cursor c = dm.query(query);
                    if(c.moveToFirst()) {
                        int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                        Log.d("DM Sample","Status Check: "+status);
                        switch(status) {
                            case DownloadManager.STATUS_PAUSED:
                            case DownloadManager.STATUS_PENDING:
                            case DownloadManager.STATUS_RUNNING:
                                break;
                            case DownloadManager.STATUS_SUCCESSFUL:
// The download-complete message said the download was "successfu" then run this code
                                ParcelFileDescriptor file;
                                StringBuffer strContent = new StringBuffer("");
                                try {
// Get file from Download Manager (which is a system service as explained in the onCreate)
                                    file = dm.openDownloadedFile(downloadID);
                                    FileInputStream fis = new FileInputStream(file.getFileDescriptor());
                                    //build a string out of the file
                                    StringBuilder builder = new StringBuilder();
                                    int ch;
                                    while((ch = fis.read()) != -1){
                                        builder.append((char)ch);
                                    }
                                    //write the file
                                    ((QuizDroid)getApplication()).writeToFile(builder.toString());


                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case DownloadManager.STATUS_FAILED:
                                Log.e("QuizDroid","Download Failed");
                                Intent choice = new Intent(context, Choice.class);
                                startActivity(choice);



                                break;
                        }
                    }
                }
            }
        }

    };
}
