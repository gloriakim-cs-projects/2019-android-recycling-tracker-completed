package com.codepath.wmgf8;

import android.content.Context;
        import android.content.SharedPreferences;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.Calendar;

public class bottom_nav_record_trash_adapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private String[] numberWord;
    private int[] numberImage;
    private int recycle_num;

    public bottom_nav_record_trash_adapter(Context c, String[] numberWord, int[] numberImage, int recycle_num) {
        context = c;
        this.numberWord = numberWord;
        this.numberImage = numberImage;
        this.recycle_num = recycle_num;
    }

    @Override
    public int getCount() {
        return recycle_num;
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
            convertView = inflater.inflate(R.layout.activity_bottom_nav_record_trash_items, null);
        }

        ImageView imageView = convertView.findViewById(R.id.other_items);
        TextView textView = convertView.findViewById(R.id.other_items_num);


        Log.e("position", "position: " + position);
        imageView.setImageResource(numberImage[position]);
        textView.setText(numberWord[position]);

        return convertView;
    }
}
