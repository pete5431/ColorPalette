package edu.temple.colorpalette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class PaletteActivity extends AppCompatActivity {

    ColorAdapter colorAdapter;
    String[] colors;
    Resources res;
    ConstraintLayout layout;
    Context context;
    // The position of the selected item for the spinner. Starts at 0 because array starts at index 0.
    int itemSelectedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        // Get the spinner and the layout from resources.
        Spinner colorSpinner = findViewById(R.id.spinner_color);
        layout = findViewById(R.id.constraint_layout_color_activity);

        // Gets the string array from resources and set it equal to string array colors.
        res = getResources();
        colors = res.getStringArray(R.array.color_names);

        // Create new color adapter using the string array.
        colorAdapter = new ColorAdapter(this, colors);

        // Set the adapter of color spinner to color adapter.
        colorSpinner.setAdapter(colorAdapter);

        // Implement on item select event listener for color spinner.
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Set the text view color to white upon being selected.
                view.findViewById(R.id.text_color).setBackgroundColor(Color.WHITE);

                // Sets the selected index to the position.
                itemSelectedIndex = position;
                // Set the item selected index variable of color adapter to the index.
                colorAdapter.setItemSelectedIndex(itemSelectedIndex);

                // Get the color string of the chosen item from spinner.
                String chosenColor = parent.getSelectedItem().toString();
                // Set the layout background color to that color by parsing the string.
                layout.setBackgroundColor(Color.parseColor(chosenColor));


                // Declares the intent for the canvas activity.
                Intent intent = new Intent(context, CanvasActivity.class);
                // Puts the string chosen color into the hash table to be passed to the next activity.
                intent.putExtra("color", chosenColor);
                // Starts the new activity.
                context.startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

}