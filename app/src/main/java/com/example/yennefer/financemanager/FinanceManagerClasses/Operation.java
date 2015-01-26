package com.example.yennefer.financemanager.FinanceManagerClasses;

import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yennefer on 25.01.2015.
 * Finance operation
 */
public class Operation extends SugarRecord<Operation> {

    private String sum;

    private Category category;

    private int date;

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

    public Operation(String sum, Category category, int date) {
        this.sum = sum;
        this.category = category;
        this.date = date;
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
