package edu.temple.colorpalette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class PaletteActivity extends AppCompatActivity {

    ColorAdapter colorAdapter;
    ConstraintLayout layout;
    // The position of the selected item for the spinner.
    int itemSelectedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        // Get the spinner and the layout from resources.
        Spinner colorSpinner = findViewById(R.id.spinner_color);
        layout = findViewById(R.id.constraint_layout_palette_activity);

        // Gets resources of the current activity.
        Resources res = this.getResources();
        // Gets the string array for the color ids from resources.
        String[] colors_id = res.getStringArray(R.array.color_ids);
        // Gets the string array for the color names form resources.
        String[] color_names = res.getStringArray(R.array.color_names);

        // Create new color adapter using the string array.
        colorAdapter = new ColorAdapter(this, colors_id, color_names);

        // Set the adapter of color spinner to color adapter.
        colorSpinner.setAdapter(colorAdapter);

        // Implement on item select event listener for color spinner.
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Since the initial view of the spinner is just a prompt, we do nothing when it is automatically selected.
                if(position == 0){
                    return;
                }else{
                    // Sets the selected position to the position.
                    itemSelectedPosition = position;
                    // Since the initial view (position 0) is ignored, we subtract one from subsequent positions.
                    position = position - 1;
                }

                // Set the text view color to white upon being selected.
                view.findViewById(R.id.text_color).setBackgroundColor(Color.WHITE);

                // Set the item selected index variable of color adapter to the index.
                colorAdapter.setItemSelectedPosition(itemSelectedPosition);

                // Calls getItem of ColorAdapter for the data contained at the position of the string array.
                String chosenColorId = parent.getItemAtPosition(position).toString();

                // Get the chosen color name based off the position.
                String chosenColorName = colorAdapter.getColorName(position);

                // Set the layout background color to that color by parsing the string.
                layout.setBackgroundColor(Color.parseColor(chosenColorId));
                // Start the canvas activity.
                startCanvasActivity(chosenColorId, chosenColorName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void startCanvasActivity(String chosenColor, String chosenColorName){
        // Declares the intent for the canvas activity.
        Intent intent = new Intent(this, CanvasActivity.class);
        // Puts the string chosen color id into the hash table to be passed to the next activity.
        intent.putExtra("color_id", chosenColor);
        // Puts the string chosen color name into the hash table to be passed to the next activity.
        intent.putExtra("color_name", chosenColorName);
        // Starts the new activity.
        this.startActivity(intent);
    }
}