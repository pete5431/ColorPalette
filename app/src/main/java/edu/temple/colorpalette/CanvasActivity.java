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

        layout = findViewById(R.id.constraint_layout_canvas_activity);
        colorName = findViewById(R.id.text_canvas_color);

        String chosenColor = getIntent().getStringExtra("color");

        layout.setBackgroundColor(Color.parseColor(chosenColor));
    }
}
