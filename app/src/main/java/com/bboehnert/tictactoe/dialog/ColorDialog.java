package com.bboehnert.tictactoe.dialog;

import android.content.Context;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ColorDialog {

    private final int selectedColor;
    private final AmbilWarnaDialog dialog;
    private final ColorChangedListener colorChangedListener;

    public ColorDialog(Context context, int selectedcolor, ColorChangedListener listener) {
        this.colorChangedListener = listener;
        this.selectedColor = selectedcolor;
        dialog = new AmbilWarnaDialog(context, selectedcolor, new ColorListener());
    }

    public void show() {
        dialog.show();
    }

    private class ColorListener implements AmbilWarnaDialog.OnAmbilWarnaListener {
        @Override
        public void onCancel(AmbilWarnaDialog dialog) {
        }

        @Override
        public void onOk(AmbilWarnaDialog dialog, int color) {
            //Schicke ein Event, wenn die Farbe gesetzt wurde
            colorChangedListener.getColor(color);
        }
    }
}
