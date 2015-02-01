package com.example.yennefer.financemanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yennefer.financemanager.Fragments.AboutFragment;
import com.example.yennefer.financemanager.Fragments.OperationFragment;
import com.example.yennefer.financemanager.Fragments.SettingsFragment;
import com.example.yennefer.financemanager.Fragments.StatisticFragment;
import com.example.yennefer.financemanager.Fragments.SummaryFragment;
import com.example.yennefer.financemanager.db.DatabaseManager;

/**
 * Created by Yennefer on 25.01.2015.
 * Main activity with fragments container
 */

public class MainActivity extends ActionBarActivity {

    // Fragments manager
    private FragmentManager myFragmentManager;
    // Fragments
    private SummaryFragment summaryFragment;
    private OperationFragment operationFragment;
    private StatisticFragment statisticFragment;
    private AboutFragment aboutFragment;
    private SettingsFragment settingsFragment;

    // Action bar
    private ActionBar actionBar;
    // Action mode
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Get database manager instance
        DatabaseManager.init(this);

        // Get fragment manager
        myFragmentManager = getSupportFragmentManager();
        // Create fragments
        summaryFragment = new SummaryFragment();
        operationFragment = new OperationFragment();
        statisticFragment = new StatisticFragment();
        aboutFragment = new AboutFragment();
        settingsFragment = new SettingsFragment();

        // Get action bar and set home button
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        // Set fragment on first start
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, summaryFragment);
            fragmentTransaction.commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Fragment currentFragment = null;

        switch (item.getItemId()) {
            case android.R.id.home:
                currentFragment = summaryFragment;
                break;
            case R.id.action_add_operations:
                currentFragment = operationFragment;
                break;
            case R.id.action_statistic:
                currentFragment = statisticFragment;
                break;
            case R.id.action_about:
                currentFragment = aboutFragment;
                break;
            case R.id.action_settings:
                currentFragment = settingsFragment;
                break;
            default:
                break;
        }

        if (currentFragment != null) {
            FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, currentFragment);
            fragmentTransaction.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
