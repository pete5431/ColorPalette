package edu.temple.colorpalette;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ColorAdapter extends BaseAdapter {

    private Context context;
    private String[] colors;
    // The selected item position.
    private int itemSelectedPosition = 0;

    public ColorAdapter(Context c, String[] colors){
        this.colors = colors;
        this.context = c;
    }

    // Sets the selected item position to the passed position value.
    public void setItemSelectedPosition(int position){
        itemSelectedPosition = position;
    }

    @Override
    public int getCount() {
        // The size will be +1 because an extra initial view is included.
        return colors.length + 1;
    }

    @Override
    public Object getItem(int position) {
        return colors[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // If position is 0, return an initial view.
        if(position == 0){
            return getInitialView(false);
        }else
        // Returns the view that will act as the item for the spinner.
        return getNormalView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        // If position is 0, return an initial view with if_drop set to true.
        if(position == 0){
            return getInitialView(true);
        }else {
            // Generate a normal view.
            View view = getNormalView(position, convertView, parent);
            // If the selected position is equal to the position, set the text view background to white.
            if (itemSelectedPosition == position) {
                view.findViewById(R.id.text_color).setBackgroundColor(Color.WHITE);
            }
            return view;
        }
    }

    // Returns an initial view that acts as the first item of the spinner.
    public View getInitialView(boolean if_drop){
        // The initial view will be a simple text view.
        TextView initial = new TextView(context);

        // If the view is being dropped in dropdown view, make the height 0 so the user can't see it.
        if(if_drop) {
            initial.setHeight(0);
        }
        else{
            // Set the appearance of the initial prompt.
            initial.setText(R.string.spinner_prompt);
            initial.setTextSize(20);
            initial.setBackgroundColor(Color.LTGRAY);
        }
        return initial;
    }

    // Returns normal view that is not an initial view.
    public View getNormalView(int position, View convertView, ViewGroup parent){

        View view;

        // Makes sure the convertView is not null or not an initial text view before recycling.
        if(convertView == null || convertView instanceof TextView){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.spinner_color_item, parent, false);
        }
        else view = convertView;

        // Get the text view from the spinner item layout.
        TextView colorText = view.findViewById(R.id.text_color);

        // Since the initial position 0 is ignored and a special view is returned, we subtract one from subsequent positions.
        position = position - 1;

        // Set the text of the text view to the color name.
        colorText.setText(colors[position]);

        // Set the background color of the text view.
        colorText.setBackgroundColor(Color.parseColor(colors[position]));

        return view;
    }
}