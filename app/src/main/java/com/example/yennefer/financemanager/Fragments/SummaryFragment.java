package com.example.yennefer.financemanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.example.yennefer.financemanager.FinanceManagerClasses.Operation;
import com.example.yennefer.financemanager.R;
import com.example.yennefer.financemanager.Content.SummaryAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yennefer on 19.01.2015.
 */
public class SummaryFragment extends ListFragment {

    // Map attribute names
    private final String ATTRIBUTE_SUM = "sum";
    private final String ATTRIBUTE_TIME = "time";
    private final String ATTRIBUTE_CATEGORY = "categoryIcon";

    // Names of operation types
    private final String INCOME_TYPE = "income";
    private final String OUTCOME_TYPE = "outcome";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setSummaryAdapter();

    }

    private void setSummaryAdapter() {

        // Creating operations list
        List<Operation> operations = Operation.find(Operation.class, null, null, null, "id DESC", "50");

        // Preparing operations data
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(operations.size());
        Map<String, Object> m;

        for (int i = 0; i < operations.size(); i++) {
            Operation currentOperation = operations.get(i);
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_SUM, currentOperation.getSum());
            m.put(ATTRIBUTE_TIME, currentOperation.getTimeAsString());

            switch (currentOperation.getType()) {
                case OUTCOME_TYPE:
                    m.put(ATTRIBUTE_CATEGORY, currentOperation.getCategory().getImage());
                    break;
                case INCOME_TYPE:
                    m.put(ATTRIBUTE_CATEGORY, currentOperation.getSource().getImage());
                    break;
                default:
                    break;
            }

            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = {ATTRIBUTE_SUM, ATTRIBUTE_TIME, ATTRIBUTE_CATEGORY};

        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = {R.id.tvSum, R.id.tvTime, R.id.ivCategory};

        // Creating adapter
        SummaryAdapter sAdapter = new SummaryAdapter(getActivity(), data, R.layout.list_item, from, to);

        // Assignment adapter to list
        setListAdapter(sAdapter);
    }

}
