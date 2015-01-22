package com.example.yennefer.financemanager.FinanceManagerClasses;

import com.orm.SugarRecord;

public class Category extends SugarRecord<Category> {

    private String name;

    private int image;

    private int is_used;

    public Category() {
    }

    public Category(String name, int image) {
        this.name = name;
        this.image = image;
        this.is_used = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getIs_used() {
        return is_used;
    }

    public void setIs_used(int is_used) {
        this.is_used = is_used;
    }
}
