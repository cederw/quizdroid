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
        qd.questions.get("blah blah idk"); // grab your repository from MyApp and get data from it


        final Intent next = new Intent(this, topicOverview.class);
        final Button math = (Button) findViewById(R.id.button);
        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.putExtra("topic","Math is like numbers and things that look like funny letters.");
                startActivity(next);
            }
        });
        final Button phyB = (Button) findViewById(R.id.button2);
        phyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.putExtra("topic","Physics: math but harder");
                startActivity(next);
            }
        });
        final Button heroB = (Button) findViewById(R.id.button3);
        heroB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.putExtra("topic","Marvel Super Hero's, I don't know anything about them :^)");
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
