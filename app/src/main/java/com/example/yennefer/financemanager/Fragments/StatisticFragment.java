package com.example.yennefer.financemanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yennefer.financemanager.R;

/**
 * Created by Yennefer on 25.01.2015.
 * Fragment with statistic info
 */

public class StatisticFragment extends Fragment {

    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistic, null);

    }

}
