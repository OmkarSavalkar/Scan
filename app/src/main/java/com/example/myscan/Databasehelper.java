package com.example.myscan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myscan.modelscanstore.Listitem;

import java.util.ArrayList;

public class Databasehelper extends SQLiteOpenHelper {

    public static final String Databasename="scanbilldatabase.db";
    public static final String table_name="scanbill_table";
    public static final String col1_id="id";
    public static final String col2_productname="pname";
    public static final String col3_price="price";

    public Databasehelper(Context context) {
        super(context, Databasename, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+table_name+"(id INTEGER PRIMARY KEY AUTOINCREMENT, pname TEXT,price FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ table_name);
        onCreate(sqLiteDatabase);
    }

    public boolean insertdata(String code,String type)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2_productname,code);
        contentValues.put(col3_price,type);

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long res=sqLiteDatabase.insert(table_name,null,contentValues);
        if (res==-1)
            return false;
        else
            return true;
    }

    public ArrayList<Listitem> getinformation()
    {
        ArrayList<Listitem> arraylist=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+table_name,null);
        if(cursor!=null)
        {
            while (cursor.moveToNext())
            {
                int id=cursor.getInt(0);
                String productname=cursor.getString(1);
                String price=cursor.getString(2);

                Listitem listitem=new Listitem(id,productname,price);
                arraylist.add(listitem);

            }
        }
        cursor.close();
        return arraylist;
    }
}
