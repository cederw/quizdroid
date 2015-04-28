package edu.washington.cederw.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class answer extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_a1);
        final Intent intent = getIntent();
        final Intent next4 = new Intent(this, question.class);
        next4.putExtra("total",intent.getIntExtra("total",0));
        next4.putExtra("correct",intent.getIntExtra("correct",0));

        next4.putExtra("type",intent.getStringExtra("type"));
        final Intent next5 = new Intent(this, MainActivity.class);

        ((TextView)findViewById(R.id.user)).setText("You answered: "+intent.getStringExtra("wrong"));
        ((TextView)findViewById(R.id.textView3)).setText("Correct answer: "+intent.getStringExtra("ans"));
        ((TextView)findViewById(R.id.textView4)).setText("You have answered "+intent.getIntExtra("correct", 0)+" out of "+intent.getIntExtra("total",0) + " correct");
        if(intent.getIntExtra("total",0)==4){
            final Button heroB = (Button) findViewById(R.id.button6);
            heroB.setText("Finish");
            heroB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(next5);
                }
            });
        } else {
            final Button heroB = (Button) findViewById(R.id.button6);
            heroB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(next4);
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_math_a1, menu);
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
