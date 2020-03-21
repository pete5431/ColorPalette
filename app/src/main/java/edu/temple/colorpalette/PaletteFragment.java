package edu.temple.colorpalette;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;


public class PaletteFragment extends Fragment {

    // the fragment initialization parameters for color id array and color name array.
    private static final String COLOR_ID_ARRAY_TAG = "colorIdArray";
    private static final String COLOR_NAME_ARRAY_TAG = "colorNameArray";

    // Contains the color Ids for parseColor method of Color class.
    private String[] colorIdArray;
    // Contains the names of the colors.
    private String[] colorNameArray;

    // Adapter for the spinner.
    ColorAdapter colorAdapter;
    // The position of the selected item in the spinner. Passed to ColorAdapter to update position.
    int itemSelectedPosition = 0;

    // The interface to communicate with the parent, PaletteActivity.
    private OnItemSelectedInterface parentActivity;

    // Default constructor for PaletteFragment.
    public PaletteFragment() {
        // Required empty public constructor
    }

    public static PaletteFragment newInstance(String[] colorIdArray, String[] colorNameArray) {
        PaletteFragment fragment = new PaletteFragment();
        Bundle args = new Bundle();
        // Puts the id array and name array into the bundle with the corresponding tag.
        args.putStringArray(COLOR_ID_ARRAY_TAG, colorIdArray);
        args.putStringArray(COLOR_NAME_ARRAY_TAG, colorNameArray);
        // Set the arguments for the fragment.
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // If there are arguments.
        if (getArguments() != null) {
            // Get them from bundle by their tag.
            colorIdArray = getArguments().getStringArray(COLOR_ID_ARRAY_TAG);
            colorNameArray = getArguments().getStringArray(COLOR_NAME_ARRAY_TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the fragment layout in the specified container which is container1.
        View fragmentView = inflater.inflate(R.layout.fragment_palette, container, false);

        // Get reference to spinner.
        Spinner colorSpinner = fragmentView.findViewById(R.id.spinner_color);

        // Create new color adapter using the two arrays.
        colorAdapter = new ColorAdapter(this.getActivity(), colorIdArray, colorNameArray);

        // Set the adapter to the spinner.
        colorSpinner.setAdapter(colorAdapter);

        // Implement on item select event listener for color spinner.
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Since the initial view of the spinner is just a prompt, we do nothing when it is automatically selected.
                if(position == 0){
                    return;
                }
                else{
                    // Sets the selected position to the position.
                    itemSelectedPosition = position;
                    // Since the initial view (position 0) is ignored, we subtract one from subsequent positions.
                    position = position - 1;
                }

                // Get reference to text view displayed in spinner item, change to white upon being selected.
                view.findViewById(R.id.text_color).setBackgroundColor(Color.WHITE);

                // Set the item selected index variable of color adapter to the index.
                colorAdapter.setItemSelectedPosition(itemSelectedPosition);

                // Calls getItem of ColorAdapter for the data contained at the position of the string array.
                String chosenColorId = parent.getItemAtPosition(position).toString();

                // Get the chosen color name based off the position.
                String chosenColorName = colorAdapter.getColorName(position);

                // Call the itemClicked method of parent with the chosen color and id.
                parentActivity.itemClicked(chosenColorId, chosenColorName);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return fragmentView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedInterface) {
            parentActivity = (OnItemSelectedInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemSelectedInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parentActivity = null;
    }

    public interface OnItemSelectedInterface {
        void itemClicked(String chosenColorId, String chosenColorName);
    }
}
