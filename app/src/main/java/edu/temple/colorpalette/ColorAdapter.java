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
    private int itemSelectedIndex = 0;

    public ColorAdapter(Context c, String[] colors){
        this.colors = colors;
        this.context = c;
    }

    // Sets the selected item position to the passed index value.
    public void setItemSelectedIndex(int index){
        itemSelectedIndex = index;
    }

    @Override
    public int getCount() {
        return colors.length;
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

        View view;

        // Makes sure the views are recycled instead generating new ones each time.
        // Makes sure the convertView is not null before recycling.
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.spinner_color_item, parent, false);
        }
        else view = convertView;

        // Get the text view from the spinner item layout.
        TextView colorText = view.findViewById(R.id.text_color);

        // Set the text of the text view to the color name.
        colorText.setText(colors[position]);

        // Set the top view to be always white because it is selected.
        colorText.setBackgroundColor(Color.parseColor(colors[position]));

        // Returns the view that will act as the item for the spinner.
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        // Get the view from getView, this is to avoid repetitive code.
        View view = getView(position, convertView, parent);

        TextView colorText = view.findViewById(R.id.text_color);

        // If the position is the selected item, set text view background to white, else set to the color.
        if(position == itemSelectedIndex){
            colorText.setBackgroundColor(Color.WHITE);
        }else colorText.setBackgroundColor(Color.parseColor(colors[position]));

        return view;
    }
}