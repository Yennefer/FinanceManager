package com.example.yennefer.financemanager.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yennefer.financemanager.CategoryType;
import com.example.yennefer.financemanager.model.Category;
import com.example.yennefer.financemanager.model.Operation;
import com.example.yennefer.financemanager.model.Type;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yennefer on 29.01.2015.
 * Class for creating and managing database
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // Database name
    private static final String DATABASE_NAME = "FinanceManagerDB";

    // Database version
    private static final int DATABASE_VERSION = 1;

    // the DAO objects needed to access the SimpleData table
    private Dao<Category, String> categoryDao = null;
    private Dao<Operation, Integer> operationDao = null;
    private Dao<Type, String> typeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Type.class);
            TableUtils.createTable(connectionSource, Category.class);
            TableUtils.createTable(connectionSource, Operation.class);

            List<String> queries = new ArrayList<>();
            queries.add("INSERT INTO Types (name) VALUES (\"" + CategoryType.OUTCOME.toString() + "\");");
            queries.add("INSERT INTO Types (name) VALUES (\"" + CategoryType.INCOME.toString() + "\");");
            queries.add("INSERT INTO Categories (name, image, type_id, is_used) VALUES (\"продукты\", \"apple.png\", \"" +
                    CategoryType.OUTCOME.toString() + "\", 1);");
            queries.add("INSERT INTO Categories (name, image, type_id, is_used) VALUES (\"развлечения\", \"bowling.png\", \"" +
                    CategoryType.OUTCOME.toString() + "\", 1);");
            queries.add("INSERT INTO Categories (name, image, type_id, is_used) VALUES (\"зарплата\", \"creditcard.png\", \"" +
                    CategoryType.INCOME.toString() + "\", 1);");
            for (String sql : queries) {
                database.execSQL(sql);
            }

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            List<String> allSql = new ArrayList<>();
            switch(oldVersion)
            {
                case 1:
                    //allSql.add("INSERT INTO Types (name) VALUES ();");
                    //allSql.add("INSERT INTO Categories () VALUES ();");
            }
            for (String sql : allSql) {
                database.execSQL(sql);
            }
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "exception during onUpgrade", e);
            throw new RuntimeException(e);
        }
    }

    public Dao<Type, String> getTypeDao() {
        if (null == typeDao) {
            try {
                typeDao = getDao(Type.class);
            }catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return typeDao;
    }

    public Dao<Category, String> getCategoryDao() {
        if (null == categoryDao) {
            try {
                categoryDao = getDao(Category.class);
            }catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return categoryDao;
    }

    public Dao<Operation, Integer> getOperationDao() {
        if (null == operationDao) {
            try {
                operationDao = getDao(Operation.class);
            }catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return operationDao;
    }

}
