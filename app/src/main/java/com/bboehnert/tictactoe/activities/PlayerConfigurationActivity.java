package com.bboehnert.tictactoe.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.bboehnert.tictactoe.PlayerOrder;
import com.bboehnert.tictactoe.R;
import com.bboehnert.tictactoe.SharedPreferencesHelper;
import com.bboehnert.tictactoe.dialog.ColorChangedListener;
import com.bboehnert.tictactoe.dialog.ColorDialog;

public class PlayerConfigurationActivity extends Activity {

    private int playerColor1 = 0;
    private int playerColor2 = 0;
    private EditText playerOneNameEditText;
    private EditText playerTwoNameEditText;
    private SharedPreferencesHelper shared;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_configuration);

        shared = new SharedPreferencesHelper(this);
        this.playerOneNameEditText = findViewById(R.id.playerOneNameEditText);
        this.playerOneNameEditText.setText(shared.readPlayerName(PlayerOrder.first));
        this.playerTwoNameEditText = findViewById(R.id.playerTwoNameEditText);
        this.playerTwoNameEditText.setText(shared.readPlayerName(PlayerOrder.second));
        this.playerColor1 = shared.readPlayerColor(PlayerOrder.first);
        this.playerColor2 = shared.readPlayerColor(PlayerOrder.second);

        Button colorPickerPlayerOne = findViewById(R.id.playerOneColorPickerButton);
        Button colorPickerPlayerTwo = findViewById(R.id.playerTwoColorPickerButton);
        colorPickerPlayerOne.setBackgroundColor(playerColor1);
        colorPickerPlayerTwo.setBackgroundColor(playerColor2);

    }

    private void showColorDialog(View view) {

        //Reference: https://guides.codepath.com/android/Creating-Custom-Listeners
        ColorDialog colorDialog = new ColorDialog(this, playerColor1, new ColorChangedListener() {
            @Override
            public void getColor(int newColor) {

                view.setBackgroundColor(newColor);

                if (view.getId() == R.id.playerOneColorPickerButton) {
                    playerColor1 = newColor;
                } else {
                    playerColor2 = newColor;
                }

            }
        });
        colorDialog.show();

    }

    public void onApplyButtonClick(View view) {
        // Save Player Configuration in App
        shared.savePlayerName(PlayerOrder.first, this.playerOneNameEditText.getText().toString());
        shared.savePlayerName(PlayerOrder.second, this.playerTwoNameEditText.getText().toString());
        shared.savePlayerColor(PlayerOrder.first, this.playerColor1);
        shared.savePlayerColor(PlayerOrder.second, this.playerColor2);
    }

    public void onReturnButtonClick(View view) {
        Intent intentMain = new Intent(this, MainMenuActivity.class);
        startActivity(intentMain);
    }

    public void onPlayerColorButtonClick(View view) {
        showColorDialog(view);
    }
}
