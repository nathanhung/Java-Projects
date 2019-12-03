package cs349.uwaterloo.ca.paperdollscenegraph;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawingView extends View {
    // array of all body parts:
    List<BodyPart> bodyParts = new ArrayList<>();

    // keep track of previous click location
    float previousX, previousY;

    // keep track of currently selected body parts:
    BodyPart currSelectedPart = null;

    public DrawingView(Context context) {
        super(context);

        // init each body part torso: 1100, 300 (to do: starting pivot point)
        BodyPart torso = new BodyPart("torso", 1100, 300,0,0);
        BodyPart head = new BodyPart("head", 1175,150, 1250, 300);

        // arms and hands
        BodyPart leftUpperArm = new BodyPart("leftUpperArm", 900, 300, 1100, 325);
        BodyPart rightUpperArm = new BodyPart("rightUpperArm", 1400, 300, 1400, 325);
        BodyPart leftLowerArm = new BodyPart("leftLowerArm", 900, 350, 925, 350);
        BodyPart rightLowerArm = new BodyPart("rightLowerArm", 1550, 350, 1575, 350);
        BodyPart leftHand = new BodyPart("leftHand", 890, 550, 925, 550);
        BodyPart rightHand = new BodyPart("rightHand", 1540, 550, 1575, 550);

        // legs + feet
        BodyPart leftUpperLeg = new BodyPart("leftUpperLeg", 1145, 725, 1180, 725);
        BodyPart rightUpperLeg = new BodyPart("rightUpperLeg", 1280, 725, 1315, 725);
        BodyPart leftLowerLeg = new BodyPart("leftLowerLeg", 1155, 975, 1180, 975);
        BodyPart rightLowerLeg = new BodyPart("rightLowerLeg", 1290, 975, 1315, 975);
        BodyPart leftFoot = new BodyPart("leftFoot",1105, 1125, 1180, 1125);
        BodyPart rightFoot = new BodyPart("rightFoot", 1290, 1125, 1315, 1125);

        // add body parts
        bodyParts.add(torso);
        bodyParts.add(head);
        bodyParts.add(leftUpperArm);
        bodyParts.add(rightUpperArm);
        bodyParts.add(leftUpperLeg);
        bodyParts.add(rightUpperLeg);
        bodyParts.add(leftLowerArm);
        bodyParts.add(rightLowerArm);
        bodyParts.add(leftLowerLeg);
        bodyParts.add(rightLowerLeg);
        bodyParts.add(leftHand);
        bodyParts.add(rightHand);
        bodyParts.add(leftFoot);
        bodyParts.add(rightFoot);

        // add children to applicable body parts
        torso.addChildBodyPart(head);
        torso.addChildBodyPart(leftUpperArm);
        torso.addChildBodyPart(rightUpperArm);
        torso.addChildBodyPart(leftUpperLeg);
        torso.addChildBodyPart(rightUpperLeg);

        leftUpperArm.addChildBodyPart(leftLowerArm);
        leftLowerArm.addChildBodyPart(leftHand);
        rightUpperArm.addChildBodyPart(rightLowerArm);
        rightLowerArm.addChildBodyPart(rightHand);

        leftUpperLeg.addChildBodyPart(leftLowerLeg);
        leftLowerLeg.addChildBodyPart(leftFoot);
        rightUpperLeg.addChildBodyPart(rightLowerLeg);
        rightLowerLeg.addChildBodyPart(rightFoot);

        // refresh the canvas
        invalidate();

    }

    // called when reset button clicked
    public void onResetClick() {
        // reset every body part to starting location
        for (int i = 0; i < bodyParts.size(); i++) {
            bodyParts.get(i).reset();
        }
        invalidate();
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("DEBUG", "touch " + event.getX() + "," + event.getY());

        float x = event.getX();
        float y = event.getY();

        int action = event.getActionMasked();

        switch(action) {
            case (MotionEvent.ACTION_DOWN):
                for (int i = bodyParts.size() - 1; i >= 0; i--) {
                    // if touch event in a body part, then set the currently selected body part
                    if (bodyParts.get(i).contains(x, y)) {
                        // if we clicked a body part
                        currSelectedPart = bodyParts.get(i);
                        break;
                    }
                }
                previousX = x;
                previousY = y;
                break;
            case (MotionEvent.ACTION_MOVE):
                if (currSelectedPart == null) {
                    return true;
                } else if (currSelectedPart.bodyPartString.equals("torso")) {
                    currSelectedPart.translate(previousX, previousY, x, y);
                } else {
                    // non-torso body part was selected and moved (rotate)
                    currSelectedPart.rotate(previousX, previousY, x, y);
                }
                previousX = x;
                previousY = y;
                invalidate();
                break;
            case (MotionEvent.ACTION_UP):
                currSelectedPart = null;
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < bodyParts.size(); i++) {
            bodyParts.get(i).draw(canvas);
        }
    }
}
