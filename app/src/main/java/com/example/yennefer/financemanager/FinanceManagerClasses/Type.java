package com.example.yennefer.financemanager.FinanceManagerClasses;

import com.orm.SugarRecord;

/**
 * Created by Yennefer on 25.01.2015.
 * Type of category
 */
public class Type extends SugarRecord<Type> {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type() {
    }

    public Type(String name) {
        this.name = name;
    }

}
