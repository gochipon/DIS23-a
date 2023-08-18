package com.example.dietapp;

import static com.example.dietapp.MorningPage.currentBitmapMorning;
import static com.example.dietapp.NightPage.currentBitmapNight;
import static com.example.dietapp.NoonPage.currentBitmapNoon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.FileInputStream;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
public class GameTopPage extends AppCompatActivity {

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
