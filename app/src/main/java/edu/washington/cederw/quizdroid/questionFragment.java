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

import java.util.List;


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
    public topic tempTopic;

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
            List<topic> temp = ((topicOverview) hostActivity).getTopics();
            tempTopic = temp.get(getArguments().getInt("topic"));
            topicText = tempTopic.getDesc();
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
        if(tempTopic.count() == total+1){
            ((Button)getView().findViewById(R.id.button4)).setText("Finish");
        }

        //get and display the data
        String question = tempTopic.getQuestion(total);
        String one = tempTopic.getAnswer1(total);
        String two = tempTopic.getAnswer2(total);
        String three = tempTopic.getAnswer3(total);
        String four = tempTopic.getAnswer4(total);
        int correct = tempTopic.getCorrect(total);



        total++; //i guess i increase the total here
        ((TextView)getView().findViewById(R.id.tq)).setText(question);
        ((RadioButton)getView().findViewById(R.id.radioButton5)).setText(one);
        ((RadioButton)getView().findViewById(R.id.radioButton6)).setText(two);
        ((RadioButton)getView().findViewById(R.id.radioButton7)).setText(three);
        ((RadioButton)getView().findViewById(R.id.radioButton8)).setText(four);

        //lots of final strings to be used in the onclick handler
        final String o = one;
        final String t = two;
        final String th = three;
        final String f = four;
        final int cor = correct;
        final int tot = total;

        final int top = getArguments().getInt("topic");

        /*
        This is a comment, but really its just a warning, this code is bad
        I just check every case and go from the current case.
         */
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
