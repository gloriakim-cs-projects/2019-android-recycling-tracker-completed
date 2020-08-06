package com.codepath.wmgf8;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class top_nav_collect_adapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private String[] numberWord;
    private int[] numberImage;
    private int daily_award_count;

    public top_nav_collect_adapter(Context c, String[] numberWord, int[] numberImage, int daily_award_count) {
        context = c;
        this.numberWord = numberWord;
        this.numberImage = numberImage;
        this.daily_award_count = daily_award_count;
    }

    @Override
    public int getCount() {
        return daily_award_count;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_top_nav_collect_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.day_image);
        TextView textView = convertView.findViewById(R.id.day_text);


        imageView.setImageResource(numberImage[position]);
        textView.setText(numberWord[position]);

        return convertView;
    }
}
