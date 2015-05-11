package edu.washington.cederw.quizdroid;


import android.app.Application;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class QuizDroid extends Application{
    private static QuizDroid instance; // singleton
    HashMap<String, Integer> questions;

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
        this.questions = new HashMap<String, Integer>();
        String json = null;
        // Fetch data.json in assets/ folder
        try {
            InputStream inputStream = getAssets().open("questions.json");
            json = readJSONFile(inputStream);
            // this is a mess
            JSONArray jArray = new JSONArray(json); //i need an array to naviate hte topics
            for (int i=0; i < jArray.length(); i++) {
                JSONArray qArray = jArray.getJSONArray(i);
                for (int j=0; i < qArray.length(); j++) {
                    JSONObject q = qArray.getJSONObject(j);

                }
            }

            JSONObject jsonData = new JSONObject(json);
        // get the questions from the file

            String questionString = jsonData.getString("text");
            int answer = jsonData.getInt("answer");
            this.questions.put(questionString, answer); // populate your repository
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String readJSONFile(InputStream inputStream) throws IOException {
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        return new String(buffer, "UTF-8");
    }
}
