package com.example.yennefer.financemanager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Yennefer on 25.01.2015.
 * Adapter for list in SummaryFragment
 */

public class SummaryAdapter extends SimpleAdapter {

    private Context myContext;

    public SummaryAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.myContext = context;
    }

    @Override
    public void setViewText(@NonNull TextView v, String text) {
        if (v.getId() == R.id.tvSum) {
            // Set up sum color
            int positiveColor = myContext.getResources().getColor(R.color.yellow_green);
            int negativeColor = myContext.getResources().getColor(R.color.coral);
            if (text.startsWith("+")) {
                v.setTextColor(positiveColor);
            } else if (text.startsWith("-")) {
                v.setTextColor(negativeColor);
            }

            // Add ruble abbreviation
            text = text + " р.";
        }

        super.setViewText(v, text);
    }

    @Override
    public void setViewImage(@NonNull ImageView v, String value) {
        try {
            // Get input stream
            InputStream inputStream = myContext.getAssets().open("images/"+value);
            // Load image as Drawable
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            // Set image to ImageView
            v.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
