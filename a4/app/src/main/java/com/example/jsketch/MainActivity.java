package com.example.jsketch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.*;



public class MainActivity extends AppCompatActivity {

    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Model
        model = new Model();

        ////////////////////////////////////////
        // Setup Views
        ////////////////////////////////////////
        ToolbarView toolbarView = new ToolbarView(this.getBaseContext(), model);
        ViewGroup v1 = findViewById(R.id.toolbar);
        v1.addView(toolbarView);

        CanvasView canvasView = new CanvasView(this.getBaseContext(), model);
        ViewGroup v2 = findViewById(R.id.canvas);
        v2.addView(canvasView);


        model.initObservers();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Save and Restore State
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Save the state of the application
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        //Log.d(String.valueOf(R.string.DEBUG_MVC_ID), "MainActivity: Save model state.");

        // Serialize all sateful values form model
        //outState.putInt("Counter", model.getCounter());
    }

    /**
     * This method is called after {@link #onStart} when the activity is
     * being re-initialized from a previously saved state, given here in
     * <var>savedInstanceState</var>.  Most implementations will simply use {@link #onCreate}
     * to restore their state, but it is sometimes convenient to do it here
     * after all of the initialization has been done or to allow subclasses to
     * decide whether to use your default implementation.  The default
     * implementation of this method performs a restore of any view state that
     * had previously been frozen by {@link #onSaveInstanceState}.
     * <p>
     * <p>This method is called between {@link #onStart} and
     * {@link #onPostCreate}.
     *
     * @param savedInstanceState the data most recently supplied in {@link #onSaveInstanceState}.
     * @see #onCreate
     * @see #onPostCreate
     * @see #onResume
     * @see #onSaveInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        //Log.d(String.valueOf(R.string.DEBUG_MVC_ID), "MainActivity: Restore model");

        // Get all values and restore them to model
        //model.setCounter(savedInstanceState.getInt("Counter"));
    }


}
