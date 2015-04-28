package edu.washington.cederw.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class topicOverview extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_overview);
        Intent intent = getIntent();
        ((TextView)findViewById(R.id.topic)).setText(intent.getStringExtra("topic"));
        final Intent next2 = new Intent(this, question.class);
        final Button start = (Button) findViewById(R.id.button4);
         String type = "";
        if(intent.getStringExtra("topic").equals("Math is like numbers and things that look like funny letters.")){
            type = "math";
        }
        else if(intent.getStringExtra("topic").equals("Physics: math but harder")){
            type = "physics";
        } else {
            type="hero";
        }
        next2.putExtra("total",0);
        next2.putExtra("correct",0);
        next2.putExtra("type",type);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(next2);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_overview, menu);
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
