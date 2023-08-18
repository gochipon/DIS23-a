package com.example.dietapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameTopPage extends AppCompatActivity {

    private Button morningButton, noonButton, nightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_top_page);

        // 各ボタンのリスナーをセット
        morningButton = findViewById(R.id.morningButton);
        morningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPictureInput();
            }
        });

        noonButton = findViewById(R.id.noonButton);
        noonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPictureInput();
            }
        });

        nightButton = findViewById(R.id.nightButton);
        nightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPictureInput();
            }
        });
    }

    // PictureInputページへの遷移処理
    private void goToPictureInput() {
        Intent intent = new Intent(GameTopPage.this, Picture_input.class);
        startActivity(intent);
    }
}
