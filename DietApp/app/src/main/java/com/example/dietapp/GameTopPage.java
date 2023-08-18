package com.example.dietapp;

import static com.example.dietapp.GoalSetting.goal;
import static com.example.dietapp.MainActivity.user;
import static com.example.dietapp.MakeCharacter.character;
import static com.example.dietapp.MorningPage.currentBitmapMorning;
import static com.example.dietapp.NightPage.currentBitmapNight;
import static com.example.dietapp.NoonPage.currentBitmapNoon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class GameTopPage extends AppCompatActivity {

    private OkHttpClient client = new OkHttpClient();

    private void getDietAdvice() {
        String url = "http://10.0.2.2:8080/diet-advice";

        // Create JSON payload. You'll have to fetch real values for these.
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        float bmr;
        float tdee;
        if (user.getGender() == "男性"){
            bmr = (float) (10*user.getWeight() + (6.25*user.getHeight()) - (5*user.getAge()) + 5);
        }
        else{
            bmr = (float) (10*user.getWeight() + (6.25*user.getHeight()) - (5*user.getAge()) - 161);
        }
        if (user.getActivityLevel() == "低い"){
            tdee = (float) (1.5 * bmr);
        }
        else if (user.getActivityLevel() == "普通"){
            tdee = (float) (1.8 * bmr);
        }
        else{
            tdee = (float) (2.2 * bmr);
        }
        float target_calories;
        float calories_ate;
        target_calories = 7700 * (goal.getTargetWeight()-user.getWeight()) / (goal.getTargetDuration());
        calories_ate = Food.getBreakfastCalories() + Food.getLunchCalories() + Food.getDinnerCalories();
        float difference = target_calories - calories_ate;
        int progress = (int) ((difference / target_calories) * 100);
        SeekBar customSeekBar = findViewById(R.id.customSeekBar);
        customSeekBar.setOnTouchListener((v, event) -> true);
        customSeekBar.setProgress(progress);

        try {
            jsonObject.put("character_traits", character.getCharacterTrait());
            jsonObject.put("character_type", character.getCharacterType());
            jsonObject.put("weight", user.getWeight());
            jsonObject.put("height", user.getHeight());
            jsonObject.put("age", user.getAge());
            jsonObject.put("gender", user.getGender());
            jsonObject.put("calories_burned", tdee);
            jsonObject.put("food", Food.getBreakfastName() + Food.getLunchName() + Food.getDinnerName());
            jsonObject.put("calories_ate", calories_ate);
            jsonObject.put("target_calories", target_calories);
            jsonObject.put("target_weight", goal.getTargetWeight());
            jsonObject.put("target_period", goal.getTargetDuration());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseString = response.body().string();
                    try {
                        JSONObject jsonResponse = new JSONObject(responseString);
                        String advice = jsonResponse.getString("advice");
                        runOnUiThread(() -> {
                            // Assuming you have a TextView with id `borderedTextView` in your layout
                            TextView adviceTextView = findViewById(R.id.borderedTextView);
                            adviceTextView.setText(advice);
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private Button morningButton, noonButton, nightButton;
    private Bitmap loadImageFromInternalStorage(String filename) {
        try {
            FileInputStream fis = openFileInput(filename);
            return BitmapFactory.decodeStream(fis);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_top_page);

        // TextViewを参照
        TextView myTextView = findViewById(R.id.usernameTextView);

        // TextViewに文字列をセット
        myTextView.setText(user.getUsername());

        //アドバイスを更新
        getDietAdvice();
        // 各ボタンのリスナーをセット
        morningButton = findViewById(R.id.morningButton);
        morningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMorningPage();
            }
        });

        noonButton = findViewById(R.id.noonButton);
        noonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNoonPage();
            }
        });

        nightButton = findViewById(R.id.nightButton);
        nightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNightPage();
            }
        });
        // Load and set the image
        try {
            FileInputStream fis = openFileInput("Morning.jpg");
            Bitmap savedBitmap = BitmapFactory.decodeStream(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filename = "Morning.jpg";
        Bitmap loadedImage = loadImageFromInternalStorage(filename);

        ImageView morningImageView = findViewById(R.id.morningImageView);
        ImageView noonImageView = findViewById(R.id.noonImageView);
        ImageView nightImageView = findViewById(R.id.nightImageView);

//        morningImageView.setImageBitmap(loadedImage);
        morningImageView.setImageBitmap(currentBitmapMorning);
        noonImageView.setImageBitmap(currentBitmapNoon);
        nightImageView.setImageBitmap(currentBitmapNight);



//        MorningPage morningPage = new MorningPage();
//        Bitmap savedImage = morningPage.getSavedImage();

    }

    // PictureInputページへの遷移処理
    private void goToMorningPage() {
        Intent intent = new Intent(GameTopPage.this, MorningPage.class);
        startActivity(intent);
    }
    private void goToNoonPage() {
        Intent intent = new Intent(GameTopPage.this, NoonPage.class);
        startActivity(intent);
    }
    private void goToNightPage() {
        Intent intent = new Intent(GameTopPage.this, NightPage.class);
        startActivity(intent);
    }
}
