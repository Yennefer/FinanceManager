package com.example.yennefer.financemanager.FinanceManagerClasses;

import com.orm.SugarRecord;

/**
 * Created by Yennefer on 25.01.2015.
 * Category of operation
 */
public class Category extends SugarRecord<Category> {

    private String name;

    private String image;

    private Type type;

    private int isUsed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int is_used) {
        this.isUsed = is_used;
    }

    public Category() {
    }

    public Category(String name, String image, Type type) {
        this.name = name;
        this.image = image;
        this.type = type;
        this.isUsed = 1;
    }

}
