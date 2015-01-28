package com.example.yennefer.financemanager;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yennefer.financemanager.fragments.AboutFragment;
import com.example.yennefer.financemanager.fragments.EditOperationFragment;
import com.example.yennefer.financemanager.fragments.SettingsFragment;
import com.example.yennefer.financemanager.fragments.StatisticFragment;
import com.example.yennefer.financemanager.fragments.SummaryFragment;

/**
 * Created by Yennefer on 25.01.2015.
 * Main activity with fragments container
 */
public class MainActivity extends ActionBarActivity {

    // Fragments manager
    private FragmentManager myFragmentManager;
    // Fragments
    private SummaryFragment summaryFragment;
    private EditOperationFragment editOperationFragment;
    private StatisticFragment statisticFragment;
    private AboutFragment aboutFragment;
    private SettingsFragment settingsFragment;

    // Action bar
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Get fragment manager
        myFragmentManager = getSupportFragmentManager();
        // Create fragments
        summaryFragment = new SummaryFragment();
        editOperationFragment = new EditOperationFragment();
        statisticFragment = new StatisticFragment();
        aboutFragment = new AboutFragment();
        settingsFragment = new SettingsFragment();

        // Get action bar and set home button
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setIcon(R.drawable.apple);
        actionBar.setDisplayShowHomeEnabled(true);

        // Set fragment on first start
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, summaryFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();

        switch (item.getItemId()) {
            case android.R.id.home:
                fragmentTransaction.replace(R.id.container, summaryFragment);
                fragmentTransaction.commit();
                actionBar.setDisplayHomeAsUpEnabled(false);
                return true;
            case R.id.action_add_operations:
                fragmentTransaction.replace(R.id.container, editOperationFragment);
                fragmentTransaction.commit();
                actionBar.setDisplayHomeAsUpEnabled(true);
                return true;
            case R.id.action_statistic:
                fragmentTransaction.replace(R.id.container, statisticFragment);
                fragmentTransaction.commit();
                actionBar.setDisplayHomeAsUpEnabled(true);
                return true;
            case R.id.action_about:
                fragmentTransaction.replace(R.id.container, aboutFragment);
                fragmentTransaction.commit();
                actionBar.setDisplayHomeAsUpEnabled(true);
                break;
            case R.id.action_settings:
                fragmentTransaction.replace(R.id.container, settingsFragment);
                fragmentTransaction.commit();
                actionBar.setDisplayHomeAsUpEnabled(true);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
