package cs349.uwaterloo.ca.paperdollscenegraph;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import com.snatik.polygon.Line;
import com.snatik.polygon.Point;
import com.snatik.polygon.Polygon;

import java.util.ArrayList;
import java.util.List;

public class BodyPart {


    float startX;
    float startY;
    Point pivotPoint;
    Point startingPivotPoint; // (anchor)
    String bodyPartString;
    //float width, height;
    Matrix myMatrix = new Matrix();
    Paint paint;



    Polygon polygon;
    float currDegree;


    List <BodyPart> children = new ArrayList<>();

    BodyPart(String _bodyPartString, float _startX, float _startY, float _startPivotX, float _startPivotY) {
        startX = _startX;
        startY = _startY;
        pivotPoint = new Point(_startPivotX, _startPivotY);
        startingPivotPoint = new Point(_startPivotX, _startPivotY);

        bodyPartString = _bodyPartString;

        if (_bodyPartString.equals("torso")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(300, 0))
                    .addVertex(new Point(300, 425))
                    .addVertex(new Point(0, 425)).build();
        } else if (_bodyPartString.equals("head")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(150, 0))
                    .addVertex(new Point(150, 150))
                    .addVertex(new Point(0, 150)).build();
        } else if (_bodyPartString.equals("leftUpperArm")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(200, 0))
                    .addVertex(new Point(200, 50))
                    .addVertex(new Point(0, 50)).build();
        } else if (_bodyPartString.equals("rightUpperArm")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(200, 0))
                    .addVertex(new Point(200, 50))
                    .addVertex(new Point(0, 50)).build();
        } else if (_bodyPartString.equals("leftLowerArm")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(50, 0))
                    .addVertex(new Point(50, 200))
                    .addVertex(new Point(0, 200)).build();
        } else if (_bodyPartString.equals("rightLowerArm")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(50, 0))
                    .addVertex(new Point(50, 200))
                    .addVertex(new Point(0, 200)).build();
        } else if (_bodyPartString.equals("leftHand")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(70, 0))
                    .addVertex(new Point(70, 70))
                    .addVertex(new Point(0, 70)).build();
        } else if (_bodyPartString.equals("rightHand")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(70, 0))
                    .addVertex(new Point(70, 70))
                    .addVertex(new Point(0, 70)).build();
        } else if (_bodyPartString.equals("leftUpperLeg")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(70, 0))
                    .addVertex(new Point(70, 250))
                    .addVertex(new Point(0, 250)).build();
        } else if (_bodyPartString.equals("rightUpperLeg")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(70, 0))
                    .addVertex(new Point(70, 250))
                    .addVertex(new Point(0, 250)).build();
        } else if (_bodyPartString.equals("leftLowerLeg")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(50, 0))
                    .addVertex(new Point(50, 150))
                    .addVertex(new Point(0, 150)).build();
        } else if (_bodyPartString.equals("rightLowerLeg")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(50, 0))
                    .addVertex(new Point(50, 150))
                    .addVertex(new Point(0, 150)).build();
        } else if (_bodyPartString.equals("leftFoot")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(100, 0))
                    .addVertex(new Point(100, 50))
                    .addVertex(new Point(0, 50)).build();
        } else if (_bodyPartString.equals("rightFoot")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(100, 0))
                    .addVertex(new Point(100, 50))
                    .addVertex(new Point(0, 50)).build();
        } else {
            // incorrect string provided
            Log.d("DEBUG", "Incorrect string body type provided");
        }

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        currDegree = 0;
        myMatrix.postTranslate(_startX,_startY);
    }

    public void addChildBodyPart(BodyPart child) {
        this.children.add(child);
    }

    public List<Point> getPoints(Polygon p){
        List<Line> sides = p.getSides();
        List<Point> points = new ArrayList<>();

        for(int i = 0; i < sides.size(); i++){
            points.add(sides.get(i).getStart());
        }
        return points;
    }

    public Polygon transformAffine(Polygon s) {
        List<Point> pts = getPoints(s);

        float[] fpts = new float[pts.size() * 2];
        for(int i = 0; i < pts.size(); i++){
            fpts[i * 2] = (float) pts.get(i).x;
            fpts[i * 2 + 1] = (float) pts.get(i).y;
        }

        myMatrix.mapPoints(fpts);

        List<Point> tpts = new ArrayList<>();
        for(int i = 0; i < pts.size(); i++){
            tpts.add(new Point(fpts[i * 2], fpts[i * 2 + 1]));
        }

        Polygon.Builder pbuilder = new Polygon.Builder();
        for(int i = 0; i < tpts.size(); i++){
            pbuilder.addVertex(tpts.get(i));
        }

        Polygon p = pbuilder.build();
        return p;
    }

    public void translate(float previousX, float previousY, float newX, float newY) {
        myMatrix.postTranslate(newX - previousX, newY - previousY);

        if (!this.bodyPartString.equals("torso")) {
            //if not torso, move the pivot point as well
        }

        // translate children:
        for (int i = 0; i < children.size(); i++) {
            children.get(i).translate(previousX, previousY, newX, newY);
        }
    }

    public boolean contains(float x, float y) {
        Matrix inverseMatrix = new Matrix();
        inverseMatrix.set(myMatrix);
        inverseMatrix.invert(inverseMatrix);

        float[] clickLocation = {x, y};
        inverseMatrix.mapPoints(clickLocation);

        if (this.polygon.contains(new Point(clickLocation[0], clickLocation[1]))) {
            return true;
        } else {
            return false;
        }
    }

    // called when reset button clicked:
    public void reset() {
        currDegree = 0;
        myMatrix = new Matrix();
        myMatrix.postTranslate(startX, startY);

        // reset the pivot point for non-torso body parts:

        /*
        for (int i = 0; i < children.size(); i++) {
            children.get(i).reset();
        }

         */
    }

    // put in abstract class
    @TargetApi(29)
    public void draw(Canvas canvas) {
        /*
        Matrix oldMatrix = canvas.getMatrix();
        oldMatrix.postConcat(myMatrix);
        canvas.setMatrix(myMatrix);
        canvas.drawRoundRect(x, y, x+width, y + height, 15, 15, paint);

         */
        Polygon p = transformAffine(polygon);
        List<Line> lines = p.getSides();

        for(int i = 0; i < lines.size(); i++){
            canvas.drawLine((float) lines.get(i).getStart().x,
                    (float) lines.get(i).getStart().y,
                    (float) lines.get(i).getEnd().x,
                    (float) lines.get(i).getEnd().y,
                    paint);
        }
        for (BodyPart child : children) {
            //child.draw(canvas);
        }
        //canvas.setMatrix(oldMatrix);
    }





}
