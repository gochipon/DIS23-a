package com.example.dietapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.dietapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private Spinner genderSpinner;
    private Spinner activityLevelSpinner;
    private EditText ageEditText;
    private EditText weightEditText;
    private EditText heightEditText;


    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToGoalSettingButton = findViewById(R.id.goToGoalSettingButton);


        genderSpinner = findViewById(R.id.genderSpinner);
        activityLevelSpinner = findViewById(R.id.activityLevelSpinner);
        ageEditText = findViewById(R.id.ageEditText);
        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);


        goToGoalSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();


                Intent intent = new Intent(MainActivity.this, GoalSetting.class);
                startActivity(intent);
            }
        });
    }

        private void saveUserInfo() {
            String gender = genderSpinner.getSelectedItem().toString();
            String activityLevel = activityLevelSpinner.getSelectedItem().toString();
            int age = Integer.parseInt(ageEditText.getText().toString());
            float weight = Float.parseFloat(weightEditText.getText().toString());
            float height = Float.parseFloat(heightEditText.getText().toString());

            user = new User(gender, activityLevel, age, weight, height);

            // 以降、user オブジェクトを使用して処理...
        }
}