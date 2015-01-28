package com.example.yennefer.financemanager.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yennefer on 25.01.2015.
 * Finance operation
 */

@DatabaseTable(tableName = "types")
public class Operation {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String sum;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Category category;

    @DatabaseField(canBeNull = false)
    private int date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Operation() {
    }

    public String getDateAsString() {
        String dateAsText = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(date * 1000L));
        return dateAsText;
    }

    public String getTimeAsString() {
        String timeAsText = new SimpleDateFormat("HH:mm")
                .format(new Date(date * 1000L));
        return timeAsText;
    }
}
