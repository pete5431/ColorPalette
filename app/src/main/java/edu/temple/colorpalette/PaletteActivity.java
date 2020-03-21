package edu.temple.colorpalette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class PaletteActivity extends AppCompatActivity implements PaletteFragment.OnItemSelectedInterface{

    // The master fragment that holds the spinner.
    PaletteFragment masterFragment;
    // The details fragment for showing the color name and the background as that color.
    CanvasFragment canvasFragment;
    // The container for the details fragment.
    FrameLayout container2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        // Get the reference to container2.
        container2 = findViewById(R.id.container2);

        // Set the visibility of the container to GONE as it is not in use right now.
        container2.setVisibility(View.GONE);

        // Gets resources of the current activity.
        Resources res = this.getResources();
        // Gets the string array for the color ids from resources.
        String[] colors_id = res.getStringArray(R.array.color_ids);
        // Gets the string array for the color names form resources.
        String[] color_names = res.getStringArray(R.array.color_names);

        // Makes new instance of PaletteFragment.
        masterFragment = PaletteFragment.newInstance(colors_id, color_names);

        // Use fragment manager to start transaction and add masterFragment to the FrameLayout, container1.
        getSupportFragmentManager().beginTransaction().add(R.id.container1, masterFragment).commit();
    }


    @Override
    public void itemClicked(String chosenColorId, String chosenColorName) {

        // The container is active now, therefore set to VISIBLE.
        container2.setVisibility(View.VISIBLE);

        // Create a new instance of canvas fragment.
        canvasFragment = CanvasFragment.newInstance(chosenColorId, chosenColorName);

        // Start a fragment transaction to replace the fragment currently in container and add to back stack, then commit.
        getSupportFragmentManager().beginTransaction().replace(R.id.container2, canvasFragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed(){
        // If there are entries in the current back stack.
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            // The frame layout container2 for canvas fragment should always be non-interactive unless displayed.
            container2.setVisibility(View.GONE);
            // Pop the fragment from back stack, and destroy it.
            getSupportFragmentManager().popBackStack();
        }
        else{
            // Call the parent onBackPressed.
            super.onBackPressed();
        }
    }
}