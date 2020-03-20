package edu.temple.colorpalette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

public class PaletteActivity extends AppCompatActivity implements PaletteFragment.OnItemSelectedInterface{

    PaletteFragment masterFragment;

    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        // Gets resources of the current activity.
        Resources res = this.getResources();
        // Gets the string array for the color ids from resources.
        String[] colors_id = res.getStringArray(R.array.color_ids);
        // Gets the string array for the color names form resources.
        String[] color_names = res.getStringArray(R.array.color_names);

        layout = findViewById(R.id.constraint_layout_palette_activity);

        // Makes new instance of PaletteFragment.
        masterFragment = PaletteFragment.newInstance(colors_id, color_names);

        // Use fragment manager to start transaction and add masterFragment to the FrameLayout, container1.
        getSupportFragmentManager().beginTransaction().add(R.id.container1, masterFragment).commit();
    }


    @Override
    public void itemClicked(String chosenColorId, String chosenColorName) {
        layout.setBackgroundColor(Color.parseColor(chosenColorId));
    }
}