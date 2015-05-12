package edu.washington.cederw.quizdroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class topicOverview extends ActionBarActivity implements pickATopic.OnFragmentInteractionListener{
    public QuizDroid qd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_overview);
        Intent intent = getIntent();
        qd = (QuizDroid) getApplication();
        FragmentManager fragmentManager = getFragmentManager();
        Fragment pickedTopic = new pickATopic();
        pickedTopic.setArguments(intent.getExtras());

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainLayout, pickedTopic);
        transaction.addToBackStack(null);
        transaction.commit();



    }

    public void loadQFrag(int total, int correct, int type){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle topicBundle = new Bundle();
        topicBundle.putInt("total", total);
        topicBundle.putInt("correct", correct);
        topicBundle.putInt("topic", type);
        questionFragment qFragment = new questionFragment();
        qFragment.setArguments(topicBundle);
        ft.replace(R.id.mainLayout, qFragment); // where , what
        ft.commit();
    }
    public void loadAFrag(int total, int correct, int type, String corr, String wrong){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle topicBundle = new Bundle();
        topicBundle.putInt("total", total);
        topicBundle.putInt("correct", correct);
        topicBundle.putInt("topic", type);
        topicBundle.putString("corr", corr);
        topicBundle.putString("wrong", wrong);
        answerFragment aFragment = new answerFragment();
        aFragment.setArguments(topicBundle);
        ft.replace(R.id.mainLayout, aFragment); // where , what
        ft.commit();
    }

    //my best method, it gives the data to the fragments
    public List getTopics(){
        return qd.topics;
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
    @Override
    public void onFragmentInteraction(Button button){

    }

}
