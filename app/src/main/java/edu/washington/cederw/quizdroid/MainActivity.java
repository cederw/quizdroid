package edu.washington.cederw.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuizDroid qd = (QuizDroid) getApplication();
        qd.makeIntent("dankmeme.website","1");
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
}
