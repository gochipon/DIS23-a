package com.example.dietapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.dietapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public EditText weightEditText;
    public EditText heightEditText;
    public EditText ageEditText;
    public static int age;
    public static float height;
    public static float weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        weightEditText = binding.weightEditText;
        heightEditText = binding.heightEditText;
        ageEditText = binding.ageEditText;

        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // ここに選択されたときのアクションを書く（必要であれば）
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // ここに何も選択されていない場合のアクションを書く（必要であれば）
            }
        });

        Spinner activityLevelSpinner = findViewById(R.id.activityLevelSpinner);
        activityLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // ここに選択されたときのアクションを書く（必要であれば）
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // ここに何も選択されていない場合のアクションを書く（必要であれば）
            }
        });

        Button goToGoalSettingButton = findViewById(R.id.goToGoalSettingButton);
        goToGoalSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String ageString = ageEditText.getText().toString();
                    String weightString = weightEditText.getText().toString();
                    String heightString = heightEditText.getText().toString();

                    if (ageString.isEmpty() || weightString.isEmpty() || heightString.isEmpty()) {
                        Toast.makeText(MainActivity.this, "すべての項目を入力してください。", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    age = Integer.parseInt(ageString);
                    height = Float.parseFloat(heightString);
                    weight = Float.parseFloat(weightString);

                    Intent intent = new Intent(MainActivity.this, GoalSetting.class);
                    startActivity(intent);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "正しい値を入力してください。", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
