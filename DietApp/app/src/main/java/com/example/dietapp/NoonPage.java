package com.example.dietapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.FileOutputStream;
import android.graphics.Bitmap;


public class NoonPage extends AppCompatActivity {

    private ImageView imageView;
    private Button buttonComplete, buttonRetake;
    private Bitmap currentBitmap = null;
    private String saveImageToInternalStorage(Bitmap bitmap) {
        try {
            String filename = "Noon.jpg";
            FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
            return filename;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_input);

        imageView = findViewById(R.id.image_view);
        buttonComplete = findViewById(R.id.button_complete);
        buttonRetake = findViewById(R.id.button_retake);

        buttonComplete.setOnClickListener(v -> {
            if (currentBitmap != null) {
                String savedFilename = saveImageToInternalStorage(currentBitmap);
                if (savedFilename != null) {
                    Log.d("debug", "Image saved as: " + savedFilename);
                } else {
                    Log.e("debug", "Error saving image.");
                }
            }
            // Go back to GameTopPage
            Intent intent = new Intent(NoonPage.this, GameTopPage.class);
            startActivity(intent);
            finish();
        });

        buttonRetake.setOnClickListener(v -> {
            buttonComplete.setVisibility(View.GONE);
            buttonRetake.setVisibility(View.GONE);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            resultLauncher.launch(intent);
        });

        Button cameraButton = findViewById(R.id.camera_button);
        cameraButton.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            resultLauncher.launch(intent);
        });
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        currentBitmap = (Bitmap) data.getExtras().get("data");
                        if (currentBitmap != null) {
                            int bmpWidth = currentBitmap.getWidth();
                            int bmpHeight = currentBitmap.getHeight();
                            Log.d("debug", "Width: " + bmpWidth);
                            Log.d("debug", "Height: " + bmpHeight);

                            imageView.setImageBitmap(currentBitmap);

                            // Show the Complete and Retake buttons
                            buttonComplete.setVisibility(View.VISIBLE);
                            buttonRetake.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
}
