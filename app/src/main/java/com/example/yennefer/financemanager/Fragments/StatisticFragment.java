package com.example.yennefer.financemanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yennefer.financemanager.R;

/**
 * Created by Yennefer on 25.01.2015.
 * Fragment with statistic info
 */

public class StatisticFragment extends Fragment {

    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_statistic, null);

    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.items, menu);

        menu.setGroupVisible(R.id.add_menu_group, true);
        menu.setGroupVisible(R.id.statistic_menu_group, false);
        menu.setGroupVisible(R.id.hide_menu_group, true);

        super.onCreateOptionsMenu(menu, inflater);
    }

}
