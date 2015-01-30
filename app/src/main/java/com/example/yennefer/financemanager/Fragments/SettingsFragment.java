package com.example.yennefer.financemanager.Fragments;

import android.os.Bundle;
import android.support.v4.preference.PreferenceFragment;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.yennefer.financemanager.R;

/**
 * Created by Yennefer on 25.01.2015.
 * Fragment with preferences
 */

public class SettingsFragment extends PreferenceFragment {

    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.items, menu);

        menu.setGroupVisible(R.id.add_menu_group, false);
        menu.setGroupVisible(R.id.statistic_menu_group, false);
        menu.setGroupVisible(R.id.hide_menu_group, false);

        super.onCreateOptionsMenu(menu, inflater);
    }

}
