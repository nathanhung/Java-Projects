package com.example.jsketch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class CanvasView extends View implements Observer {
    private Model model;

    Paint blue_brush;
    Paint red_brush;
    Paint yellow_brush;
    Paint green_brush;

    ArrayList<Rectangle> rectangles;
    ArrayList<Circle> circles;
    ArrayList<Line> lines;

    private float previousX;
    private float previousY;

    /*
    Rectangle rectangle;

     */
//    Circle circle;


    public CanvasView(Context context, Model m) {
        super(context);

        //View.inflate(context, R.layout.canvaslayout, this);

        model = m;

        // Add view to observer
        model.addObserver(this);

        blue_brush = new Paint(Paint.ANTI_ALIAS_FLAG);
        blue_brush.setColor(Color.BLUE);

        red_brush = new Paint(Paint.ANTI_ALIAS_FLAG);
        red_brush.setColor(Color.RED);

        yellow_brush = new Paint(Paint.ANTI_ALIAS_FLAG);
        yellow_brush.setColor(Color.YELLOW);

        green_brush = new Paint(Paint.ANTI_ALIAS_FLAG);
        green_brush.setColor(Color.GREEN);

        rectangles = new ArrayList<>();
        circles = new ArrayList<>();
        lines = new ArrayList<>();
/*
        // define shapes to be drawn
        rectangle = new Rectangle(200, 300);
        rectangle.scale(4, 3);
        rectangle.translate(250,250);
*/
//        circle = new Circle(100);
//        circle.scale(2, 2);
//        circle.translate(300, 300);



    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // could handle input here
        Log.d("DEBUG", "touch " + event.getX() + "," + event.getY());

        float x = event.getX();
        float y = event.getY();

        int action = event.getActionMasked();

        if (this.model.selectionBtnSelected) {
            switch (action) {
                case (MotionEvent.ACTION_DOWN):
                    Log.d("DEBUG", "Action was DOWN");
                    return true;
                case (MotionEvent.ACTION_MOVE):
                    Log.d("DEBUG", "Action was MOVE");

                    return true;
                case (MotionEvent.ACTION_UP):
                    Log.d("DEBUG", "Action was UP");
                    return true;
                case (MotionEvent.ACTION_CANCEL):
                    Log.d("DEBUG", "Action was CANCEL");
                    return true;
                case (MotionEvent.ACTION_OUTSIDE):
                    Log.d("DEBUG", "Movement occurred outside bounds " +
                            "of current screen element");
                    return true;
                default:
                    return super.onTouchEvent(event);
            }
        } else if (this.model.eraseBtnSelected) {

        } else if (this.model.lineBtnSelected) {
            switch (action) {
                case (MotionEvent.ACTION_DOWN):
                    Log.d("DEBUG", "Action was DOWN");
                    Line line = new Line();
                    line.translate(event.getX(), event.getY());
                    lines.add(line);
                    invalidate();
                    return true;
                case (MotionEvent.ACTION_MOVE):
                    Log.d("DEBUG", "Action was MOVE");

                    return true;
                case (MotionEvent.ACTION_UP):
                    Log.d("DEBUG", "Action was UP");
                    return true;
                case (MotionEvent.ACTION_CANCEL):
                    Log.d("DEBUG", "Action was CANCEL");
                    return true;
                case (MotionEvent.ACTION_OUTSIDE):
                    Log.d("DEBUG", "Movement occurred outside bounds " +
                            "of current screen element");
                    return true;
                default:
                    return super.onTouchEvent(event);
            }
        } else if (this.model.circleBtnSelected) {
            switch (action) {
                case (MotionEvent.ACTION_DOWN):
                    Log.d("DEBUG", "Action was DOWN");
                    Circle circle = new Circle(50);
                    circle.translate(event.getX(), event.getY());
                    circles.add(circle);
                    invalidate();
                    return true;
                case (MotionEvent.ACTION_MOVE):
                    Log.d("DEBUG", "Action was MOVE");

                    return true;
                case (MotionEvent.ACTION_UP):
                    Log.d("DEBUG", "Action was UP");
                    return true;
                case (MotionEvent.ACTION_CANCEL):
                    Log.d("DEBUG", "Action was CANCEL");
                    return true;
                case (MotionEvent.ACTION_OUTSIDE):
                    Log.d("DEBUG", "Movement occurred outside bounds " +
                            "of current screen element");
                    return true;
                default:
                    return super.onTouchEvent(event);
            }
        } else if (this.model.rectangleBtnSelected) {
            switch (action) {
                case (MotionEvent.ACTION_DOWN):
                    Log.d("DEBUG", "Action was DOWN");
                    Rectangle rectangle = new Rectangle(50, 50);
                    rectangle.translate(event.getX(), event.getY());
                    rectangles.add(rectangle);
                    invalidate();
                    return true;
                case (MotionEvent.ACTION_MOVE):
                    Log.d("DEBUG", "Action was MOVE");
                    //rectangles.get(rectangles.size() - 1)
                    return true;
                case (MotionEvent.ACTION_UP):
                    Log.d("DEBUG", "Action was UP");
                    return true;
                case (MotionEvent.ACTION_CANCEL):
                    Log.d("DEBUG", "Action was CANCEL");
                    return true;
                case (MotionEvent.ACTION_OUTSIDE):
                    Log.d("DEBUG", "Movement occurred outside bounds " +
                            "of current screen element");
                    return true;
                default:
                    return super.onTouchEvent(event);
            }
        }
        return super.onTouchEvent(event);
/*
        Rectangle rectangle = new Rectangle(200, 300);
        rectangle.scale(4, 3);
        rectangle.translate(250,250);
        rectangles.add(rectangle);
        invalidate();
        return super.onTouchEvent(event);

 */
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("DEBUG", "drawing canvas");
        for (int i = 0; i < rectangles.size(); i++) {
            rectangles.get(i).draw(canvas, rectangles.get(i).brushColour);
        }
        for (int i = 0; i < circles.size(); i++) {
            circles.get(i).draw(canvas, circles.get(i).brushColour);
        }
        for (int i = 0; i < lines.size(); i++) {
            lines.get(i).draw(canvas, lines.get(i).brushColour);
        }
        /*
        // tell shapes to draw themselves
        rectangle.draw(canvas, red_brush);

         */
//        circle.draw(canvas, blue_brush);


    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
