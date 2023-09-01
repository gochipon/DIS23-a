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
import androidx.navigation.ui.AppBarConfiguration;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.dietapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_FIRST_RUN = "firstRun";
//
//    private AppBarConfiguration appBarConfiguration;
    private Spinner genderSpinner;
    private Spinner activityLevelSpinner;
    private EditText ageEditText;
    private EditText weightEditText;
    private EditText heightEditText;
    private EditText usernameEditText;

    public static User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToGoalSettingButton = findViewById(R.id.goToGoalSettingButton);

        genderSpinner = findViewById(R.id.genderSpinner);
        activityLevelSpinner = findViewById(R.id.activityLevelSpinner);
        ageEditText = findViewById(R.id.ageEditText);
        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        usernameEditText = findViewById(R.id.usernameEditText);

        // 初回起動の判定ロジック
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isFirstRun = prefs.getBoolean(KEY_FIRST_RUN, true);

        if (isFirstRun) {
            // 初回起動時の処理を行うためのActivityを起動
            // 例: 初回起動時の情報入力画面に遷移する
//            startActivity(FirstRunActivity.newIntent(this));

            goToGoalSettingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user = saveUserInfo();


                    Intent intent = new Intent(MainActivity.this, GoalSetting.class);
                    startActivity(intent);
                }
            });

            // 初回起動フラグをfalseに設定して保存
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(KEY_FIRST_RUN, false);
            editor.apply();
        } else {
            // 通常の起動時の処理を行う
            // 例: アプリのメイン画面に遷移する
            goToGoalSettingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user = saveUserInfo();


                    Intent intent = new Intent(MainActivity.this, GoalSetting.class);
                    startActivity(intent);
                }
            });

            // 初回起動フラグをfalseに設定して保存
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(KEY_FIRST_RUN, false);
            editor.apply();
        }



    }

        private User saveUserInfo() {
            String username = usernameEditText.getText().toString();
            String gender = genderSpinner.getSelectedItem().toString();
            String activityLevel = activityLevelSpinner.getSelectedItem().toString();
            int age = Integer.parseInt(ageEditText.getText().toString());
            float weight = Float.parseFloat(weightEditText.getText().toString());
            float height = Float.parseFloat(heightEditText.getText().toString());

            user = new User(username, gender, activityLevel, age, weight, height);

            // 以降、user オブジェクトを使用して処理...

            return user;
        }
}
