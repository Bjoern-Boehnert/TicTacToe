package com.bboehnert.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

public class SharedPreferencesHelper {

    private SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(Context context) {
        this.sharedPreferences = context.getSharedPreferences(
                "SharedPreferencesFile",
                Context.MODE_PRIVATE);

    }

    public String readPlayerName(PlayerOrder playerOrder) {

        String playerField = getPlayerNameField(playerOrder);
        String result = readString(playerField);
        if (result == null) {
            return playerOrder.toString();
        }
        return result;
    }

    public void savePlayerName(PlayerOrder playerOrder, String value) {

        String playerField = getPlayerNameField(playerOrder);
        saveStringAttribute(playerField, value);

    }

    public int readPlayerColor(PlayerOrder playerOrder) {

        String playerColorField = getPlayerColorField(playerOrder);

        int result = readInt(playerColorField);
        if (result == 0) {
            if (playerOrder == PlayerOrder.first) {
                return Color.BLUE; //Blue
            } else if (playerOrder == PlayerOrder.second) {
                return Color.RED; //Red
            }
        }
        return result;
    }

    public void savePlayerColor(PlayerOrder playerOrder, int value) {

        String playerField = getPlayerColorField(playerOrder);
        saveIntegerAttribute(playerField, value);

    }

    private String getPlayerColorField(PlayerOrder player) {
        switch (player) {
            case first:
                return "PlayerOneColor";
            case second:
                return "PlayerTwoColor";
        }
        return null;
    }

    private String getPlayerNameField(PlayerOrder playerOrder) {
        switch (playerOrder) {
            case first:
                return "PlayerOneName";
            case second:
                return "PlayerTwoName";
        }
        return null;
    }

    private int readInt(String field) {
        return this.sharedPreferences.getInt(field, 0);
    }

    private String readString(String field) {
        return this.sharedPreferences.getString(field, null);
    }

    private void saveIntegerAttribute(String field, int value) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putInt(field, value);
        editor.apply();
    }

    private void saveStringAttribute(String field, String value) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(field, value);
        editor.apply();
    }

}
