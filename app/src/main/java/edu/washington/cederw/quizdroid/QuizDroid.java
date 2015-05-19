package edu.washington.cederw.quizdroid;


import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuizDroid extends Application{
    private static QuizDroid instance; // singleton
    public List<topic> topics;
    private static AlarmManager alarmMgr;
    private static PendingIntent alarmIntent;
    //What is "security"
    public static String prefUrl;
    public static Long time;

    public QuizDroid() {
        if (instance == null) {
            instance = this;
        } else {
            Log.e("QuizDroid", "There is an error beep boop. You tried to create more than 1 QuizDroid");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("QuizDroid", "App has been created and is running");
        topics = new ArrayList<topic>();
        String json = null;
        // Fetch data.json in assets/ folder
        try {
            InputStream inputStream = getAssets().open("questions.json");
            Log.i("qq","what is life?");
            json = readJSONFile(inputStream);
            Log.i("dump",json);
            // this is a mess
            JSONArray jArray = new JSONArray(json);
            Log.i("idk",""+jArray.length());//i need an array to naviate hte topics
            for (int i=0; i < jArray.length(); i++) {
                String tempTitle = jArray.getJSONObject(i).getString("title");
                String tempDesc = jArray.getJSONObject(i).getString("desc");
                Log.i("idk",jArray.getJSONObject(i).getString("title"));
                JSONArray qArray = jArray.getJSONObject(i).getJSONArray("questions");
                List<quiz> tempList = new ArrayList<quiz>();
                for (int j=0; j < qArray.length(); j++) {
                    String text = qArray.getJSONObject(j).getString("text");
                    int answer = qArray.getJSONObject(j).getInt("answer");
                    Log.i("idk",qArray.getJSONObject(j).getString("text"));
                    //String[] answers = qArray.getJSONObject(j).getString("answers").split(",");
                   // Log.i("idk",answers.toString());
                    JSONArray answers = qArray.getJSONObject(j).getJSONArray("answers");
                    Log.i("idk",answers.getString(0));
                    tempList.add(new quiz(text, answers.getString(0),answers.getString(1),answers.getString(2),answers.getString(3),answer));
                }
                Log.i("counts",tempList.size()+"");
                topic tempTopic = new topic(tempTitle,tempDesc,tempList, R.drawable.ic_launcher);
                topics.add(tempTopic);
                Log.i("Life","is a journey through life");
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //method can be called from anywhere to set a new url to check
    public void makeIntent(String url, String num){
        Log.i("msg","?");
        prefUrl = url;
        time = Long.decode(num);
        if(time <=0){
            throw new IllegalArgumentException("Negative number or 0 entered");
        }
        Intent intent = new Intent(instance, getQuestions.class);
        intent.putExtra("url",prefUrl);
        alarmMgr = (AlarmManager)instance.getSystemService(Context.ALARM_SERVICE);
        if (alarmIntent != null) {
            // Now cancel the alarm that matches the old PendingIntent
            alarmMgr.cancel(alarmIntent);
        }
        alarmIntent = PendingIntent.getBroadcast(instance, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                1000,
                time*60000, alarmIntent);

    }

    public String readJSONFile(InputStream inputStream) throws IOException {
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        return new String(buffer, "UTF-8");
    }
}
