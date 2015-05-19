package edu.washington.cederw.quizdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Preferences extends ActionBarActivity {
    private static Button control;
    private static AlarmManager alarmMgr;
    private static PendingIntent alarmIntent;
    private static Preferences instance;

    public Preferences() {
        if (instance == null) {
            instance = this;
        } else {
            Log.e("MainActivity", "There is an error beep boop. You tried to create more than 1 MainActivity");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        control = (Button)findViewById(R.id.button8);
        Log.i("msg","does this even load");
        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizDroid qd = (QuizDroid) getApplication();
                qd.makeIntent(((EditText)findViewById(R.id.editText)).getText().toString(),((EditText)findViewById(R.id.editText2)).getText().toString());
            }
//check that everything is filled out
//                        Log.i("msg","?");
//                        Long time = Long.decode(((EditText)findViewById(R.id.editText2)).getText().toString());
//                        if(time <=0){
//                            throw new IllegalArgumentException("Negative number or 0 entered");
//                        }
//                        Intent intent = new Intent(instance, getQuestions.class);
//                        intent.putExtra("url",((EditText)findViewById(R.id.editText)).getText().toString());
//                        Log.i("msg", ((EditText) findViewById(R.id.editText)).getText().toString() + Long.decode(((EditText) findViewById(R.id.editText2)).getText().toString()));
//                        alarmMgr = (AlarmManager)instance.getSystemService(Context.ALARM_SERVICE);
//                if (alarmIntent != null) {
//                    // Now cancel the alarm that matches the old PendingIntent
//                    alarmMgr.cancel(alarmIntent);
//                }
//                        alarmIntent = PendingIntent.getBroadcast(instance, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                                1000,
//                                time*60000, alarmIntent);
//                    }


        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preferences, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
