package com.example.yennefer.financemanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.example.yennefer.financemanager.FinanceManagerClasses.Operation;
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final String ATTRIBUTE_SUM = getResources().getString(R.string.attribute_sum);
        final String ATTRIBUTE_TIME = getResources().getString(R.string.attribute_time);
        final String ATTRIBUTE_CATEGORY = getResources().getString(R.string.attribute_category);

        final String OUTCOME_TYPE = getResources().getString(R.string.outcome_type);
        final String INCOME_TYPE = getResources().getString(R.string.income_type);

        // Creating operations list
        List<Operation> operations = Operation.find(Operation.class, null, null, null, "id DESC", "50");

        // Preparing operations data
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(operations.size());
        Map<String, Object> m;

        for (int i = 0; i < operations.size(); i++) {
            Operation currentOperation = operations.get(i);
            String currentOperationType = currentOperation.getCategory().getType().getName();
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_SUM, currentOperation.getSum());
            m.put(ATTRIBUTE_TIME, currentOperation.getTimeAsString());

            if (currentOperationType == OUTCOME_TYPE) {
                m.put(ATTRIBUTE_CATEGORY, currentOperation.getCategory().getImage());
            }
            else if (currentOperationType == INCOME_TYPE) {
                m.put(ATTRIBUTE_CATEGORY, currentOperation.getCategory().getImage());
            }
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

}
