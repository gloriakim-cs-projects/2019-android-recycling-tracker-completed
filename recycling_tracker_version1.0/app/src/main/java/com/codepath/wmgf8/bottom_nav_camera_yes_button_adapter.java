package com.codepath.wmgf8;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class bottom_nav_camera_yes_button_adapter extends BaseAdapter
{
    private Activity activity;
    private String[] filepath;
    private static LayoutInflater inflater = null;
    Bitmap bmp = null;

    public bottom_nav_camera_yes_button_adapter (Activity a, String[] fpath)
    {
        activity = a;
        filepath = fpath;
        inflater = (LayoutInflater)activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount ()
    {
        return filepath.length;
    }

    public Object getItem (int position)
    {
        return position;
    }

    public long getItemId (int position)
    {
        return position;
    }

    public View getView (int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.activity_bottom_nav_camera_yes_button_items, null);
        ImageView image = (ImageView)vi.findViewById(R.id.image_view);
        //int targetWidth = 400;
        //int targetHeight = 400;
        BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
        bmpOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filepath[position], bmpOptions);
        int currHeight = bmpOptions.outHeight;
        int currWidth = bmpOptions.outWidth;
        int sampleSize = 1;
        bmpOptions.inSampleSize = sampleSize;
        bmpOptions.inJustDecodeBounds = false;
        bmp = BitmapFactory.decodeFile(filepath[position], bmpOptions);
        image.setImageBitmap(bmp);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        bmp = null;
        return vi;
    }
}
