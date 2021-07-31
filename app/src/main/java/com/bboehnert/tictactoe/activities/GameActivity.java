package com.bboehnert.tictactoe.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bboehnert.tictactoe.Gamefield;
import com.bboehnert.tictactoe.PlayerOrder;
import com.bboehnert.tictactoe.R;
import com.bboehnert.tictactoe.SharedPreferencesHelper;

import java.util.Random;

public class GameActivity extends Activity {

    private SharedPreferencesHelper shared;
    private Gamefield gamefield = new Gamefield();
    private PlayerOrder currentPlayer = getRandomStartingPlayer();
    private TextView turnTextView;
    private ImageView[] imageFields = new ImageView[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        this.shared = new SharedPreferencesHelper(this);

        // Init
        this.turnTextView = findViewById(R.id.TurnText);
        this.turnTextView.setText(shared.readPlayerName(this.currentPlayer));

        TableLayout tblLayout = this.findViewById(R.id.fieldButtons);
        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) tblLayout.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                //Init Tile
                int index = (i * 3) + j;
                this.imageFields[index] = (ImageView) row.getChildAt(j);
                this.imageFields[index].setOnClickListener(v -> {
                    gamefield.setField(index, currentPlayer);
                    v.setEnabled(false);
                    v.setBackgroundColor(getPlayerColor(currentPlayer));
                    triggerGameRoutine();
                });
            }

        }

    }

    private int getPlayerColor(PlayerOrder currentPlayer) {
        return shared.readPlayerColor(currentPlayer);
    }

    private void triggerGameRoutine() {

        if (this.gamefield.isWin()) {
            PlayerOrder winner = PlayerOrder.nothing;
            if (this.gamefield.isWinCondition(PlayerOrder.first)) {
                winner = PlayerOrder.first;
            } else if (this.gamefield.isWinCondition(PlayerOrder.second)) {
                winner = PlayerOrder.second;
            }
            displayWinner(winner);
            disableInput();
        } else {
            switchPlayers();
        }

    }

    private void displayWinner(PlayerOrder player) {
        this.turnTextView.setText("Winner is: " + shared.readPlayerName(player));
    }

    private void switchPlayers() {
        if (this.currentPlayer == PlayerOrder.first) {
            this.currentPlayer = PlayerOrder.second;
        } else {
            this.currentPlayer = PlayerOrder.first;
        }
        this.turnTextView.setText(shared.readPlayerName(this.currentPlayer));
    }

    private void disableInput() {
        for (ImageView img : this.imageFields) {
            img.setEnabled(false);
        }
    }

    private PlayerOrder getRandomStartingPlayer() {
        int number = new Random().nextInt(100);
        return (number % 2 == 0) ? PlayerOrder.first : PlayerOrder.second;
    }

    public void onRestartButtonClick(View view) {
        for (ImageView img : this.imageFields) {
            img.setBackgroundResource(R.drawable.image_border);
            img.setEnabled(true);
        }
        this.gamefield = new Gamefield();
        this.currentPlayer = getRandomStartingPlayer();
        this.turnTextView.setText(shared.readPlayerName(this.currentPlayer));
    }

    public void onReturnButtonClick(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }


}