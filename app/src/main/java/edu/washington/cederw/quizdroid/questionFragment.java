package edu.washington.cederw.quizdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link pickATopic.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link pickATopic#newInstance} factory method to
 * create an instance of this fragment.
 */
public class questionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private  String topicText;
    private  int total;
    private  int correctQ;
    private Activity hostActivity;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment pickATopic.
     */
    // TODO: Rename and change types and number of parameters
    public static pickATopic newInstance(TextView t, String tt) {
        pickATopic fragment = new pickATopic();
        //  Bundle args = new Bundle();
        //  args.putString("tText", tt);
        //  fragment.setArguments(args);
        return fragment;
    }

    public questionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topicText = "dankmeme.website";
        if (getArguments() != null) {
            topicText = getArguments().getString("topic");
            total = getArguments().getInt("total");
            correctQ = getArguments().getInt("correct");
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_question, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(total==3){
            ((Button)getView().findViewById(R.id.button4)).setText("Finish");
        }


        String question = "";
        String one = "";
        String two = "";
        String three = "";
        String four = "";
        int correct = 0;
        if(topicText.equals("math")){
            if(total==0){
                question = "What is 1/1/1/1/1/1/1/1";
                one = "1";
                two = "-1";
                three = "5";
                four = "7";
                correct = 1;
            } else if(total==1){
                question = "What is 1+1";
                one = "1";
                two = "-1";
                three = "2";
                four = "7";
                correct = 3;
            } else if(total==2){
                question = "What is 30-30";
                one = "1";
                two = "-1";
                three = "5";
                four = "0";
                correct = 4;
            } else if(total==3){
                question = "What is 1*6";
                one = "1";
                two = "6";
                three = "5";
                four = "7";
                correct = 2;
            } else if(total==4){
                question = "What is 100+1";
                one = "1";
                two = "-1";
                three = "101";
                four = "7";
                correct = 3;
            }
        } else if(topicText.equals("physics")){
            if(total==0){
                question = "What is the speed of a car going 60 mph";
                one = "1kph";
                two = "60mph";
                three = "5mph";
                four = "7mph";
                correct = 2;
            } else if(total==1){
                question = "Where is the Sun";
                one = "The sky";
                two = "The Ground";
                three = "In this phone";
                four = "It doesn't exist";
                correct = 1;
            } else if(total==2){
                question = "What the velocity of Earth at 0 degrees Kelvin";
                one = "7kph";
                two = "-1kph";
                three = "5kph";
                four = "0kph";
                correct = 4;
            } else if(total==3){
                question = "Who is a smart guy";
                one = "Albert Einstein";
                two = "Alberto Einstein";
                three = "Halbert Einstein";
                four = "Albert Minestein";
                correct = 1;
            } else if(total==4){
                question = "What is matter";
                one = "dank memes";
                two = "smoke";
                three = "Not anti-matter";
                four = "stuff?";
                correct = 3;
            }
        } else if(topicText.equals("hero")){
            if(total==0){
                question = "What is Captain America's civilian identity?";
                one = "Steve Rogers";
                two = "Dave";
                three = "John";
                four = "Sean";
                correct = 1;
            } else if(total==1){
                question = "Who is Peter Parker's first love?";
                one = "Obama";
                two = "McCain";
                three = "Gwen Stacy";
                four = "Palin";
                correct = 3;
            } else if(total==2){
                question = "Where does the Captain come from";
                one = "Space";
                two = "Also Space";
                three = "Earth";
                four = "America";
                correct = 4;
            } else if(total==3){
                question = "Who is Marvel";
                one = "Superman";
                two = "Not DC";
                three = "Batman";
                four = "Mario";
                correct = 2;
            } else if(total==4){
                question = "Is Link a Marvel superhero?";
                one = "Yeahhhhh";
                two = "Yessssss";
                three = "No";
                four = "Yes";
                correct = 3;
            }
        }


        total++;
        View v = getView();
        ((TextView)getView().findViewById(R.id.tq)).setText(question);
        ((RadioButton)getView().findViewById(R.id.radioButton5)).setText(one);
        ((RadioButton)getView().findViewById(R.id.radioButton6)).setText(two);
        ((RadioButton)getView().findViewById(R.id.radioButton7)).setText(three);
        ((RadioButton)getView().findViewById(R.id.radioButton8)).setText(four);
        final String o = one;
        final String t = two;
        final String th = three;
        final String f = four;
        final int cor = correct;
        final int tot = total;

        final String top = topicText;

        final Button submit = (Button) getView().findViewById(R.id.button4);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wrong = "";
                String right = "";
                if(((RadioButton)getView().findViewById(R.id.radioButton5)).isChecked()||((RadioButton)getView().findViewById(R.id.radioButton6)).isChecked()||((RadioButton)getView().findViewById(R.id.radioButton7)).isChecked()||((RadioButton)getView().findViewById(R.id.radioButton8)).isChecked()){
                    if(cor == 1&& ((RadioButton)getView().findViewById(R.id.radioButton5)).isChecked()){
                       Log.i("qq","wtf");
                        if (hostActivity instanceof topicOverview) {

                            ((topicOverview) hostActivity).loadAFrag(tot, correctQ + 1, top, o, o);
                        }

                    } else if(cor == 2&& ((RadioButton)getView().findViewById(R.id.radioButton6)).isChecked()){

                        if (hostActivity instanceof topicOverview) {

                            ((topicOverview) hostActivity).loadAFrag(tot,correctQ+1,top,t,t);
                        }
                    } else if(cor == 3&& ((RadioButton)getView().findViewById(R.id.radioButton7)).isChecked()){

                        if (hostActivity instanceof topicOverview) {

                            ((topicOverview) hostActivity).loadAFrag(tot,correctQ+1,top,th,th);
                        }
                    } else if(cor == 4&& ((RadioButton)getView().findViewById(R.id.radioButton8)).isChecked()){

                        if (hostActivity instanceof topicOverview) {

                            ((topicOverview) hostActivity).loadAFrag(tot,correctQ+1,top,f,f);
                        }
                    } else {

                        if(((RadioButton)getView().findViewById(R.id.radioButton5)).isChecked()){
                            wrong = o;
                        } else if(((RadioButton)getView().findViewById(R.id.radioButton6)).isChecked()){
                            wrong = t;
                        } else if(((RadioButton)getView().findViewById(R.id.radioButton7)).isChecked()){
                            wrong  =th;
                        } else if(((RadioButton)getView().findViewById(R.id.radioButton8)).isChecked()){
                            wrong = f;
                        }
                        if(cor == 1){
                            right = o;
                        } else if(cor ==2){
                            right = t;
                        } else if(cor ==3){
                            right = th;
                        } else {
                            right = f;
                        }
                        if (hostActivity instanceof topicOverview) {

                            ((topicOverview) hostActivity).loadAFrag(tot,correctQ,top,right,wrong);
                        }
                    }

                }

            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Button button) {
        if (mListener != null) {
            mListener.onFragmentInteraction(button);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.hostActivity = activity;
       /* try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Button button);
    }

}
