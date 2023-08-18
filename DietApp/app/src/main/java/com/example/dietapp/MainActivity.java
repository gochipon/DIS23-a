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
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
                Intent intent = new Intent(MainActivity.this, GoalSetting.class);
                startActivity(intent);
            }
        });

        Button goToGoalSettingButton = findViewById(R.id.goToGoalSettingButton);
        goToGoalSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GoalSetting.class);
                startActivity(intent);
            }
        });
    }

}