package com.example.jsketch;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import java.util.Observable;
import java.util.Observer;

public class ToolbarView extends LinearLayout implements Observer {
    private Model model;


    public ToolbarView(Context context, Model m) {
        super(context);

        View.inflate(context, R.layout.toolbarlayout, this);

        model = m;

        // Add view to observer
        model.addObserver(this);

        // add event listeners:
        Button selectionBtn;
        Button eraseBtn;
        Button lineBtn;
        Button circleBtn;
        Button rectangleBtn;
        Button redBtn;
        Button greenBtn;
        Button yellowBtn;
        Button blueBtn;
        selectionBtn = findViewById(R.id.selectionBtn);
        selectionBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                model.onToolbarClicked(v);
            }
        });
        eraseBtn = findViewById(R.id.eraseBtn);
        eraseBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                model.onToolbarClicked(v);
            }
        });
        lineBtn = findViewById(R.id.lineBtn);
        lineBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                model.onToolbarClicked(v);
            }
        });
        circleBtn = findViewById(R.id.circleBtn);
        circleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                model.onToolbarClicked(v);
            }
        });
        rectangleBtn = findViewById(R.id.rectanglebtn);
        rectangleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                model.onToolbarClicked(v);
            }
        });
        redBtn = findViewById(R.id.redBtn);
        redBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                model.onColourPaletteClicked(v);
            }
        });
        greenBtn = findViewById(R.id.greenBtn);
        greenBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                model.onColourPaletteClicked(v);
            }
        });
        yellowBtn = findViewById(R.id.yellowBtn);
        yellowBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                model.onColourPaletteClicked(v);
            }
        });
        blueBtn = findViewById(R.id.blueBtn);
        blueBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                model.onColourPaletteClicked(v);
            }
        });


    }

//    @Override
//    public void setOnTouchListener(OnTouchListener l) {
//        super.setOnTouchListener(l);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        // could handle input here
//        Log.d("DEBUG", "touch " + event.getX() + "," + event.getY());
//        return super.onTouchEvent(event);
//    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

