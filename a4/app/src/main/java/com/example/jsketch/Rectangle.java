package com.example.jsketch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Rectangle {
    float x, y;
    float width, height;
    Matrix matrix = new Matrix(); // identity matrix
    Paint brushColour;

    // Construct a rectangle with the given dimensions
    // The matrix will be used to determine location (defaults to identity matrix)
    // By default, is drawn with the upper-left corner at the origin
    // Assumes: width and height are positive numbers
    Rectangle(float _width, float _height, String color) {
        x = 0;
        y = 0;
        width = _width;
        height = _height;
        brushColour = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (color == "blue") {
            brushColour.setColor(Color.BLUE);
        } else if (color == "red") {
            brushColour.setColor(Color.RED);
        } else if (color == "yellow") {
            brushColour.setColor(Color.YELLOW);
        } else if (color == "green") {
            brushColour.setColor(Color.GREEN);
        } else {
            brushColour.setColor(Color.WHITE);
        }

    }

    // Translate by dx, dy
    // Appends to the current matrix, so operations are cumulative
    void translate(float dx, float dy) {
        matrix.postTranslate(dx, dy);
    }

    // Scale by sx, sy
    // Appends to the current matrix, so operations are cumulative
    void scale(float sx, float sy) {
        matrix.postScale(sx, sy);
    }

    // Draw using the current matrix
    void draw(Canvas canvas, Paint paint) {
        Matrix oldMatrix = canvas.getMatrix();
        canvas.setMatrix(matrix);
        canvas.drawRect(x, y, x+width, y+height, paint);
        canvas.setMatrix(oldMatrix);
    }
}
