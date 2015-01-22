package com.example.yennefer.financemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Yennefer on 06.01.2015.
 */
public class StatisticActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Задаем Layout для StatisticActivity
        setContentView(R.layout.statistic_fragment);

    }

    @Override
    public void onClick(View v) {

    }
}
