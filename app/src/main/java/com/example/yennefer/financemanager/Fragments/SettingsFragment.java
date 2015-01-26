package com.example.yennefer.financemanager.Fragments;

import android.os.Bundle;
import android.support.v4.preference.PreferenceFragment;

import com.example.yennefer.financemanager.R;

/**
 * Created by Yennefer on 25.01.2015.
 * Fragment with preferences
 */
public class SettingsFragment extends PreferenceFragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}
