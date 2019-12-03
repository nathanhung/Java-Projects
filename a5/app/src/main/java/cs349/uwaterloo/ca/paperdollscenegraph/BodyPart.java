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

    // body part name:
    String bodyPartString;
    // starting position:
    private float startX;
    private float startY;
    // for rotations:
    private Point pivotPoint;
    private Point startingPivotPoint;
    private float currDegree;
    private float maxDegree;
    private float minDegree;

    private Matrix myMatrix = new Matrix();
    private Paint paint;
    private Polygon polygon;

    private List <BodyPart> children = new ArrayList<>();

    BodyPart(String _bodyPartString, float _startX, float _startY, float _startPivotX, float _startPivotY) {
        bodyPartString = _bodyPartString;

        startX = _startX;
        startY = _startY;

        pivotPoint = new Point(_startPivotX, _startPivotY);
        startingPivotPoint = new Point(_startPivotX, _startPivotY);
        currDegree = 0;
        maxDegree = 0;
        minDegree = 0;

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);


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
            minDegree = -50;
            maxDegree = 50;
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
            minDegree = -135;
            maxDegree = 135;
        } else if (_bodyPartString.equals("rightLowerArm")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(50, 0))
                    .addVertex(new Point(50, 200))
                    .addVertex(new Point(0, 200)).build();
            minDegree = -135;
            maxDegree = 135;
        } else if (_bodyPartString.equals("leftHand")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(70, 0))
                    .addVertex(new Point(70, 70))
                    .addVertex(new Point(0, 70)).build();
            minDegree = -35;
            maxDegree = 35;
        } else if (_bodyPartString.equals("rightHand")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(70, 0))
                    .addVertex(new Point(70, 70))
                    .addVertex(new Point(0, 70)).build();
            minDegree = -35;
            maxDegree = 35;
        } else if (_bodyPartString.equals("leftUpperLeg")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(70, 0))
                    .addVertex(new Point(70, 250))
                    .addVertex(new Point(0, 250)).build();
            minDegree = -90;
            maxDegree = 90;
        } else if (_bodyPartString.equals("rightUpperLeg")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(70, 0))
                    .addVertex(new Point(70, 250))
                    .addVertex(new Point(0, 250)).build();
            minDegree = -90;
            maxDegree = 90;
        } else if (_bodyPartString.equals("leftLowerLeg")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(50, 0))
                    .addVertex(new Point(50, 150))
                    .addVertex(new Point(0, 150)).build();
            minDegree = -90;
            maxDegree = 90;
        } else if (_bodyPartString.equals("rightLowerLeg")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(50, 0))
                    .addVertex(new Point(50, 150))
                    .addVertex(new Point(0, 150)).build();
            minDegree = -90;
            maxDegree = 90;
        } else if (_bodyPartString.equals("leftFoot")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(100, 0))
                    .addVertex(new Point(100, 50))
                    .addVertex(new Point(0, 50)).build();
            minDegree = -35;
            maxDegree = 35;
        } else if (_bodyPartString.equals("rightFoot")) {
            polygon = Polygon.Builder()
                    .addVertex(new Point(0, 0))
                    .addVertex(new Point(100, 0))
                    .addVertex(new Point(100, 50))
                    .addVertex(new Point(0, 50)).build();
            minDegree = -35;
            maxDegree = 35;
        } else {
            // incorrect string provided
            Log.d("DEBUG", "Incorrect string body type provided");
        }
        myMatrix.postTranslate(_startX,_startY);
    }

    public void addChildBodyPart(BodyPart child) {
        this.children.add(child);
    }

    public void translate(float previousX, float previousY, float newX, float newY) {
        myMatrix.postTranslate(newX - previousX, newY - previousY);
        // reset the pivot point:
        this.pivotPoint.x = this.pivotPoint.x + (newX - previousX);
        this.pivotPoint.y = this.pivotPoint.y + (newY - previousY);
        // translate children:
        for (int i = 0; i < children.size(); i++) {
            children.get(i).translate(previousX, previousY, newX, newY);
        }
    }

    public void rotate(float previousX, float previousY, float newX, float newY) {
        double newXPolarCoord = Math.atan2(newY - pivotPoint.y, newX - pivotPoint.x);
        double newYPolarCoord = Math.atan2(previousY - pivotPoint.y, previousX - pivotPoint.x);
        float changeInDegree = (float) Math.toDegrees(newXPolarCoord - newYPolarCoord);

        // check if we can apply the rotation:
        if (!(bodyPartString.equals("leftUpperArm") || bodyPartString.equals("rightUpperArm"))) {
            if (currDegree + changeInDegree > maxDegree || currDegree + changeInDegree < minDegree) {
                return;
            }
        }
        currDegree = currDegree + changeInDegree;
        myMatrix.postTranslate((float) -pivotPoint.x, (float) -pivotPoint.y);
        myMatrix.postRotate(changeInDegree);
        myMatrix.postTranslate((float) pivotPoint.x, (float) pivotPoint.y);
        // rotate children
        for (int i = 0; i < children.size(); i++) {
            children.get(i).rotateChild(this.pivotPoint, changeInDegree);
        }
    }

    public void rotateChild (Point parentPivot, float changeInDegree) {
        myMatrix.postTranslate((float) -parentPivot.x, (float) -parentPivot.y);
        myMatrix.postRotate(changeInDegree);
        myMatrix.postTranslate((float) parentPivot.x, (float) parentPivot.y);

        // update the pivot point
        Matrix tempMatrix = new Matrix();
        tempMatrix.postTranslate((float) -parentPivot.x, (float) -parentPivot.y);
        tempMatrix.postRotate(changeInDegree);
        tempMatrix.postTranslate((float) parentPivot.x, (float) parentPivot.y);

        float[] points = {(float) pivotPoint.x, (float) pivotPoint.y};
        // apply matrix to these points, and writes the transformed points back to array
        tempMatrix.mapPoints(points);
        pivotPoint.x = points[0];
        pivotPoint.y = points[1];

        // apply rotation to children:
        for (int i = 0; i < children.size(); i++) {
            children.get(i).rotateChild(parentPivot, changeInDegree);
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
        myMatrix = new Matrix();
        myMatrix.postTranslate(startX, startY);
        currDegree = 0;
        this.pivotPoint.x = this.startingPivotPoint.x;
        this.pivotPoint.y = this.startingPivotPoint.y;
    }

    public Polygon getTransformedPolygon(Polygon myPolygon) {
        List<Line> sides = myPolygon.getSides();
        List<Point> points = new ArrayList<>();
        for(int i = 0; i < sides.size(); i++){
            points.add(sides.get(i).getStart());
        }
        float[] floatPoints = new float[points.size() * 2];
        for(int i = 0; i < points.size(); i++){
            floatPoints[i * 2] = (float) points.get(i).x;
            floatPoints[i * 2 + 1] = (float) points.get(i).y;
        }

        // apply matrix to these points, and writes the transformed points back to array
        myMatrix.mapPoints(floatPoints);

        Polygon.Builder pBuilder = new Polygon.Builder();
        for(int i = 0; i < points.size(); i++){
            pBuilder.addVertex(new Point(floatPoints[i * 2], floatPoints[i * 2 + 1]));
        }
        Polygon newPolygon = pBuilder.build();
        return newPolygon;
    }

    @TargetApi(29)
    public void draw(Canvas canvas) {
        Polygon p = getTransformedPolygon(polygon);
        List<Line> lines = p.getSides();
        for(int i = 0; i < lines.size(); i++){
            canvas.drawLine(
                    (float) lines.get(i).getStart().x,
                    (float) lines.get(i).getStart().y,
                    (float) lines.get(i).getEnd().x,
                    (float) lines.get(i).getEnd().y,
                    paint);
        }
    }
}
