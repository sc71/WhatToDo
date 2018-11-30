package com.example.whattodo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;


public class FragmentAddGoal extends Fragment implements View.OnClickListener{

    public Context context;

    FragmentCommunicator dataPasser;
    private PopupWindow popupWindow;
    private LinearLayout linearLayout;
    private String[] frequecyOptions;
    private Spinner frequencySpinner;
    private View rootView;
    private Button buttonDone;
    private EditText editTextName, editTextUnits, editTextGoal;
    private ImageView imageViewIcon, imageViewColor;
    private String stringFrequency, stringName, stringUnits;
    private int intGoal, intIconId, intColorId;
    private static final String GOAL_NAME = "goal name";
    private static final String GOAL_UNITS = "goal units";
    private static final String GOAL_TOTAL = "goal total";
    private static final String GOAL_FREQUENCY = "goal frequency";
    private static final String GOAL_ICON = "goal icon";
    private static final String GOAL_COLOR = "goal color";

    private ActivityCommunicator activityCommunicator;
    private String activityAssignedValue = "";




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //inflate the view (make java obj from the xml)
        rootView = inflater.inflate(R.layout.fragment_add_goal, container, false);

        //wire widgets + set listeners
        wireWidgets();
        setListeners();

        //sending the view back
        return rootView;
    }

    private void setListeners() {

    }

    private void wireWidgets() {
        frequecyOptions = new String[] {"never refresh", "weekly", "biweekly", "monthly", "yearly"};
        frequencySpinner = rootView.findViewById(R.id.spinner_addgoal_frequency);
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (
                Objects.requireNonNull(getActivity()),
                android.R.layout.simple_spinner_item,
                frequecyOptions
        );
        frequencySpinner.setAdapter(adapter);

        buttonDone = rootView.findViewById(R.id.button_add_goal_done);
        editTextGoal = rootView.findViewById(R.id.edittext_addgoal_goal);
        editTextName = rootView.findViewById(R.id.edittext_addgoal_name);
        editTextUnits = rootView.findViewById(R.id.edittext_addgoal_units);
        imageViewColor = rootView.findViewById(R.id.imageview_addgoal_color);
        imageViewIcon = rootView.findViewById(R.id.imageview_addgoal_icon);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageview_addgoal_color:

                break;

            case R.id.imageview_addgoal_icon:

                break;

            case R.id.button_add_goal_done:
                stringFrequency = frequencySpinner.getSelectedItem().toString();
                stringUnits = editTextUnits.getText().toString();
                stringName = editTextName.getText().toString();
                intGoal = Integer.parseInt(editTextGoal.getText().toString());
                intColorId = imageViewColor.getId();
                intIconId= imageViewIcon.getId();

                break;
        }
    }


}
