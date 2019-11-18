package com.example.jsketch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Line {
    float xStart, yStart, xStop, yStop;
    //float radius;
    Matrix matrix = new Matrix(); // identity matrix
    Paint brushColour;

    // Construct a circle with the given dimensions
    // The matrix will be used to determine location (defaults to identity matrix)
    // By default, is drawn centred at the origin
    // Assumes: positive radius
    Line() {
        xStart = 0;
        yStart = 0;
        xStop = 50;
        yStop = 50;

        brushColour = new Paint(Paint.ANTI_ALIAS_FLAG);
        brushColour.setColor(Color.BLUE);
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
        canvas.drawLine(xStart, yStart, xStop, yStop, paint);
        canvas.setMatrix(oldMatrix);
    }
}
