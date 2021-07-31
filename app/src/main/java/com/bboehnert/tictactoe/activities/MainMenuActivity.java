package com.bboehnert.tictactoe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bboehnert.tictactoe.R;

public class MainMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void onStartGameButtonClick(View view) {
        Intent intentMain = new Intent(this, GameActivity.class);
        startActivity(intentMain);
    }

    public void onConfigurationButtonClick(View view) {
        Intent intentMain = new Intent(this, PlayerConfigurationActivity.class);
        startActivity(intentMain);
    }
}
