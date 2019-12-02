package cs349.uwaterloo.ca.paperdollscenegraph;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inflate the view
        final DrawingView drawingView = new DrawingView(this.getBaseContext());
        ViewGroup viewGroup = findViewById(R.id.mainRegion);
        viewGroup.addView(drawingView);

        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("About")
                        .setMessage("Paper Doll Scene Graph\nNathan Hung\n20584462")
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawingView.onResetClick();
            }
        });
    }
}
