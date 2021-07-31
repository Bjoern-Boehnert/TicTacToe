package com.bboehnert.tictactoe;

public class Gamefield {

    private PlayerOrder[] fields = new PlayerOrder[9];

    public Gamefield() {
        for (int i = 0; i < fields.length; i++) {
            fields[i] = PlayerOrder.nothing;
        }
    }

    public void setField(int pos, PlayerOrder player) {
        if (pos < 0 || pos > 8) return;
        this.fields[pos] = player;
    }

    public boolean isWin() {
        return isWinCondition(PlayerOrder.first) || isWinCondition(PlayerOrder.second);
    }

    public boolean isWinCondition(PlayerOrder player) {
        if (fields[0] == player && fields[1] == player && fields[2] == player) {
            return true;
        } else if (fields[3] == player && fields[4] == player && fields[5] == player) {
            return true;
        } else if (fields[6] == player && fields[7] == player && fields[8] == player) {
            return true;
        } else if (fields[0] == player && fields[3] == player && fields[6] == player) {
            return true;
        } else if (fields[1] == player && fields[4] == player && fields[7] == player) {
            return true;
        } else if (fields[2] == player && fields[5] == player && fields[8] == player) {
            return true;
        } else if (fields[0] == player && fields[4] == player && fields[8] == player) {
            return true;
        } else if (fields[6] == player && fields[4] == player && fields[2] == player) {
            return true;
        }
        return false;
    }

}
