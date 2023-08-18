package INHEACA.integratedhealthcareapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import INHEACA.integratedhealthcareapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
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

        final TextView genderHint = findViewById(R.id.genderHint);
        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // 項目が選択されたらヒントを非表示にする
                genderHint.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // 何も選択されていない場合、ヒントを表示する
                genderHint.setVisibility(View.VISIBLE);
            }
        });

        final TextView activityHint = findViewById(R.id.activityHint);
        Spinner activityLevelSpinner = findViewById(R.id.activityLevelSpinner);
        activityLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                activityHint.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                activityHint.setVisibility(View.VISIBLE);
            }
        });
    }
}