package com.example.jsketch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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


    Rectangle currRectangle;
    Line currLine;
    Circle currCircle;

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
                    Line line;
                    if (this.model.blueBtnSelected) {
                        line = new Line(0, 0, 50, 50,"blue");
                    } else if (this.model.redBtnSelected) {
                        line = new Line(0, 0, 50, 50,"red");
                    } else if (this.model.yellowBtnSelected) {
                        line = new Line(0, 0, 50, 50,"yellow");
                    } else if (this.model.greenBtnSelected) {
                        line = new Line(0, 0, 50, 50,"green");
                    } else {
                        line = new Line(0, 0, 50, 50,"white");
                    }
                    //line.translate(event.getX(), event.getY());
                    lines.add(line);
                    currLine = line;
                    previousX = x;
                    previousY = y;
                    //invalidate();
                    return true;
                case (MotionEvent.ACTION_MOVE):
                    Log.d("DEBUG", "Action was MOVE");
//                    float dx = x / previousX;
//                    float dy = y / previousY;
//                    previousX = x;
//                    previousY = y;
//                    currLine.scale(dx,dy);
//                    invalidate();
                    return true;
                case (MotionEvent.ACTION_UP):
                    currLine.translate(x,y);
                    invalidate();
                    currLine = null;
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
                    Circle circle;
                    if (this.model.blueBtnSelected) {
                        circle = new Circle(50, "blue");
                    } else if (this.model.redBtnSelected) {
                        circle = new Circle(50,"red");
                    } else if (this.model.yellowBtnSelected) {
                        circle = new Circle(50, "yellow");
                    } else if (this.model.greenBtnSelected) {
                        circle = new Circle(50, "green");
                    } else {
                        circle = new Circle(50, "white");
                    }
                    //circle.translate(event.getX(), event.getY());
                    circles.add(circle);
                    currCircle = circle;
                    previousX = x;
                    previousY = y;
                    //invalidate();
                    return true;
                case (MotionEvent.ACTION_MOVE):
                    Log.d("DEBUG", "Action was MOVE");
                    float dx = x / previousX;
                    float dy = y / previousY;
                    previousX = x;
                    previousY = y;
                    currCircle.scale(dx,dy);
                    invalidate();
                    return true;
                case (MotionEvent.ACTION_UP):
                    Log.d("DEBUG", "Action was UP");
                    currCircle.translate(x,y);
                    invalidate();
                    currCircle = null;
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
                    Rectangle rectangle;
                    if (this.model.blueBtnSelected) {
                        rectangle = new Rectangle(50, 50, "blue");
                    } else if (this.model.redBtnSelected) {
                        rectangle = new Rectangle(50, 50, "red");
                    } else if (this.model.yellowBtnSelected) {
                        rectangle = new Rectangle(50, 50, "yellow");
                    } else if (this.model.greenBtnSelected) {
                        rectangle = new Rectangle(50, 50, "green");
                    } else {
                        rectangle = new Rectangle(50, 50, "white");
                    }
                    //rectangle.translate(event.getX(), event.getY());
                    rectangles.add(rectangle);
                    currRectangle = rectangle;
                    previousX = x;
                    previousY = y;
                    //invalidate();
                    return true;
                case (MotionEvent.ACTION_MOVE):
                    Log.d("DEBUG", "Action was MOVE");
                    float dx = x / previousX;
                    float dy = y / previousY;
                    previousX = x;
                    previousY = y;
                    currRectangle.scale(dx,dy);
                    invalidate();
                    //rectangles.get(rectangles.size() - 1)
                    return true;
                case (MotionEvent.ACTION_UP):
//                    float dx = x - previousX;
//                    float dy = y - previousY;
                    currRectangle.translate(x,y);
                    currRectangle = null;
                    invalidate();
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


    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
