package com.example.yennefer.financemanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yennefer.financemanager.R;

/**
 * Created by Yennefer on 25.01.2015.
 * Fragment with information about the program
 */
public class AboutFragment extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_fragment, null);
    }
}
