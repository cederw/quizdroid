package edu.washington.cederw.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link pickATopic.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link pickATopic#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pickATopic extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private static String topicText;

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

    public pickATopic() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topicText = "dankmeme.website";
        if (getArguments() != null) {
           topicText = getArguments().getString("topic");
           //mParam2 = getArguments().getString(ARG_PARAM2);
            Log.i("TopicFrag", "Awkward");
        }


        /*
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
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_pick_atopic, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ((TextView) getView().findViewById(R.id.meme)).setText(topicText);
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
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
