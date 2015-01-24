package com.example.yennefer.financemanager.Fragments;

import android.os.Bundle;
import android.support.v4.preference.PreferenceFragment;

import com.example.yennefer.financemanager.R;

public class SettingsFragment extends PreferenceFragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}
