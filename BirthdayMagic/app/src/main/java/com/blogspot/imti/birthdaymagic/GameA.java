package com.blogspot.imti.birthdaymagic;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameA extends Fragment implements View.OnClickListener{
    TextView a_tv1, a_tv2;
    EditText a_et1;
    Button backButton, nextButton;
    int stepClickCounter;

    int dateNumber, temp, day, month;
    int dayArray[];
    String monthName;

    Communicator communicator;

    String [] stepDescriptions = {"Multiply month number by 5\n(For example: month number is 1 for January, 12 for December and so on).",
            "Add 6 with the result.",
            "Multiply the result by 4.",
            "Add 9 with the result.",
            "Multiply the result by 5 again.",
            "Add the Day number on which you were born.",
            "Ask your friend to give you the final result.",
            "Enter the result below and hit on BIRTHDAY."};
    String stepMessage, stepText, resultText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        if(savedInstanceState == null){
            stepClickCounter = 0;
            stepMessage = "Multiply month number by 5\n(For example: month number is 1 for January, 12 for December and so on).";
            stepText = "STEP A";
        }
        else{
            stepClickCounter = savedInstanceState.getInt("stepCounter");
            stepMessage = (String)savedInstanceState.get("stepDescription");
            stepText = (String)savedInstanceState.get("stepText");
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        a_tv1 = (TextView)getActivity().findViewById(R.id.gamea_tv1);
        a_tv2 = (TextView)getActivity().findViewById(R.id.gamea_tv2);
        backButton = (Button)getActivity().findViewById(R.id.gamea_b1);
        nextButton = (Button)getActivity().findViewById(R.id.gamea_b2);
        a_et1 = (EditText)getActivity().findViewById(R.id.gamea_et1);

        communicator = (Communicator)getActivity();//typecast the activity to communicator

        //upto final step, I hide edit text
        a_et1.setEnabled(false);
        a_et1.setInputType(InputType.TYPE_NULL);
        a_et1.setFocusable(false);

        a_tv2.setText(stepMessage);
        a_tv1.setText(stepText);
        backButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == nextButton) {
            stepClickCounter++;
            goSetStep();
        }else if(v == backButton){
            if(stepClickCounter == 0){
                getActivity().finish();
            }else if(stepClickCounter >= 7){
                stepClickCounter = 6;
                a_et1.setHint("");
                a_et1.setEnabled(false);
                a_et1.setInputType(InputType.TYPE_NULL);
                a_et1.setFocusable(false);
                nextButton.setText("NEXT STEP");
                goSetStep();
            }else{
                stepClickCounter--;
                goSetStep();
            }
        }
    }
    public void goSetStep(){
        if (stepClickCounter==0) {
            stepMessage = stepDescriptions[0];
            a_tv1.setText("STEP A");
            a_tv2.setText(stepMessage);

        }else if (stepClickCounter==1) {
            stepMessage = stepDescriptions[1];
            a_tv1.setText("STEP B");
            a_tv2.setText(stepMessage);

        }else if (stepClickCounter==2) {
            stepMessage = stepDescriptions[2];
            a_tv1.setText("STEP C");
            a_tv2.setText(stepMessage);

        }else if (stepClickCounter==3) {
            stepMessage = stepDescriptions[3];
            a_tv1.setText("STEP D");
            a_tv2.setText(stepMessage);

        }else if (stepClickCounter==4) {
            stepMessage = stepDescriptions[4];
            a_tv1.setText("STEP E");
            a_tv2.setText(stepMessage);

        }else if (stepClickCounter==5) {
            stepMessage = stepDescriptions[5];
            a_tv1.setText("STEP F");
            a_tv2.setText(stepMessage);

        }else if (stepClickCounter==6) {
            stepMessage = stepDescriptions[6];
            a_tv1.setText("STEP G");
            a_tv2.setText(stepMessage);

        }else if (stepClickCounter==7) {
            stepMessage = stepDescriptions[7];
            a_tv1.setText("FINAL STEP");
            a_tv2.setText(stepMessage);

            a_et1.setHint("Final Result Here");
            //final step, so edittext appear
            a_et1.setEnabled(true);
            a_et1.setInputType(InputType.TYPE_CLASS_TEXT);
            a_et1.setFocusableInTouchMode(true);
            a_et1.setFocusable(true);

            nextButton.setText("BIRTHDAY");

        }else {

            try{
                dateNumber = Integer.parseInt(a_et1.getText().toString());
            }catch (NumberFormatException e){
                //Toast.makeText(getActivity(), "Enter valid result", Toast.LENGTH_LONG).show();
            }
            if(dateNumber<266 || dateNumber>1396){
                Toast.makeText(getActivity(), "Invalid calculations or date", Toast.LENGTH_LONG).show();
            }else{
                dayArray = new int[2];
                dateNumber -= 165;
                for(int i = 1; i >= 0; i--){
                    temp = dateNumber%10;
                    dayArray[i] = temp;
                    dateNumber /= 10;
                }
                day = dayArray[0]*10 + dayArray[1];
                if(day<1 || day>31){
                    Toast.makeText(getActivity(), "Invalid calculations or date", Toast.LENGTH_LONG).show();
                }else{
                    month = dateNumber;
                    monthName = getMonthName(month);
                    a_et1.setText("");

                    BackupFragmentDataToPassAnotherFragment.setDate(day+" "+monthName);

                    communicator.communicateResponse();
                }
            }
        }
    }

    public String getMonthName(int month){
        switch(month){
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "";
        }
    }

    //if screen rotate or something that make fragment destroy and reset,
    //before destroy this method called and save the value
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("stepCounter", stepClickCounter);
        //outState.putString("stepText", st);
        outState.putString("stepDescription", stepMessage);
        outState.putString("stepText", a_tv1.getText().toString());

    }

}
