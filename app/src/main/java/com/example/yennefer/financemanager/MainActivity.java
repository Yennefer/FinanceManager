package com.example.yennefer.financemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.yennefer.financemanager.FinanceManagerClasses.Category;
import com.example.yennefer.financemanager.FinanceManagerClasses.Operation;
import com.example.yennefer.financemanager.FinanceManagerClasses.Source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ActionBarActivity {

    private Button btnAddTransaction, btnSeeStatistic;
    private ListView lvSummary;

    // Fragments manager
    FragmentManager myFragmentManager;

    // Fragments
    private SummaryFragment summaryFragment;
    private EditOperationFragment editOperationFragment;
    private StatisticFragment statisticFragment;

    // Container for fragments
    private FrameLayout container;

    // Map attribute names
    private final String ATTRIBUTE_SUM = "sum";
    private final String ATTRIBUTE_TIME = "time";
    private final String ATTRIBUTE_CATEGORY = "categoryIcon";

    // Tag for log writing
    private static final String LOG_TAG = "myLogs";

    public static String getLogTag() {
        return LOG_TAG;
    }

    // Names of operation types
    private static final String INCOME_TYPE = "income";
    private static final String OUTCOME_TYPE = "outcome";

    public static String getIncomeType() {
        return INCOME_TYPE;
    }

    public static String getOutcomeType() {
        return OUTCOME_TYPE;
    }

    // Setting default database
    private void setDefaultData() {

        List<Category> categories = Category.listAll(Category.class);
        List<Source> sources = Source.listAll(Source.class);

        // If category table is empty, fill it with default categories
        if (categories.isEmpty()) {
            Category category;
            category = new Category("Продукты", R.drawable.chicken);
            category.save();
            category = new Category("Транспорт", R.drawable.car);
            category.save();
            category = new Category("Развлечения", R.drawable.bowling);
            category.save();
        }

        // If source table is empty, fill it with default sources
        if (sources.isEmpty()) {
            Source source;
            source = new Source("Зарплата", R.drawable.creditcard);
            source.save();
        }

    }

    // Updating and outputting list of 50 last operations
    private void UpdateSummary() {

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
        SummaryAdapter sAdapter = new SummaryAdapter(this, data, R.layout.list_item, from, to);

        // Assignment adapter to list
        lvSummary.setAdapter(sAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Create fragment manager
        myFragmentManager = getSupportFragmentManager();

        // Create fragments
        summaryFragment = new SummaryFragment();
        editOperationFragment = new EditOperationFragment();
        statisticFragment = new StatisticFragment();

        // Find container for fragments
        container = (FrameLayout) findViewById(R.id.container);

        if (savedInstanceState == null) {
            // On first start
            // Add summaryFragment in container
            FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, summaryFragment);
            fragmentTransaction.commit();
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setIcon(R.drawable.ic_launcher);
//        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();

        switch (item.getItemId()) {
            case android.R.id.home:
/*                fragmentTransaction.replace(R.id.container, summaryFragment);
                Log.d(LOG_TAG, "Go to summary fragment");
                fragmentTransaction.commit();*/
                return true;
            case R.id.action_add_operations:
                fragmentTransaction.replace(R.id.container, editOperationFragment);
                Log.d(LOG_TAG, "Go to edit operation fragment");
                fragmentTransaction.commit();
                return true;
            case R.id.action_statistic:
                fragmentTransaction.replace(R.id.container, statisticFragment);
                Log.d(LOG_TAG, "Go to statistic fragment");
                fragmentTransaction.commit();
                return true;
            case R.id.action_about:
                Log.d(LOG_TAG, "Go to about fragment");
                fragmentTransaction.commit();
                break;
            case R.id.action_settings:
                Log.d(LOG_TAG, "Go to settings fragment");
                fragmentTransaction.commit();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
