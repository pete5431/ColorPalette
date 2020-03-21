package edu.temple.colorpalette;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


public class CanvasFragment extends Fragment{

    // the fragment initialization parameters. One for chosen color id, and another for chosen color name.
    private static final String CHOSEN_COLOR_ID_TAG = "chosenColorId";
    private static final String CHOSEN_COLOR_NAME_TAG = "chosenColorName";

    // The chosen color id is the parsable id string of the color that was picked.
    private String chosenColorId;
    // The chosen color name is the corresponding actual name (depending on language) of the color picked.
    private String chosenColorName;

    public CanvasFragment() {
        // Required empty public constructor
    }

    public static CanvasFragment newInstance(String chosenColorId, String chosenColorName) {
        CanvasFragment fragment = new CanvasFragment();
        Bundle args = new Bundle();
        // Add the chosen id and name to arguments using respective tags.
        args.putString(CHOSEN_COLOR_ID_TAG, chosenColorId);
        args.putString(CHOSEN_COLOR_NAME_TAG, chosenColorName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            chosenColorId = getArguments().getString(CHOSEN_COLOR_ID_TAG);
            chosenColorName = getArguments().getString(CHOSEN_COLOR_NAME_TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        View fragmentView = inflater.inflate(R.layout.fragment_canvas, container, false);

        // Get reference to the text view of the fragment.
        TextView colorText = fragmentView.findViewById(R.id.text_canvas_color);

        // Get reference to the layout of the fragment.
        FrameLayout canvasLayout = fragmentView.findViewById(R.id.frame_layout_canvas);

        // Set the background of the fragment layout to the chosen color.
        canvasLayout.setBackgroundColor(Color.parseColor(chosenColorId));

        // Set the text of the text view to the chosen color.
        colorText.setText(chosenColorName);

        return fragmentView;
    }

}
