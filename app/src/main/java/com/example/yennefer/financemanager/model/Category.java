package com.example.yennefer.financemanager.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yennefer on 25.01.2015.
 * Category of operation
 */

@DatabaseTable(tableName = "categories")
public class Category {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private String image;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Type type;

    @DatabaseField(canBeNull = false)
    private int isUsed;

    @ForeignCollectionField
    private ForeignCollection<Operation> operations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<Operation> getOperations() {
        ArrayList<Operation> operationList = new ArrayList<>();
        for (Operation operation : operations) {
            operationList.add(operation);
        }
        return operationList;
    }

    public void setOperations(ForeignCollection<Operation> operations) {
        this.operations = operations;
    }

    public Category() {
    }

    public Category(int id, String name, String image, Type type, int isUsed) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.type = type;
        this.isUsed = isUsed;
    }
}
