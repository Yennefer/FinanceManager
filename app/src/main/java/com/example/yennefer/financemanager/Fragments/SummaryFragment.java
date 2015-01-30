package com.example.yennefer.financemanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.yennefer.financemanager.db.DatabaseManager;
import com.example.yennefer.financemanager.model.Operation;
import com.example.yennefer.financemanager.R;
import com.example.yennefer.financemanager.SummaryAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yennefer on 25.01.2015.
 * Fragment with list of resent operations
 */

public class SummaryFragment extends ListFragment {

    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final String ATTRIBUTE_SUM = getResources().getString(R.string.attribute_sum);
        final String ATTRIBUTE_TIME = getResources().getString(R.string.attribute_time);
        final String ATTRIBUTE_CATEGORY = getResources().getString(R.string.attribute_category);

        // Creating operations list
        List<Operation> operations = DatabaseManager.getInstance().getAllOperations();

        // Preparing operations data
        ArrayList<Map<String, Object>> data = new ArrayList<>(operations.size());
        Map<String, Object> m;

        for (Operation operation : operations) {
            m = new HashMap<>();
            m.put(ATTRIBUTE_SUM, operation.getSum());
            m.put(ATTRIBUTE_TIME, operation.getTimeAsString());
            m.put(ATTRIBUTE_CATEGORY, operation.getCategory().getImage());
            data.add(m);
        }

        // Array of attributes
        String[] from = { ATTRIBUTE_SUM, ATTRIBUTE_TIME, ATTRIBUTE_CATEGORY };
        // Array of views
        int[] to = {R.id.tvSum, R.id.tvTime, R.id.ivCategory};

        // Create adapter
        SummaryAdapter sAdapter = new SummaryAdapter(getActivity(), data, R.layout.list_item, from, to);

        // Assign adapter to list
        setListAdapter(sAdapter);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.items, menu);

        menu.setGroupVisible(R.id.add_menu_group, true);
        menu.setGroupVisible(R.id.statistic_menu_group, true);
        menu.setGroupVisible(R.id.hide_menu_group, true);

        super.onCreateOptionsMenu(menu, inflater);
    }
}
