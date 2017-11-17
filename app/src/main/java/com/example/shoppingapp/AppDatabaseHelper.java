package com.example.shoppingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by SG0216351 on 11/1/2017.
 */

public class AppDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "APPDatabaseHelper";

    //Database name and version
    private static final int DATABASE_VERSION =    1;
    private static final String DATABASE_NAME = "PRODUCTS.db";

    /**********************************************************
     *              TABLE NAMES
     **********************************************************/
    private static final String PRODUCT_TABLE = "product_table";
    private static final String LISTPRODUCT_TABLE = "listproduct_table";
    private static final String INVENTORYPRODUCT_TABLE = "inventoryproduct_table";
    private static final String LIST_TABLE = "list_table";
    private static final String INVENTORY_TABLE = "inventory_table";
    /**********************************************************
     *              PRODUCT TABLE COLUMNS NAMES
     **********************************************************/
    private static final String PRODUCT_ID = "product_id";
    private static final String PRODUCT_NAME = "product_name";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_AISLE = "aisle";
    private static final String PRODUCT_VALUE = "value";
    /**********************************************************
     *              LIST TABLE COLUMNS NAMES
     **********************************************************/
    private static final String LIST_ID = "list_id";
    private static final String LIST_NAME = "list_name";
    private static final String QUANTITY = "quantity";
    /**********************************************************
     *              INVENTORY TABLE COLUMN NAMES
     **********************************************************/
    private static final String INVENTORY_ID = "inventory_id";
    private static final String INVENTORY_NAME = "inventory_name";


    //Create statement for product_table
    private static final String CREATE_PRODUCT_TABLE = "CREATE TABLE " + PRODUCT_TABLE
            + "(" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PRODUCT_NAME + " TEXT UNIQUE,"
            + PRODUCT_PRICE + " INTEGER," + PRODUCT_AISLE + " TEXT," + PRODUCT_VALUE + "INTEGER DEFAULT 0" +  ")";
    //Create statement for list_table
    private static final String CREATE_LIST_TABLE = "CREATE TABLE " + LIST_TABLE
            + "(" + LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + LIST_NAME + " TEXT" + ")";
    //Create statement for inventory_table
    private static final String CREATE_INVENTORY_TABLE = "CREATE TABLE " + INVENTORY_TABLE
            + "(" + INVENTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + INVENTORY_NAME + " TEXT" + ")";
    //Create statement for listproduct_table
    private static final String CREATE_LISTPRODUCT_TABLE = "CREATE TABLE " + LISTPRODUCT_TABLE
            + "(" + PRODUCT_ID + " INTEGER," + LIST_ID + " INTEGER," + QUANTITY + " INTEGER,"
            + " FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT_TABLE(PRODUCT_ID)," + " FOREIGN KEY(LIST_ID) REFERENCES LIST_TABLE(LIST_ID)" + ")";
    //Create statement for inventoryproduct_table
    private static final String CREATE_INVENTORYPRODUCT_TABLE = "CREATE TABLE " + INVENTORYPRODUCT_TABLE
            + "(" + INVENTORY_ID + " INTEGER," + PRODUCT_ID + " INTEGER," + QUANTITY + " INTEGER,"
            + " FOREIGN KEY(INVENTORY_ID) REFERENCES INVENTORY_TABLE(INVENTORY_ID)," + " FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT_TABLE(PRODUCT_ID)" + ")";

    /**********************************************************
     *              DATABASE CREATION AND METHOD HANDLING
     **********************************************************/
    public AppDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_LIST_TABLE);
        db.execSQL(CREATE_INVENTORY_TABLE);
        db.execSQL(CREATE_LISTPRODUCT_TABLE);
        db.execSQL(CREATE_INVENTORYPRODUCT_TABLE);
     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    public void addProduct(Product product){
        Log.v(TAG, "Attempting to add Product");
        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, product.getName());
        values.put(PRODUCT_PRICE, product.getPrice());
        values.put(PRODUCT_AISLE, product.getAisle());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(PRODUCT_TABLE, null, values);
        Log.d(TAG, "Product has been added");
        db.close();
    }

    public void addList(String listName){
        ContentValues values = new ContentValues();
        values.put(LIST_NAME, listName);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(LIST_TABLE, null, values);
        db.close();
    }

    public void addInventory(String inventoryName){
        ContentValues values = new ContentValues();
        values.put(INVENTORY_NAME, inventoryName);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(INVENTORY_TABLE, null, values);
        db.close();
    }

    public ArrayList<String> feedNewList(){
        String query = "SELECT * FROM " + PRODUCT_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> mProduct = new ArrayList<String>();
        Cursor c = db.rawQuery(query, null);


        int Column1 = c.getColumnIndex(PRODUCT_NAME);
        //int Column2 = c.getColumnIndex(PRODUCT_PRICE);


        while (c.moveToNext()){
            String Name = c.getString(Column1);
            //int Price = c.getInt(Column2);
            mProduct.add(Name/* + " " + Price*/);

        }
        return mProduct;
    }

    public void editProduct(String name){}

    public void addListProduct(){}

    public void addInventoryProduct(){}

}
