package com.example.yennefer.financemanager.Content;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yennefer.financemanager.R;

public class DataBase {

    private final String CATEGORIES_TABLE = "Categories";
    private final String CATEGORIES_NAME_COLUMN = "Name";
    private final String CATEGORIES_TYPE_COLUMN = "Type";
    private final String CATEGORIES_IMAGE_COLUMN = "Image";
    private final String CATEGORIES_IS_USED_COLUMN = "Is_used";

    private final String TYPES_TABLE = "Types";
    private final String TYPE_NAME_COLUMN = "Type_name";
    private final String INCOME_TYPE;
    private final String OUTCOME_TYPE;

    private final String OPERATIONS_TABLE = "Operations";
    private final String OPERATIONS_SUM_COLUMN = "Sum";
    private final String OPERATIONS_CATEGORY_COLUMN = "Categoty";
    private final String OPERATIONS_DATE_COLUMN = "Date";

    private final Context context;

    private DBHelper helper;
    private SQLiteDatabase myDataBase;

    // Constructor
    public DataBase(Context context) {
        this.context = context;
        INCOME_TYPE = context.getResources().getString(R.string.income_type);
        OUTCOME_TYPE = context.getResources().getString(R.string.income_type);
    }

    // Open connection
    public void open() {

        String DB_NAME = "FinanceManager";
        int DB_VERSION = 1;

        helper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        myDataBase = helper.getWritableDatabase();
    }

    // Close connection
    public void close() {
        if (helper != null) {
            helper.close();
        }
    }

/*    // получить все данные из таблицы DB_TABLE
    public Cursor getAllData() {
        return myDataBase.query("", null, null, null, null, null, null);
    }

    // добавить запись в DB_TABLE
    public void addRec(String txt, int img) {

    }

    // удалить запись из DB_TABLE
    public void delRec(long id) {

    }*/

    // Class for create and manage database version
    private class DBHelper extends SQLiteOpenHelper {

        // Constructor
        public DBHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Create and fill in database
        @Override
        public void onCreate(SQLiteDatabase myDataBase) {

            String DB_CREATE = "CREATE TABLE " + TYPES_TABLE + " (" +
                    TYPE_NAME_COLUMN + " text  NOT NULL ," +
                    "PRIMARY KEY (" + TYPE_NAME_COLUMN + "));" +
                    "CREATE TABLE " + CATEGORIES_TABLE + " (" +
                    "_id integer  NOT NULL ," +
                    CATEGORIES_NAME_COLUMN + " text  NOT NULL ," +
                    CATEGORIES_TYPE_COLUMN + " text  NOT NULL ," +
                    CATEGORIES_IMAGE_COLUMN + " integer  NOT NULL ," +
                    CATEGORIES_IS_USED_COLUMN + " integer  NOT NULL ," +
                    "PRIMARY KEY (_id)," +
                    "FOREIGN KEY (" + CATEGORIES_TYPE_COLUMN + ") REFERENCES Type(" + TYPE_NAME_COLUMN + "));" +
                    "CREATE TABLE " + OPERATIONS_TABLE + " (" +
                    "_id integer  NOT NULL ," +
                    OPERATIONS_SUM_COLUMN + " text  NOT NULL ," +
                    OPERATIONS_CATEGORY_COLUMN + " integer  NOT NULL ," +
                    OPERATIONS_DATE_COLUMN + " integer  NOT NULL ," +
                    "PRIMARY KEY (_id)," +
                    "FOREIGN KEY (" + OPERATIONS_CATEGORY_COLUMN + ") REFERENCES Categories(_id));";

            myDataBase.execSQL(DB_CREATE);

            ContentValues cv = new ContentValues();

            cv.put(TYPE_NAME_COLUMN, INCOME_TYPE);
            myDataBase.insert(TYPES_TABLE, null, cv);
            cv.clear();
            cv.put(TYPE_NAME_COLUMN, OUTCOME_TYPE);
            myDataBase.insert(TYPES_TABLE, null, cv);
            cv.clear();

            cv.put(CATEGORIES_NAME_COLUMN, "Продукты");
            cv.put(CATEGORIES_TYPE_COLUMN, OUTCOME_TYPE);
            cv.put(CATEGORIES_IMAGE_COLUMN, "chicken");
            cv.put(CATEGORIES_IS_USED_COLUMN, "1");
            myDataBase.insert(CATEGORIES_TABLE, null, cv);
            cv.clear();
            cv.put(CATEGORIES_NAME_COLUMN, "Транспорт");
            cv.put(CATEGORIES_TYPE_COLUMN, OUTCOME_TYPE);
            cv.put(CATEGORIES_IMAGE_COLUMN, "car");
            cv.put(CATEGORIES_IS_USED_COLUMN, "1");
            myDataBase.insert(CATEGORIES_TABLE, null, cv);
            cv.clear();
            cv.put(CATEGORIES_NAME_COLUMN, "Развлечения");
            cv.put(CATEGORIES_TYPE_COLUMN, OUTCOME_TYPE);
            cv.put(CATEGORIES_IMAGE_COLUMN, "bowling");
            cv.put(CATEGORIES_IS_USED_COLUMN, "1");
            myDataBase.insert(CATEGORIES_TABLE, null, cv);
            cv.clear();
            cv.put(CATEGORIES_NAME_COLUMN, "Зарплата");
            cv.put(CATEGORIES_TYPE_COLUMN, INCOME_TYPE);
            cv.put(CATEGORIES_IMAGE_COLUMN, "creditcard");
            cv.put(CATEGORIES_IS_USED_COLUMN, "1");
            myDataBase.insert(CATEGORIES_TABLE, null, cv);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Add something when upgrade database version
        }
    }
}