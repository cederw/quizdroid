package edu.washington.cederw.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Choice extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);


        Button retry = (Button)findViewById(R.id.button9);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //retry
                QuizDroid qd = (QuizDroid) getApplication();
                //its awkward because the method calls for a string which it then turns into a long
                qd.makeIntent(qd.prefUrl,qd.time.toString());
            }
        });

        Button quit = (Button)findViewById(R.id.button10);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //quit
                //What  i do is stop the alarm and move to the home screen.
                //In android it is the phones job to manage memory and terminate apps
                //so i think this a good solution
                QuizDroid qd = (QuizDroid)getApplication();
                qd.stopAlarm();

                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choice, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
