package com.codepath.wmgf8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class bottom_nav_camera_no_button_adapter extends BaseAdapter implements Filterable {
    private Context context;
    private LayoutInflater inflater;
    private List<ExampleItem> exampleList;
    private List<ExampleItem> exampleListFull;

    public bottom_nav_camera_no_button_adapter(Context c, List<ExampleItem> exampleList) {
        context = c;
        this.exampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
    }

    @Override
    public int getCount() {
        return exampleList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_bottom_nav_search_items, null);
        }

        ImageButton imageButton = convertView.findViewById(R.id.image_button);
        ImageView imageView = convertView.findViewById(R.id.image_view);
        final TextView textView = convertView.findViewById(R.id.text_view1);

        final ExampleItem currentItem = exampleList.get(position);

        imageView.setImageResource(currentItem.getImageResource());
        textView.setText(currentItem.getText1());

        final String text = textView.getText().toString();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,bottom_nav_camera_no_button_details.class);
                i.putExtra("label", text); //e.g., "Furniture"
                context.startActivity(i);
            }
        });

        return convertView;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ExampleItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ExampleItem item : exampleListFull) {
                    if (item.getText1().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
