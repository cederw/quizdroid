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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

        File myFile = new File(getFilesDir().getAbsolutePath(), "/questions.json"); // this string is where you can specify what file you are looking for inside your data/ directory
        String json = null;
// Let's get the JSON in the files directory! (aka data/data.json which is a hidden folder that you can't access or see unless its from the app itself)
// check if data.json file exists in files directory
        if (myFile.exists()) {
            Log.i("QuizDroid", "questions.json DOES exist");
            try {
                FileInputStream fis = openFileInput("questions.json"); // sweet we found it. openFileInput() takes a string path from your data directory. no need to put 'data/' in your path parameter
                json = readJSONFile(fis);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
// Can't find data.json file in files directory. Fetch data.json in assets
            Log.i("QuizDroid", "data.json DOESN'T exist. Fetch from assets");
            try {
                InputStream inputStream = getAssets().open("questions.json");
                json = readJSONFile(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
// DO SOMETHING WITH JSON HERE





        // Fetch data.json in assets/ folder
        try {

            JSONArray jArray = new JSONArray(json);
         //   Log.i("idk",""+jArray.length());//i need an array to naviate hte topics
            for (int i=0; i < jArray.length(); i++) {
                String tempTitle = jArray.getJSONObject(i).getString("title");
                String tempDesc = jArray.getJSONObject(i).getString("desc");
              //  Log.i("idk",jArray.getJSONObject(i).getString("title"));
                JSONArray qArray = jArray.getJSONObject(i).getJSONArray("questions");
                List<quiz> tempList = new ArrayList<quiz>();
                for (int j=0; j < qArray.length(); j++) {
                    String text = qArray.getJSONObject(j).getString("text");
                    int answer = qArray.getJSONObject(j).getInt("answer");
                  //  Log.i("idk",qArray.getJSONObject(j).getString("text"));
                    //String[] answers = qArray.getJSONObject(j).getString("answers").split(",");
                   // Log.i("idk",answers.toString());
                    JSONArray answers = qArray.getJSONObject(j).getJSONArray("answers");
                 //   Log.i("idk",answers.getString(0));
                    tempList.add(new quiz(text, answers.getString(0),answers.getString(1),answers.getString(2),answers.getString(3),answer));
                }
                Log.i("counts",tempList.size()+"");
                topic tempTopic = new topic(tempTitle,tempDesc,tempList, R.drawable.ic_launcher);
                topics.add(tempTopic);
                Log.i("Life","is a journey through life");
            }


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
    //cancle the alarm
    public void stopAlarm(){
        if (alarmIntent != null) {
            // Now cancel the alarm that matches the old PendingIntent
            alarmMgr.cancel(alarmIntent);
        }
    }

    public String readJSONFile(InputStream inputStream) throws IOException {
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        return new String(buffer, "UTF-8");
    }

    public String readJSONFile(FileInputStream inputStream) throws IOException {
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        return new String(buffer, "UTF-8");
    }

    public void writeToFile(String data) {
        try {
            Log.i("QuizDroid", "writing downloaded to file");
            File file = new File(getFilesDir().getAbsolutePath(), "questions.json");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
