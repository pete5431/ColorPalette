package edu.temple.colorpalette;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CanvasActivity extends AppCompatActivity {

    ConstraintLayout layout;
    TextView colorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        // Get references to layout and text view.
        layout = findViewById(R.id.constraint_layout_canvas_activity);
        colorName = findViewById(R.id.text_canvas_color);

        // Get the chosen color string from intent.
        String chosenColor = getIntent().getStringExtra("color");

        // Change the background color of the layout.
        layout.setBackgroundColor(Color.parseColor(chosenColor));

        colorName.setText(chosenColor);
    }
}
