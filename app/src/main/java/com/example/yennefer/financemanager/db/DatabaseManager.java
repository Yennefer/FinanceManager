package com.example.yennefer.financemanager.db;

import android.content.Context;

import com.example.yennefer.financemanager.model.Category;
import com.example.yennefer.financemanager.model.Operation;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yennefer on 29.01.2015.
 * Singleton class for encapsulating work with DAO objects
 */

public class DatabaseManager {

    static private DatabaseManager instance;

    static public void init(Context ctx) {
        if (null==instance) {
            instance = new DatabaseManager(ctx);
        }
    }

    static public DatabaseManager getInstance() {
        return instance;
    }

    private DatabaseHelper helper;
    private DatabaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }

    /* My custom queries: */

    public List<Operation> getAllOperations() {
        List<Operation> operations = null;
        try {
            operations = getHelper().getOperationDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operations;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = null;
        try {
            categories = getHelper().getCategoryDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category getCategoryWithName(String categoryName) {
        Category category = null;
        try {
            category = getHelper().getCategoryDao().queryForId(categoryName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public void addOperation(Operation operation) {
        try {
            getHelper().getOperationDao().create(operation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOperation(Operation operation) {
        try {
            getHelper().getOperationDao().update(operation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}