package com.example.yennefer.financemanager.FinanceManagerClasses;

import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Operation extends SugarRecord<Operation> {

    private String sum;

    private String type;

    private int date;

    private Category category;

    private Source source;

    public Operation() {
    }

    public Operation(String sum, String type, int date, Category category, Source source) {
        this.sum = sum;
        this.date = date;
        this.type = type;
        this.category = category;
        this.source = source;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public int getDate() {
        return date;
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

    public void setDate(int date) {
        this.date = date;
    }

}
