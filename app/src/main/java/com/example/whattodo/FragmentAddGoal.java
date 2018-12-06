package com.example.whattodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Switch;
import java.util.Objects;


public class FragmentAddGoal extends Fragment implements View.OnClickListener{

    public Context context;

    private PopupWindow popupWindow;
    private LinearLayout linearLayout;
    private String[] frequencyOptions;
    private Spinner frequencySpinner;
    private View rootView;
    private Button buttonDone;
    private EditText editTextName, editTextUnits, editTextGoal;
    private ImageView imageViewIcon, imageViewColor;
    private CalendarView calendarViewEndDate;
    private Switch switchToggleCalendar;
    private String stringFrequency, stringName, stringUnits;
    private ConstraintLayout constraintLayout;
    private int intGoal, intIconId, intColorId;
    private long longDate;
    private boolean calendarVisibility;

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
        imageViewIcon.setOnClickListener(this);
        imageViewColor.setOnClickListener(this);
        buttonDone.setOnClickListener(this);
        switchToggleCalendar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                if(checked){
                    calendarVisibility = true;
                    calendarViewEndDate.setVisibility(View.VISIBLE);
                }
                else{
                    calendarVisibility = false;
                    calendarViewEndDate.setVisibility(View.GONE);
                }
            }
        });
    }

    private void wireWidgets() {
        frequencyOptions = new String[] {"never refresh", "weekly", "biweekly", "monthly", "yearly"};
        frequencySpinner = rootView.findViewById(R.id.spinner_addgoal_frequency);
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (
                Objects.requireNonNull(getActivity()),
                android.R.layout.simple_spinner_item,
                frequencyOptions
        );
        frequencySpinner.setAdapter(adapter);

        buttonDone = rootView.findViewById(R.id.button_add_goal_done);
        editTextGoal = rootView.findViewById(R.id.edittext_addgoal_goal);
        editTextName = rootView.findViewById(R.id.edittext_addgoal_name);
        editTextUnits = rootView.findViewById(R.id.edittext_addgoal_units);
        imageViewColor = rootView.findViewById(R.id.imageview_addgoal_color);
        imageViewIcon = rootView.findViewById(R.id.imageview_addgoal_icon);
        calendarViewEndDate = rootView.findViewById(R.id.calendarview_addgoal_enddate);
        switchToggleCalendar = rootView.findViewById(R.id.switch_addgoal_toggleenddate);
        constraintLayout = rootView.findViewById(R.id.constraitlayout_addgoal);
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
                    stringName = editTextName.getText().toString();
                    stringUnits = editTextUnits.getText().toString();
                    if(!(editTextGoal.getText().toString().equals(""))){
                        intGoal = Integer.parseInt(editTextGoal.getText().toString());
                    }
                    intIconId = imageViewIcon.getId();
                    intColorId = imageViewColor.getId();
                    if(calendarVisibility) {
                        longDate = calendarViewEndDate.getDate();
                    }
                    else{
                        longDate = 0;
                    }

                    saveToDB();

                   FragmentManager fm = getFragmentManager();

                   Fragment nextFrag= new Fragment();
                    assert fm != null;
                    fm.beginTransaction()
                    .replace(R.id.container_main, nextFrag)
                    .commit();

                break;
        }
    }

    private void saveToDB(){
        SQLiteDatabase database = new DBHelperGoal(this.getContext()).getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(GoalDatabase.Goal.COLUMN_NAME, stringName);
        values.put(GoalDatabase.Goal.COLUMN_UNITS, stringUnits);
        values.put(GoalDatabase.Goal.COLUMN_FREQUENCY, stringFrequency);

        values.put(GoalDatabase.Goal.COLUMN_TOTAL, intGoal);
        values.put(GoalDatabase.Goal.COLUMN_ICON, intIconId);
        values.put(GoalDatabase.Goal.COLUMN_COLOR, intColorId);
        if(longDate != 0) {
            values.put(GoalDatabase.Goal.COLUMN_END_DATE, longDate);
        }

        long newRowId = database.insert(GoalDatabase.Goal.TABLE_NAME, null, values);

    }


}
