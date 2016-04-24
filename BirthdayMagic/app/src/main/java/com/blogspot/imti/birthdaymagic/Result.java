package com.blogspot.imti.birthdaymagic;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Result extends Fragment implements View.OnClickListener{

    static TextView r_tv2;
    Button r_b2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return inflater.inflate(R.layout.fragment_result, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        r_tv2 = (TextView)getActivity().findViewById(R.id.result_tv2);
        r_b2 = (Button)getActivity().findViewById(R.id.result_b1);

        r_b2.setOnClickListener(this);
        birthdayResult(BackupFragmentDataToPassAnotherFragment.getDate());
    }
    public void birthdayResult(String monthName){
        r_tv2.setText(monthName);
    }

    @Override
    public void onClick(View v) {
        getActivity().finish();
    }
}
