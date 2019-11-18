package com.example.jsketch;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.Observable;
import java.util.Observer;


public class Model extends Observable {

    // toolbar buttons:
    public Boolean selectionBtnSelected = false;
    public Boolean eraseBtnSelected = false;
    public Boolean lineBtnSelected = false;
    public Boolean circleBtnSelected = false;
    public Boolean rectangleBtnSelected = false;

    // colour palette buttons:
    public Boolean redBtnSelected = false;
    public Boolean greenBtnSelected = false;
    public Boolean yellowBtnSelected = false;
    public Boolean blueBtnSelected = false;


    Model() {

    }

    public void onToolbarClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.selectionBtn:
                if (checked)
                    selectionBtnSelected = true;
                    eraseBtnSelected = false;
                    lineBtnSelected = false;
                    circleBtnSelected = false;
                    rectangleBtnSelected = false;
                    break;
            case R.id.eraseBtn:
                if (checked)
                    selectionBtnSelected = false;
                    eraseBtnSelected = true;
                    lineBtnSelected = false;
                    circleBtnSelected = false;
                    rectangleBtnSelected = false;
                    break;
            case R.id.lineBtn:
                if (checked)
                    selectionBtnSelected = false;
                    eraseBtnSelected = false;
                    lineBtnSelected = true;
                    circleBtnSelected = false;
                    rectangleBtnSelected = false;
                    break;
            case R.id.circleBtn:
                if (checked)
                    selectionBtnSelected = false;
                    eraseBtnSelected = false;
                    lineBtnSelected = false;
                    circleBtnSelected = true;
                    rectangleBtnSelected = false;
                    break;
            case R.id.rectanglebtn:
                if (checked)
                    selectionBtnSelected = false;
                    eraseBtnSelected = false;
                    lineBtnSelected = false;
                    circleBtnSelected = false;
                    rectangleBtnSelected = true;
                    break;

        }
    }

    public void onColourPaletteClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.redBtn:
                redBtnSelected = true;
                greenBtnSelected = false;
                yellowBtnSelected = false;
                blueBtnSelected = false;
                break;
            case R.id.greenBtn:
                redBtnSelected = false;
                greenBtnSelected = true;
                yellowBtnSelected = false;
                blueBtnSelected = false;
                break;
            case R.id.yellowBtn:
                redBtnSelected = false;
                greenBtnSelected = false;
                yellowBtnSelected = true;
                blueBtnSelected = false;
                break;
            case R.id.blueBtn:
                redBtnSelected = false;
                greenBtnSelected = false;
                yellowBtnSelected = false;
                blueBtnSelected = true;
                break;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Observable Methods
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Helper method to make it easier to initialize all observers
     */
    public void initObservers()
    {
        setChanged();
        notifyObservers();
    }

    /**
     * Deletes an observer from the set of observers of this object.
     * Passing <CODE>null</CODE> to this method will have no effect.
     *
     * @param o the observer to be deleted.
     */
    @Override
    public synchronized void deleteObserver(Observer o)
    {
        super.deleteObserver(o);
    }

    /**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     * The order in which notifications will be delivered to multiple
     * observers is not specified. See the class comment.
     *
     * @param o an observer to be added.
     * @throws NullPointerException if the parameter o is null.
     */
    @Override
    public synchronized void addObserver(Observer o)
    {
        super.addObserver(o);
    }

    /**
     * Clears the observer list so that this object no longer has any observers.
     */
    @Override
    public synchronized void deleteObservers()
    {
        super.deleteObservers();
    }

    /**
     * If this object has changed, as indicated by the
     * <code>hasChanged</code> method, then notify all of its observers
     * and then call the <code>clearChanged</code> method to
     * indicate that this object has no longer changed.
     * <p>
     * Each observer has its <code>update</code> method called with two
     * arguments: this observable object and <code>null</code>. In other
     * words, this method is equivalent to:
     * <blockquote><tt>
     * notifyObservers(null)</tt></blockquote>
     *
     * @see Observable#clearChanged()
     * @see Observable#hasChanged()
     * @see Observer#update(Observable, Object)
     */
    @Override
    public void notifyObservers()
    {
        super.notifyObservers();
    }

}
