package com.example.dietapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MakeCharacter extends AppCompatActivity {
    public static Character character;
    private String imageUrl;
    Spinner characterTypeSpinner, characterTraitSpinner, characterAppearanceSpinner;
    Button btnSubmit;  // 追加
    private final OkHttpClient client = new OkHttpClient();

    private void callGenerateAPI(String type, String trait, String appearance) {
        String url = "http://10.0.2.2:8080/generate"; // 実際のサーバーアドレスを入力してください。

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("character_type", type);
            jsonBody.put("character_traits", trait);
            jsonBody.put("appearance_attributes", appearance);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonBody.toString());
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
                        imageUrl = jsonResponse.getString("image_url");
                        // ここで出力画像を表示するメソッドを呼び出す
                        displayImage(imageUrl);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // UIの変更をここで行う
                                btnSubmit.setVisibility(View.VISIBLE);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    private void displayImage(String imageUrl) {
        runOnUiThread(() -> {
            ImageView imageView = findViewById(R.id.characterImageView); // idを変更して実際のImageViewのIDに合わせてください。
            Picasso.get().load(imageUrl).into(imageView);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_character);

        characterTypeSpinner = findViewById(R.id.spinnerCharacterType);
        characterTraitSpinner = findViewById(R.id.spinnerCharacterTrait);
        characterAppearanceSpinner = findViewById(R.id.spinnerCharacterAppearance);
        // btnSubmitボタンの参照を取得
        btnSubmit = findViewById(R.id.btnSubmit);

        Button generateButton = findViewById(R.id.btnGenerate);
        generateButton.setOnClickListener(v -> {
            // Here you can get the selected items and generate your character
            String type = characterTypeSpinner.getSelectedItem().toString();
            String trait = characterTraitSpinner.getSelectedItem().toString();
            String appearance = characterAppearanceSpinner.getSelectedItem().toString();
            callGenerateAPI(type, trait, appearance);
        });
        // btnSubmitボタンのクリックリスナーを設定
        Button goToGameTopPageButton = findViewById(R.id.btnSubmit);

        goToGameTopPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                character = saveCharacter();
                Intent intent = new Intent(MakeCharacter.this, GameTopPage.class);
                startActivity(intent);
            }
        });
    }
    private Character saveCharacter() {
        String type = characterTypeSpinner.getSelectedItem().toString();
        String trait = characterTraitSpinner.getSelectedItem().toString();
        String appearance = characterAppearanceSpinner.getSelectedItem().toString();
        character = new Character(type, trait, appearance, imageUrl);

        return character;
    }
}
