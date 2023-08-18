package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dietapp.databinding.ActivityGameTopPageBinding;

public class GameTopPage extends AppCompatActivity {

    private ActivityGameTopPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_top_page);
    }
}