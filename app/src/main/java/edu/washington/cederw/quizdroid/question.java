package edu.washington.cederw.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class question extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_q1);
        final Intent intent = getIntent();
        final Intent next3 = new Intent(this, answer.class);
        if(intent.getIntExtra("total",0)==3){
            ((Button)findViewById(R.id.button5)).setText("Finish");
        }

        next3.putExtra("total",intent.getIntExtra("total",0)+1);

        next3.putExtra("type",intent.getStringExtra("type"));
        String question = "";
        String one = "";
        String two = "";
        String three = "";
        String four = "";
        int correct = 0;
        if(intent.getStringExtra("type").equals("math")){
            if(intent.getIntExtra("total",0)==0){
                question = "What is 1/1/1/1/1/1/1/1";
                one = "1";
                two = "-1";
                three = "5";
                four = "7";
                correct = 1;
            } else if(intent.getIntExtra("total",0)==1){
                question = "What is 1+1";
                one = "1";
                two = "-1";
                three = "2";
                four = "7";
                correct = 3;
            } else if(intent.getIntExtra("total",0)==2){
                question = "What is 30-30";
                one = "1";
                two = "-1";
                three = "5";
                four = "0";
                correct = 4;
            } else if(intent.getIntExtra("total",0)==3){
                question = "What is 1*6";
                one = "1";
                two = "6";
                three = "5";
                four = "7";
                correct = 2;
            } else if(intent.getIntExtra("total",0)==4){
                question = "What is 100+1";
                one = "1";
                two = "-1";
                three = "101";
                four = "7";
                correct = 3;
            }
        } else if(intent.getStringExtra("type").equals("physics")){
            if(intent.getIntExtra("total",0)==0){
                question = "What is the speed of a car going 60 mph";
                one = "1kph";
                two = "60mph";
                three = "5mph";
                four = "7mph";
                correct = 2;
            } else if(intent.getIntExtra("total",0)==1){
                question = "Where is the Sun";
                one = "The sky";
                two = "The Ground";
                three = "In this phone";
                four = "It doesn't exist";
                correct = 1;
            } else if(intent.getIntExtra("total",0)==2){
                question = "What the velocity of Earth at 0 degrees Kelvin";
                one = "7kph";
                two = "-1kph";
                three = "5kph";
                four = "0kph";
                correct = 4;
            } else if(intent.getIntExtra("total",0)==3){
                question = "Who is a smart guy";
                one = "Albert Einstein";
                two = "Alberto Einstein";
                three = "Halbert Einstein";
                four = "Albert Minestein";
                correct = 1;
            } else if(intent.getIntExtra("total",0)==4){
                question = "What is matter";
                one = "dank memes";
                two = "smoke";
                three = "Not anti-matter";
                four = "stuff?";
                correct = 3;
            }
        } else if(intent.getStringExtra("type").equals("hero")){
            if(intent.getIntExtra("total",0)==0){
                question = "What is Captain America's civilian identity?";
                one = "Steve Rogers";
                two = "Dave";
                three = "John";
                four = "Sean";
                correct = 1;
            } else if(intent.getIntExtra("total",0)==1){
                question = "Who is Peter Parker's first love?";
                one = "Obama";
                two = "McCain";
                three = "Gwen Stacy";
                four = "Palin";
                correct = 3;
            } else if(intent.getIntExtra("total",0)==2){
                question = "Where does the Captain come from";
                one = "Space";
                two = "Also Space";
                three = "Earth";
                four = "America";
                correct = 4;
            } else if(intent.getIntExtra("total",0)==3){
                question = "Who is Marvel";
                one = "Superman";
                two = "Not DC";
                three = "Batman";
                four = "Mario";
                correct = 2;
            } else if(intent.getIntExtra("total",0)==4){
                question = "Is Link a Marvel superhero?";
                one = "Yeahhhhh";
                two = "Yessssss";
                three = "No";
                four = "Yes";
                correct = 3;
            }
        }


        ((TextView)findViewById(R.id.question)).setText(question);
        ((RadioButton)findViewById(R.id.radioButton)).setText(one);
        ((RadioButton)findViewById(R.id.radioButton2)).setText(two);
        ((RadioButton)findViewById(R.id.radioButton3)).setText(three);
        ((RadioButton)findViewById(R.id.radioButton4)).setText(four);
        final String o = one;
        final String t = two;
        final String th = three;
        final String f = four;
        final int cor = correct;
        final RadioButton oneB = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton twoB = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton threeB = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton fourB = (RadioButton) findViewById(R.id.radioButton);
        final Button submit = (Button) findViewById(R.id.button5);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((RadioButton)findViewById(R.id.radioButton)).isChecked()||((RadioButton)findViewById(R.id.radioButton2)).isChecked()||((RadioButton)findViewById(R.id.radioButton3)).isChecked()||((RadioButton)findViewById(R.id.radioButton4)).isChecked()){
                    if(cor == 1&& ((RadioButton)findViewById(R.id.radioButton)).isChecked()){
                        next3.putExtra("correct",intent.getIntExtra("correct",0)+1);
                        next3.putExtra("ans",o);
                        next3.putExtra("wrong",o);
                    } else if(cor == 2&& ((RadioButton)findViewById(R.id.radioButton2)).isChecked()){
                        next3.putExtra("correct",intent.getIntExtra("correct",0)+1);
                        next3.putExtra("ans",t);
                        next3.putExtra("wrong",t);
                    } else if(cor == 3&& ((RadioButton)findViewById(R.id.radioButton3)).isChecked()){
                        next3.putExtra("correct",intent.getIntExtra("correct",0)+1);
                        next3.putExtra("ans",th);
                        next3.putExtra("wrong",th);
                    } else if(cor == 4&& ((RadioButton)findViewById(R.id.radioButton4)).isChecked()){
                        next3.putExtra("correct",intent.getIntExtra("correct",0)+1);
                        next3.putExtra("ans",f);
                        next3.putExtra("wrong",f);
                    } else {
                        next3.putExtra("correct",intent.getIntExtra("correct",0));
                        if(((RadioButton)findViewById(R.id.radioButton)).isChecked()){
                            next3.putExtra("wrong",o);
                        } else if(((RadioButton)findViewById(R.id.radioButton2)).isChecked()){
                            next3.putExtra("wrong",t);
                        } else if(((RadioButton)findViewById(R.id.radioButton3)).isChecked()){
                            next3.putExtra("wrong",th);
                        } else if(((RadioButton)findViewById(R.id.radioButton4)).isChecked()){
                            next3.putExtra("wrong",f);
                        }
                        if(cor == 1){
                            next3.putExtra("ans",o);
                        } else if(cor ==2){
                            next3.putExtra("ans",t);
                        } else if(cor ==3){
                            next3.putExtra("ans",th);
                        } else {
                            next3.putExtra("ans",f);
                        }
                    }
                    startActivity(next3);
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_math_q1, menu);
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
