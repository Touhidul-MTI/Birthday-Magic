package com.blogspot.imti.birthdaymagic;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentHolder extends AppCompatActivity implements Communicator{

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        GameA gamea = new GameA();
        fragmentTransaction.add(R.id.holder_rl1, gamea, "gameATag");
        fragmentTransaction.commit();
    }
    public void communicateResponse(){

        FragmentManager fragmentManager2 = getFragmentManager();
        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
        Result result = new Result();
        fragmentTransaction2.replace(R.id.holder_rl1, result,"result");

        fragmentTransaction2.commit();
    }
}
