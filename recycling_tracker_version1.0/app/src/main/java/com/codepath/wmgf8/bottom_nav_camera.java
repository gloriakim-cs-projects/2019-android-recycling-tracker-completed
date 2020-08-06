package com.codepath.wmgf8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.tensorflow.lite.Interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class bottom_nav_camera extends AppCompatActivity {

    public static final int IMAGE_GALLERY_REQUEST = 20;
    public static final int CAMERA_REQUEST_CODE = 8;

    ImageView imageView;
    Button yes_button, no_button;
    TextView label1, confidence1;

    ///////////////////
    /*start of tflite*/
    ///////////////////
    Interpreter tflite;
    // presets for rgb conversion
    private static final int RESULTS_TO_SHOW = 1;
    private static final int IMAGE_MEAN = 128;
    private static final float IMAGE_STD = 128.0f;
    // holds all the possible labels for model
    private List<String> labelList;
    // holds the selected image data as bytes
    private ByteBuffer imgData = null;
    // holds the probabilities of each label for non-quantized graphs
    private float[][] labelProbArray = null;
    // holds the probabilities of each label for quantized graphs
    private byte[][] labelProbArrayB = null;
    // array that holds the labels with the highest probabilities
    private String[] topLabels = null;
    // array that holds the highest probabilities
    private String[] topConfidence = null;

    // int array to hold image data
    private int[] intValues;

    // input image dimensions for the Mobile Net V2 Model
    private int DIM_IMG_SIZE_X = 224;
    private int DIM_IMG_SIZE_Y = 224;
    private int DIM_PIXEL_SIZE = 3;

    // priority queue that will hold the top results from the CNN
    private PriorityQueue<Map.Entry<String, Float>> sortedLabels =
            new PriorityQueue<>(
                    RESULTS_TO_SHOW,
                    new Comparator<Map.Entry<String, Float>>() {
                        @Override
                        public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                            return (o1.getValue()).compareTo(o2.getValue());
                        }
                    });
    ///////////////////
    /*end of tflite*/
    ///////////////////

    private static final String TAG = "MyActivity";
    private static final String MODEL_PATH = "recycling.tflite";
    private static final String LABEL_PATH = "label.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_camera);

        //open camera
        openCamera();

        imageView = (ImageView) findViewById(R.id.item_image);
        label1 = (TextView) findViewById(R.id.item_name);
        confidence1 = (TextView) findViewById(R.id.item_percent);
        yes_button = (Button) findViewById(R.id.yes_button);
        no_button = (Button) findViewById(R.id.no_button);

        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSave();
                pointCounter();
                //move to yes_button activity
                Intent int_yes = new Intent(bottom_nav_camera.this, bottom_nav_camera_yes_button.class);
                //passing the topLabels[0] to yes_button activity
                int_yes.putExtra("topLabels", topLabels[0]);
                startActivity(int_yes);
            }
        });

        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_no = new Intent(bottom_nav_camera.this, bottom_nav_camera_no_button.class);
                startActivity(int_no);
            }
        });

    }

    public void pointCounter() {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        int lastDay = settings.getInt("day",0);
        int lastWeek = settings.getInt("week", 0);
        SharedPreferences.Editor editor = settings.edit();

        //FIXME: Please changes all the below oic_array in: (1) bottom_nav_record.java, (2) bottom_nav_search.java, (3) bottom_nav_search_details.java (4) bottom_nav_camera.java, (5) top_nav_goal.java
        String[] oic_array = {"Aluminum Can", "Backpack", "Ball", "Bicycle", "Binder", "Candy Wrapper", "Cardboard", "Carton", "Cellphone", "Charger", "Chips", "Clothing", "Coffee Cup", "Coffee Machine", "Comb", "Computer", "Detergent", "Earphone", "Eraser", "Fan", "Food Scrap", "Frying Pan", "Furniture", "Glass Bottle", "Glass Cup", "Glass Jar", "Glasses", "Hair Dryer", "Hairbrush", "Hanger", "Juice Box", "Juice Pouch", "Lamp", "Laptop", "Lightbulb", "Medicine", "Mug", "Paper", "Paper Cup", "Paper Plate", "Paper Towel", "Pen", "Pillow", "Pizza Box", "Plastic Bag", "Plastic Cup", "Plastic Drink Bottle", "Plastic Jug", "Plastic Liquid Bottle", "Plastic Suace Bottle", "Plastic Utensil", "Pot", "Refrigerator", "Rug", "Scissors", "Shoes", "Silverware", "Steel Can", "Straw", "Styrofoam", "Styrofoam Box", "Surge Protector", "Textile", "Tissue", "To Go Box", "Toothbrush", "Toothpaste", "Towel", "Trash Bag", "TV", "Umbrella", "USB Flash Drive"};
        int[] daily_counter = new int[75];
        int[] total_each_item_collected = new int[75];

        //total collected item
        int total_daily_counter = settings.getInt("total_day", 0);
        int total_last_week = settings.getInt("total_last_week", 0);
        int total_current_week = settings.getInt("total_current_week", 0);
        int total_trash_counter = settings.getInt("total_trash_counter", 0);
        int total_recycle_counter = settings.getInt("total_recycle_counter", 0);

        //each item's counter
        for (int i = 0; i < oic_array.length; i++) {
            //reset every day; count the number of item collected in one day
            StringBuilder s = new StringBuilder("counter_");
            s.append(oic_array[i]);
            String s2 = s.toString();
            daily_counter[i] = Integer.valueOf(settings.getString(s2, "0"));

            //does not reset; count the total number of each item collected
            StringBuilder s3 = new StringBuilder("total_");
            s3.append(oic_array[i]);
            String s4 = s3.toString();
            total_each_item_collected[i] = Integer.valueOf(settings.getString(s4, "0"));
        }

        //double point setter
        String doublePoint = settings.getString("doublePoint", "0");

        if (doublePoint.equals("True")) {
            //week 같음
            if (lastWeek == currentWeek) {
                //day 같음
                if (lastDay == currentDay) {
                    for (int i = 0; i < oic_array.length; i++) {
                        if (topLabels[0].equals(oic_array[i])) {
                            //If a user collected an item, increase the daily counter
                            daily_counter[i] += 2;

                            StringBuilder s = new StringBuilder("counter_");
                            s.append(oic_array[i]);
                            String s2 = s.toString();
                            editor.putString(s2, Integer.toString(daily_counter[i]));

                            //If a user collected an item, increase the total each item counter
                            total_each_item_collected[i] += 1;

                            StringBuilder s3 = new StringBuilder("total_");
                            s3.append(oic_array[i]);
                            String s4 = s3.toString();
                            editor.putString(s4, Integer.toString(total_each_item_collected[i]));

                            //total collected item
                            total_daily_counter += 1;
                            editor.putInt("total_day", total_daily_counter);
                            total_current_week += 1;
                            editor.putInt("total_current_week", total_current_week);
                            //FIXME: put if statement to filter trash / recyclable materials
                            total_trash_counter += 1;
                            editor.putInt("total_trash_counter", total_trash_counter);
                            total_recycle_counter += 1;
                            editor.putInt("total_recycle_counter", total_recycle_counter);
                            //commit
                            editor.commit();
                        }
                    }
                } else { //day 다름
                    editor.putInt("day", currentDay);
                    total_daily_counter = 0;
                    editor.putInt("total_day", total_daily_counter);
                    editor.commit();
                    //reset counter
                    for (int i = 0; i < oic_array.length; i++) {
                        if (topLabels[0].equals(oic_array[i])) {
                            //If a user collected an item, increase the daily counter
                            daily_counter[i] += 2;

                            StringBuilder s = new StringBuilder("counter_");
                            s.append(oic_array[i]);
                            String s2 = s.toString();
                            editor.putString(s2, Integer.toString(daily_counter[i]));

                            //If a user collected an item, increase the total each item counter
                            total_each_item_collected[i] += 1;

                            StringBuilder s3 = new StringBuilder("total_");
                            s3.append(oic_array[i]);
                            String s4 = s3.toString();
                            editor.putString(s4, Integer.toString(total_each_item_collected[i]));

                            //total collected item
                            total_daily_counter = 1;
                            editor.putInt("total_day", total_daily_counter);
                            total_current_week += 1;
                            editor.putInt("total_current_week", total_current_week);
                            //FIXME: put if statement to filter trash / recyclable materials
                            total_trash_counter += 1;
                            editor.putInt("total_trash_counter", total_trash_counter);
                            total_recycle_counter += 1;
                            editor.putInt("total_recycle_counter", total_recycle_counter);

                            //commit
                            editor.commit();
                        } else {
                            daily_counter[i] = 0;

                            StringBuilder s = new StringBuilder("counter_");
                            s.append(oic_array[i]);
                            String s2 = s.toString();
                            editor.putString(s2, Integer.toString(daily_counter[i]));

                            //commit
                            editor.commit();
                        }
                    }
                    //commit
                    editor.commit();
                }
            }
            else { //week 다름
                editor.putInt("week", currentWeek);
                total_last_week = total_current_week;
                editor.putInt("total_last_week", total_last_week);
                total_current_week = 0;
                editor.putInt("total_current_week", total_current_week);

                //day 다름만
                editor.putInt("day", currentDay);
                total_daily_counter = 0;
                editor.putInt("total_day", total_daily_counter);

                editor.commit();

                //reset counter
                for (int i = 0; i < oic_array.length; i++) {
                    if (topLabels[0].equals(oic_array[i])) {
                        //If a user collected an item, increase the daily counter
                        daily_counter[i] += 2;

                        StringBuilder s = new StringBuilder("counter_");
                        s.append(oic_array[i]);
                        String s2 = s.toString();
                        editor.putString(s2, Integer.toString(daily_counter[i]));

                        //If a user collected an item, increase the total each item counter
                        total_each_item_collected[i] += 1;

                        StringBuilder s3 = new StringBuilder("total_");
                        s3.append(oic_array[i]);
                        String s4 = s3.toString();
                        editor.putString(s4, Integer.toString(total_each_item_collected[i]));

                        //total collected item
                        total_daily_counter = 1;
                        editor.putInt("total_day", total_daily_counter);
                        total_current_week += 1;
                        editor.putInt("total_current_week", total_current_week);
                        //FIXME: put if statement to filter trash / recyclable materials
                        total_trash_counter += 1;
                        editor.putInt("total_trash_counter", total_trash_counter);
                        total_recycle_counter += 1;
                        editor.putInt("total_recycle_counter", total_recycle_counter);

                        //commit
                        editor.commit();
                    } else {
                        daily_counter[i] = 0;

                        StringBuilder s = new StringBuilder("counter_");
                        s.append(oic_array[i]);
                        String s2 = s.toString();
                        editor.putString(s2, Integer.toString(daily_counter[i]));

                        //commit
                        editor.commit();
                    }
                }
                //commit
                editor.commit();
            }
        }
        else { //2배 아님
            //week 같음
            if (lastWeek == currentWeek) {
                //day 같음
                if (lastDay == currentDay) {
                    for (int i = 0; i < oic_array.length; i++) {
                        if (topLabels[0].equals(oic_array[i])) {
                            //If a user collected an item, increase the daily counter
                            daily_counter[i] += 1;

                            StringBuilder s = new StringBuilder("counter_");
                            s.append(oic_array[i]);
                            String s2 = s.toString();
                            editor.putString(s2, Integer.toString(daily_counter[i]));

                            //If a user collected an item, increase the total each item counter
                            total_each_item_collected[i] += 1;

                            StringBuilder s3 = new StringBuilder("total_");
                            s3.append(oic_array[i]);
                            String s4 = s3.toString();
                            editor.putString(s4, Integer.toString(total_each_item_collected[i]));

                            //total collected item
                            total_daily_counter += 1;
                            editor.putInt("total_day", total_daily_counter);
                            total_current_week += 1;
                            editor.putInt("total_current_week", total_current_week);
                            //FIXME: put if statement to filter trash / recyclable materials
                            if (topLabels[0].equals("Styrofoam") || topLabels[0].equals("Styrofoam Box") || topLabels[0].equals("Tissue") || topLabels[0].equals("To Go Box") || topLabels[0].equals("Toothbrush") || topLabels[0].equals("Toothpaste") || topLabels[0].equals("Straw") || topLabels[0].equals("Pizza Box")) {
                                total_trash_counter += 1;
                                editor.putInt("total_trash_counter", total_trash_counter);
                                //commit
                                editor.commit();
                            }
                            else {
                                total_recycle_counter += 1;
                                editor.putInt("total_recycle_counter", total_recycle_counter);
                                //commit
                                editor.commit();
                            }
                            //commit
                            editor.commit();
                        }
                        //commit
                        editor.commit();
                    }
                } else { //day 다름
                    editor.putInt("day", currentDay);
                    total_daily_counter = 0;
                    editor.putInt("total_day", total_daily_counter);
                    //reset counter
                    for (int i = 0; i < oic_array.length; i++) {
                        if (topLabels[0].equals(oic_array[i])) {
                            //If a user collected an item, increase the daily counter
                            daily_counter[i] += 1;

                            StringBuilder s = new StringBuilder("counter_");
                            s.append(oic_array[i]);
                            String s2 = s.toString();
                            editor.putString(s2, Integer.toString(daily_counter[i]));

                            //If a user collected an item, increase the total each item counter
                            total_each_item_collected[i] += 1;

                            StringBuilder s3 = new StringBuilder("total_");
                            s3.append(oic_array[i]);
                            String s4 = s3.toString();
                            editor.putString(s4, Integer.toString(total_each_item_collected[i]));

                            //total collected item
                            total_daily_counter = 1;
                            editor.putInt("total_day", total_daily_counter);
                            total_current_week += 1;
                            editor.putInt("total_current_week", total_current_week);
                            //FIXME: put if statement to filter trash / recyclable materials
                            total_trash_counter += 1;
                            editor.putInt("total_trash_counter", total_trash_counter);
                            total_recycle_counter += 1;
                            editor.putInt("total_recycle_counter", total_recycle_counter);

                            editor.commit();
                        } else {
                            daily_counter[i] = 0;

                            StringBuilder s = new StringBuilder("counter_");
                            s.append(oic_array[i]);
                            String s2 = s.toString();
                            editor.putString(s2, Integer.toString(daily_counter[i]));

                            editor.commit();
                        }
                    }
                    //commit
                    editor.commit();
                }
            } else { //week 다름
                editor.putInt("week", currentWeek);
                total_last_week = total_current_week;
                editor.putInt("total_last_week", total_last_week);
                total_current_week = 0;
                editor.putInt("total_current_week", total_current_week);
                editor.putInt("week", currentWeek);

                //day 다름만
                editor.putInt("day", currentDay);
                total_daily_counter = 0;
                editor.putInt("total_day", total_daily_counter);

                //commit
                editor.commit();

                //reset counter
                for (int i = 0; i < oic_array.length; i++) {
                    if (topLabels[0].equals(oic_array[i])) {
                        //If a user collected an item, increase the daily counter
                        daily_counter[i] += 1;

                        StringBuilder s = new StringBuilder("counter_");
                        s.append(oic_array[i]);
                        String s2 = s.toString();
                        editor.putString(s2, Integer.toString(daily_counter[i]));

                        //If a user collected an item, increase the total each item counter
                        total_each_item_collected[i] += 1;

                        StringBuilder s3 = new StringBuilder("total_");
                        s3.append(oic_array[i]);
                        String s4 = s3.toString();
                        editor.putString(s4, Integer.toString(total_each_item_collected[i]));

                        //total collected item
                        total_daily_counter = 1;
                        editor.putInt("total_day", total_daily_counter);
                        total_current_week += 1;
                        editor.putInt("total_current_week", total_current_week);
                        //FIXME: put if statement to filter trash / recyclable materials
                        total_trash_counter += 1;
                        editor.putInt("total_trash_counter", total_trash_counter);
                        total_recycle_counter += 1;
                        editor.putInt("total_recycle_counter", total_recycle_counter);

                        //commit
                        editor.commit();
                    } else {
                        daily_counter[i] = 0;

                        StringBuilder s = new StringBuilder("counter_");
                        s.append(oic_array[i]);
                        String s2 = s.toString();
                        editor.putString(s2, Integer.toString(daily_counter[i]));

                        //commit
                        editor.commit();
                    }
                }
                //commit
                editor.commit();
            }
        }
    }

    /*start of classifying with TensorFlow Lite*/
    public void classifyTFLite() {
        //initialize array that holds image data
        // initialize array that holds image data
        intValues = new int[DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y];
        //intValues = new int[imageView.getWidth() * imageView.getHeight()];

        //TensorFlow Lite
        try {
            tflite = new Interpreter(loadModelFile());
            labelList = loadLabelList();
            //Toast.makeText(this, "TFLite Loaded", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //initialize byte array
        imgData = ByteBuffer.allocateDirect(4 * DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y * DIM_PIXEL_SIZE);
        imgData.order(ByteOrder.nativeOrder());
        labelProbArray = new float[1][labelList.size()];

        // initialize array to hold top labels
        topLabels = new String[RESULTS_TO_SHOW];
        // initialize array to hold top probabilities
        topConfidence = new String[RESULTS_TO_SHOW];

        /*classify*/
        //get current bitmap from imageView
        Bitmap bitmap_tflite = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        // resize the bitmap to the required input size to the CNN
        Bitmap bitmap = getResizedBitmap(bitmap_tflite, DIM_IMG_SIZE_X, DIM_IMG_SIZE_Y);
        // convert bitmap to byte array
        convertBitmapToByteBuffer(bitmap);
        tflite.run(imgData, labelProbArray);
        // display the results
        printTopKLabels();
    }

    // resizes bitmap to given dimensions
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    // convert bitmap to bytebuffer
    private void convertBitmapToByteBuffer(Bitmap bitmap) {
        if (imgData == null) {
            return;
        }
        imgData.rewind();
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        // loop through all pixels
        int pixel = 0;
        for (int i = 0; i < DIM_IMG_SIZE_X; ++i) {
            for (int j = 0; j < DIM_IMG_SIZE_Y; ++j) {
                final int val = intValues[pixel++];
                // get rgb values from intValues where each int holds the rgb values for a pixel.
                // if quantized, convert each rgb value to a byte, otherwise to a float
                imgData.putFloat((((val >> 16) & 0xFF) - IMAGE_MEAN) / IMAGE_STD);
                imgData.putFloat((((val >> 8) & 0xFF) - IMAGE_MEAN) / IMAGE_STD);
                imgData.putFloat((((val) & 0xFF) - IMAGE_MEAN) / IMAGE_STD);
            }
        }
    }

    // print the top labels and respective confidences
    private void printTopKLabels() {
        // add all results to priority queue
        for (int i = 0; i < labelList.size(); ++i) {
            sortedLabels.add(
                    new AbstractMap.SimpleEntry<>(labelList.get(i), labelProbArray[0][i]));
            if (sortedLabels.size() > RESULTS_TO_SHOW) {
                sortedLabels.poll();
            }
        }

        // get top results from priority queue
        final int size = sortedLabels.size();
        for (int i = 0; i < size; ++i) {
            Map.Entry<String, Float> label = sortedLabels.poll();
            topLabels[i] = label.getKey();
            topConfidence[i] = String.format("Photo Identification Accuracy: %.0f%%", label.getValue() * 100);
        }

        // set the corresponding textviews with the results
        label1.setText(topLabels[0]);
        confidence1.setText(topConfidence[0]);
    }

    //memory-map the model file in Assets
    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd(MODEL_PATH);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    private List<String> loadLabelList() throws IOException {
        List<String> labelList = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.getAssets().open(LABEL_PATH)));
        String line;
        while ((line = reader.readLine()) != null) {
            labelList.add(line);
        }
        reader.close();
        return labelList;
    }
    /*end of classifying with TensorFlow Lite*/

    /*start of saving images*/
    public void startSave() {
        FileOutputStream fileOutputStream = null;

        //create a folder
        String folder_main = "Recycling Tracker";
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File f = new File(file, folder_main);
        if (!f.exists()) {
            f.mkdirs();
        }

        //create anohter folder
        File f1 = new File(f.getAbsolutePath(), topLabels[0]);
        if (!f1.exists()) {
            f1.mkdirs();
        }

        //create a image name
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmsshhmmss");
        String date = simpleDateFormat.format(new Date());
        String name = date + ".jpg";
        String file_name = f1.getAbsolutePath() + "/" + name;
        File new_file = new File(file_name);

        //save the image
        try {
            fileOutputStream = new FileOutputStream(new_file);
            Bitmap bitmap = getBitmapFromView(imageView);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            //Toast.makeText(this, "Save image success", Toast.LENGTH_SHORT).show();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        refreshGallery(new_file);
    }

    public void refreshGallery(File file) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
    }

    //imageView -> bitmap
    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888); //Each pixel is stored on 4 bytes.
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }
    /*end of saving image*/


    /*start of opening camera and showing image*/
    public void openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            invokeCamera();
        } else {
            //request permission
            String[] permissionRequest = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissionRequest, 0);

            //FIXME not working :(
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                invokeCamera();
            }
            else {
                finish();
            }
        }
    }

    private void invokeCamera() {
        Intent int8 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (int8.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(int8, CAMERA_REQUEST_CODE);
        }
        else {
            Log.d(TAG, "Inside of invokeCamera(), taking a picture cannot be done");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            // we have heard back from our request for camera, write, and read external storage.
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                invokeCamera();
            } else {
                Log.d(TAG, "Required the user permission to access camera");
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            image = getRoundedCornerBitmap(image);
            imageView.setImageBitmap(image);

            //apply TensorFlow Lite to the captured image
            classifyTFLite();
        } else {
            Log.d(TAG, "Image was not displayed due to errors.");
            finish();
        }
    }
    /*end of opening camera and showing image*/

    /* start of round corner of image */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 12;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
    /* end of round corner image */
}