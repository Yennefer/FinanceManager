package com.example.yennefer.financemanager.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yennefer on 25.01.2015.
 * Type of category
 */

@DatabaseTable(tableName = "types")
public class Type {

    @DatabaseField(id = true)
    private String name;

    @ForeignCollectionField
    private ForeignCollection<Category> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategories(ForeignCollection<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        ArrayList<Category> categoriesList = new ArrayList<>();
        for (Category category : categories) {
            categoriesList.add(category);
        }
        return categoriesList;
    }

    public Type() {
    }

    public Type(String name) {
        this.name = name;
    }
}
