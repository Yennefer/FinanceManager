package com.example.yennefer.financemanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.yennefer.financemanager.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Yennefer on 25.01.2015.
 * Adapter for list in SummaryFragment
 */
public class SummaryAdapter extends SimpleAdapter {

    private Context myContext;

    public static final int ITEM_VIEW_TYPE = 0;    // элемент списка - пункт
    public static final int SECTION_VIEW_TYPE = 1; // элемент списка - заголовок
    public static final int VIEW_TYPES_COUNT = 2;  // количество типов элементов списка

    public SummaryAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.myContext = context;
    }

/*    @Override
    public int getItemViewType(int position) {
        if (...) {
            return ITEM_VIEW_TYPE;
        }
        if (...) {
            return SECTION_VIEW_TYPE;
        }
        return IGNORE_ITEM_VIEW_TYPE;
    }*/

/*    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int viewType = getItemViewType(position);
        if (viewType == IGNORE_ITEM_VIEW_TYPE) {
            throw new IllegalStateException("Failed to get object at position " + position);
        }
        if (viewType == SECTION_VIEW_TYPE) {
            convertView = ... // здесь можно через LayoutInflater получить Layout для заголовка раздела
        } else if (viewType == ITEM_VIEW_TYPE) {
            convertView = ... // здесь можно через LayoutInflater получить Layout для пункта
        }
        return convertView;
    }*/

/*    public boolean isEnabled(int position) {
        return (getItemViewType(position) != SECTION_VIEW_TYPE);
    }*/

    @Override
    public void setViewText(TextView v, String text) {

        if (v.getId() == R.id.tvSum) {
            // Setting up sum color
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

}
