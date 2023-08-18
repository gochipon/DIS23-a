package com.example.dietapp;

import static com.example.dietapp.MainActivity.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import static com.example.dietapp.MainActivity.user;

public class GoalSetting extends AppCompatActivity {


    private EditText targetWeightEditText;
    private EditText targetDurationEditText;
    public static Goal goal;


    private Button helloButton;
    private float weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_setting);

        targetWeightEditText = findViewById(R.id.targetWeightEditText);
        targetDurationEditText = findViewById(R.id.targetDurationEditText);
        Button goToGameTopPageButton = findViewById(R.id.goToGameTopPageButton);
        goToGameTopPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal = saveGoalInfo();

                Intent intent = new Intent(GoalSetting.this, MakeCharacter.class);
                startActivity(intent);

            }
        });
    }
    private Goal saveGoalInfo() {
        float targetWeight = Float.parseFloat(targetWeightEditText.getText().toString());
        float targetDuration = Float.parseFloat(targetDurationEditText.getText().toString());

        goal = new Goal(targetWeight, targetDuration);

        return goal;
    }
}