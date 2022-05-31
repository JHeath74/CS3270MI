package edu.weber.cs.cs3270mi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Notification;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ActionFragment.calculateButtonListener{

    private ActionFragment actionFragment;
    private ResultsFragment resultsFragment;

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm=getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.ActionFragment, new ActionFragment(), "ActionFragment")
                .replace(R.id.ResultsFragment, new ResultsFragment(), "ResultsFragment")
                .commit();
    }

    @Override
    public void calculateButtonPressed(double BMI, double BFP)
    {
        if(resultsFragment == null)
        {
            fm = getSupportFragmentManager();
            resultsFragment = (ResultsFragment) fm.findFragmentById(R.id.ResultsFragment);
        }

        resultsFragment.updateFields(BMI, BFP);
    }
}