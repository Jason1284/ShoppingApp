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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SG0216351 on 11/1/2017.
 */

public class AppDatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "APPDatabaseHelper";

    //Database name and version
    public static final int DATABASE_VERSION =    1;
    public static final String DATABASE_NAME = "PRODUCTS.db";

    /**********************************************************
     *              TABLE NAMES
     **********************************************************/
    public static final String PRODUCT_TABLE = "product_table";
    public static final String LISTPRODUCT_TABLE = "listproduct_table";
    public static final String INVENTORYPRODUCT_TABLE = "inventoryproduct_table";
    public static final String LIST_TABLE = "list_table";
    public static final String INVENTORY_TABLE = "inventory_table";
    /**********************************************************
     *              PRODUCT TABLE COLUMNS NAMES
     **********************************************************/
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_AISLE = "aisle";
    public static final String PRODUCT_VALUE = "value";
    /**********************************************************
     *              LIST TABLE COLUMNS NAMES
     **********************************************************/
    public static final String LIST_ID = "list_id";
    public static final String LIST_NAME = "list_name";
    public static final String AISLE = "aisle";
    public static final String QUANTITY = "quantity";
    /**********************************************************
     *              INVENTORY TABLE COLUMN NAMES
     **********************************************************/
    public static final String INVENTORY_ID = "inventory_id";
    public static final String INVENTORY_NAME = "inventory_name";


    //Create statement for product_table
    public static final String CREATE_PRODUCT_TABLE = "CREATE TABLE " + PRODUCT_TABLE
            + "(" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PRODUCT_NAME + " TEXT NOT NULL UNIQUE,"
            + PRODUCT_PRICE + " TEXT," + PRODUCT_AISLE + " TEXT," + PRODUCT_VALUE + " INTEGER DEFAULT 0" + ")";
    //Create statement for list_table
    public static final String CREATE_LIST_TABLE = "CREATE TABLE " + LIST_TABLE
            + "(" + LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + LIST_NAME + " TEXT UNIQUE" + ")";
    //Create statement for inventory_table
    public static final String CREATE_INVENTORY_TABLE = "CREATE TABLE " + INVENTORY_TABLE
            + "(" + INVENTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + INVENTORY_NAME + " TEXT UNIQUE" + ")";
    //Create statement for listproduct_table
    public static final String CREATE_LISTPRODUCT_TABLE = "CREATE TABLE " + LISTPRODUCT_TABLE
            + "(" + PRODUCT_ID + " INTEGER," + LIST_ID + " INTEGER," + QUANTITY + " INTEGER,"
            + " FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT_TABLE(PRODUCT_ID)," + " FOREIGN KEY(LIST_ID) REFERENCES LIST_TABLE(LIST_ID)" + ")";
    //Create statement for inventoryproduct_table
    public static final String CREATE_INVENTORYPRODUCT_TABLE = "CREATE TABLE " + INVENTORYPRODUCT_TABLE
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
        values.put(PRODUCT_VALUE, product.getValue());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(PRODUCT_TABLE, null, values);
        Log.d(TAG, "Product has been added");
        
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

    public Cursor feedNewList(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + PRODUCT_TABLE, null);
        return data;
    }

    public Cursor allLists(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + LIST_TABLE, null);
        return data;
    }

    public Cursor allInventories(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + INVENTORY_TABLE, null);
        return data;
    }

    public void editProduct(String name){}

    public void addListProduct(String name, Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        String list_id;
        String product_id;
        list_id = "SELECT " + LIST_ID + " FROM " + LIST_TABLE + " WHERE " + LIST_NAME + " = '" + name + "'";
        product_id = "SELECT " + PRODUCT_ID + " FROM " + PRODUCT_TABLE + " WHERE " + PRODUCT_NAME + " = '" + product.getName() + "'";
        Cursor idList = db.rawQuery(list_id, null);
        Cursor idProduct = db.rawQuery(product_id, null);
        int listId = 0;
        int productId = 0;
       while (idList.moveToNext()) {
            listId = idList.getInt(0);
       }
        while (idProduct.moveToNext()) {
            productId = idProduct.getInt(0);
        }
        ContentValues values = new ContentValues();
        values.put(LIST_ID, listId);
        values.put(PRODUCT_ID, productId);
        values.put(QUANTITY, product.getQuantity());

        db.insert(LISTPRODUCT_TABLE, null, values);
        db.close();
    }

    public void addInventoryProduct(String name, Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        String inventory_id;
        String product_id;
        inventory_id = "SELECT " + INVENTORY_ID + " FROM " + INVENTORY_TABLE + " WHERE " + INVENTORY_NAME + " = '" + name + "'";
        product_id = "SELECT " + PRODUCT_ID + " FROM " + PRODUCT_TABLE + " WHERE " + PRODUCT_NAME + " = '" + product.getName() + "'";
        Cursor idInventory = db.rawQuery(inventory_id, null);
        Cursor idProduct = db.rawQuery(product_id, null);
        int inventoryId = 0;
        int productId = 0;
        while (idInventory.moveToNext()) {
            inventoryId = idInventory.getInt(0);
        }
        while (idProduct.moveToNext()) {
            productId = idProduct.getInt(0);
        }
        ContentValues values = new ContentValues();
        values.put(INVENTORY_ID, inventoryId);
        values.put(PRODUCT_ID, productId);
        values.put(QUANTITY, product.getQuantity());

        db.insert(INVENTORYPRODUCT_TABLE, null, values);
        db.close();
    }

    public List<Product> displayListProducts(String list){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Product> products = new ArrayList<Product>();

        Product product = new Product();
        int listId = 0;
        Integer prodId;

        //FIND LIST ID ACCORDING TO LIST NAME RECEIVED BY PARAMETER
        String listToBeUsed = "SELECT " + LIST_ID + " FROM " + LIST_TABLE + " WHERE " + LIST_NAME + " = '" + list + "'";
        Cursor findId = db.rawQuery(listToBeUsed, null);
        while (findId.moveToNext()) {
            listId = findId.getInt(0);
        }

        //FIND ALL PRODUCT IDS AND QUANTITY FOR THE CORRESPONDING LIST ID IN LISTPRODUCT TABLE
        String wholeListProd = "SELECT * FROM " + LISTPRODUCT_TABLE + " WHERE " + LIST_ID + " = '" + listId + "'";
        Cursor all = db.rawQuery(wholeListProd, null);
        Integer prodSearch[] = new Integer[all.getCount()];
        String quantity[] = new String[all.getCount()];
        int i = 0;

        while (all.moveToNext()) {
            prodSearch[i] = all.getInt(0);
            quantity[i] = all.getString(2);
            i++;
        }


        //USE PRODUCT IDS FOUND TO ADD PRODUCT INFORMATION TO PRODUCT INSTANCES AND ADD THEM TO ARRAYLIST TO BE RETURNED.
        int k = 0;
        for(int j = 0; j < all.getCount(); j++) {
            String finalProdList = "SELECT * FROM " + PRODUCT_TABLE + " WHERE " + PRODUCT_ID + " = '" + prodSearch[i] + "'";
            Cursor prods = db.rawQuery(finalProdList, null);
            while (prods.moveToNext()) {
                product.setName(prods.getString(1));
                product.setPrice(prods.getString(2));
                product.setAisle(prods.getString(3));
                product.setQuantity(quantity[k]);
                k++;

                products.add(product);
            }
        }

        return products;
    }

}
