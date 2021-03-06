package edu.weber.cs.cs3270mi;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;

import java.math.BigDecimal;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View root;

    private calculateButtonListener mCallback;

    double BFP;
    double BMI;

    double Weight;
    double Height;
    double Age;

    double female;
    double male;

    public interface calculateButtonListener
    {
        void calculateButtonPressed(double BMI, double BodyFatPercentage);
    }

    private TextInputEditText WeightInPoundsTextField;
    private TextInputEditText HeightInInchesTextField;
    private TextInputEditText AgeTextField;

    private RadioGroup RadioButtonSex;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;

    private final TextWatcher weightWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            /*    String Weighted = WeightInPounds.getText().toString();
                BigDecimal Weight = new BigDecimal(Weighted);

                String High = HeightInInches.getText().toString();
                BigDecimal Height = new BigDecimal(High);

                String Aged = AgeInYears.getText().toString();
                BigDecimal Age = new BigDecimal(Aged);*/
            //----------------------------------------------------
                 Weight = Double.parseDouble(WeightInPoundsTextField.getText().toString());
                 Height = Double.parseDouble(HeightInInchesTextField.getText().toString());
                 Age = Double.parseDouble(AgeTextField.getText().toString());


        }
    };

    public ActionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActionFragment newInstance(String param1, String param2) {
        ActionFragment fragment = new ActionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_action, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();



        TextInputEditText WeightInPounds = root.findViewById(R.id.WeightInPoundsTextField);
        WeightInPounds.addTextChangedListener(weightWatcher);

        TextInputEditText HeightInInches = root.findViewById(R.id.HeightInInchesTextField);
        HeightInInches.addTextChangedListener(weightWatcher);

        TextInputEditText AgeInYears = root.findViewById(R.id.AgeTextField);
        AgeInYears.addTextChangedListener(weightWatcher);


    }

    @Override
    public void onStart() {
        super.onStart();

        Button btnCalculate = root.findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(R.id.radioButtonMale == 1)
                {
                    BMI = ((Weight / Height)*(Weight / Height)) * 703;
                    BFP = ((1.20 * BMI) + (0.23* Age) - (10.8 * 1)) - 5.4;

                    mCallback.calculateButtonPressed(BMI, BFP);

                }
                if(R.id.radioButtonFemale == 1)
                {
                    BMI = ((Weight / Height)*(Weight / Height)) * 703;
                    BFP = ((1.20 * BMI) + (0.23* Age) - (10.8 * 0)) - 5.4;

                    mCallback.calculateButtonPressed(BMI, BFP);
                }
            }
        });

    }

   /* public void onRadioButtonClicked(View view) // Not working
    {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId())
        {
            case R.id.radioButtonMale:
                if(checked)
                {
                    male = 1;
                }
            break;
            case R.id.radioButtonFemale:
                if(checked)
                {
                    female = 0;
                }
        }
    }*/



    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

        try
        {
           mCallback = (calculateButtonListener) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement calculateButtonListener");
        }
    }
}