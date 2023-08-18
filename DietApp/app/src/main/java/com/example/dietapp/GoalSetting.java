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

                float targetWeight = Float.parseFloat(targetWeightEditText.getText().toString());
                String targetDuration = targetDurationEditText.getText().toString();

                Goal goal = new Goal(targetWeight, targetDuration);

                Intent intent = new Intent(GoalSetting.this, GameTopPage.class);
                startActivity(intent);

            }
        });
    }
}