package com.example.dietapp;

import static com.example.dietapp.GoalSetting.goal;
import static com.example.dietapp.MainActivity.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WeightUpdate extends AppCompatActivity {

    private EditText weightEditText;
    private Button finishInputButton;
    private int Day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_update);  // 仮定: レイアウトの名前はactivity_weight_update

        weightEditText = findViewById(R.id.weightEditText);
        finishInputButton = findViewById(R.id.finishInputButton);

        finishInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    float weight = Float.parseFloat(weightEditText.getText().toString().trim());
                    Day = goal.getDay();
                    Day += 1;
                    goal.setDay(Day);
                    user.setWeight(weight);  // Userクラスで定義されるsetWeight関数を用いてweightを更新

                    // GameTopPage.javaに遷移
                    Intent intent = new Intent(WeightUpdate.this, GameTopPage.class);
                    startActivity(intent);
                } catch (NumberFormatException e) {
                    Toast.makeText(WeightUpdate.this, "正しい体重を入力してください", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}