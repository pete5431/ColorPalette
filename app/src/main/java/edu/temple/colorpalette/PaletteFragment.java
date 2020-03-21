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

    private String[] colorIdArray;
    private String[] colorNameArray;

    ColorAdapter colorAdapter;
    int itemSelectedPosition = 0;

    private OnItemSelectedInterface parentActivity;

    public PaletteFragment() {
        // Required empty public constructor
    }

    public static PaletteFragment newInstance(String[] colorIdArray, String[] colorNameArray) {
        PaletteFragment fragment = new PaletteFragment();
        Bundle args = new Bundle();
        args.putStringArray(COLOR_ID_ARRAY_TAG, colorIdArray);
        args.putStringArray(COLOR_NAME_ARRAY_TAG, colorNameArray);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            colorIdArray = getArguments().getStringArray(COLOR_ID_ARRAY_TAG);
            colorNameArray = getArguments().getStringArray(COLOR_NAME_ARRAY_TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_palette, container, false);

        Spinner colorSpinner = fragmentView.findViewById(R.id.spinner_color);

        colorAdapter = new ColorAdapter(this.getActivity(), colorIdArray, colorNameArray);

        colorSpinner.setAdapter(colorAdapter);

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    return;
                }
                else{
                    itemSelectedPosition = position;
                    position = position - 1;
                }

                view.findViewById(R.id.text_color).setBackgroundColor(Color.WHITE);

                // Set the item selected index variable of color adapter to the index.
                colorAdapter.setItemSelectedPosition(itemSelectedPosition);

                // Calls getItem of ColorAdapter for the data contained at the position of the string array.
                String chosenColorId = parent.getItemAtPosition(position).toString();

                // Get the chosen color name based off the position.
                String chosenColorName = colorAdapter.getColorName(position);

                container.setBackgroundColor(Color.parseColor(chosenColorId));

                parentActivity.itemClicked(chosenColorId, chosenColorName);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Inflate the layout for this fragment
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
