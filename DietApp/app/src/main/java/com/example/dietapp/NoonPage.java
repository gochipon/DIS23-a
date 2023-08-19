package com.example.dietapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Base64;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import android.graphics.Bitmap;

public class NoonPage extends AppCompatActivity {

    private ImageView imageView;
    private Button buttonComplete, buttonRetake;
    public static Bitmap currentBitmapNoon = null;
    private OkHttpClient client = new OkHttpClient();
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
    private void sendImageToServer(Bitmap bitmap) {
        String url = "http://10.0.2.2:8080/predict-food";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("bitmap", encodedImage);
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
                        String food = jsonResponse.getString("food");
                        int calories_ate = jsonResponse.getInt("calories_ate");

                        // 以下、受け取ったデータを使用した処理
                        Log.d("API_RESPONSE", "Food: " + food + ", Calories: " + calories_ate);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noon_page);

        imageView = findViewById(R.id.image_view);
        buttonComplete = findViewById(R.id.button_complete);
        buttonRetake = findViewById(R.id.button_retake);

        buttonComplete.setOnClickListener(v -> {
            if (currentBitmapNoon != null) {
                String savedFilename = saveImageToInternalStorage(currentBitmapNoon);
                if (savedFilename != null) {
                    Log.d("debug", "Image saved as: " + savedFilename);
                } else {
                    Log.e("debug", "Error saving image.");
                }
                // 画像をAPIに投げる
                sendImageToServer(currentBitmapNoon);
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
                        currentBitmapNoon = (Bitmap) data.getExtras().get("data");
                        if (currentBitmapNoon != null) {
                            currentBitmapNoon = Bitmap.createScaledBitmap(currentBitmapNoon, 224, 224, true);

                            imageView.setImageBitmap(currentBitmapNoon);

                            // Show the Complete and Retake buttons
                            buttonComplete.setVisibility(View.VISIBLE);
                            buttonRetake.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
}
