package com.example.myscan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myscan.modelscanstore.Listitem;

import java.util.ArrayList;

public class Dbase extends SQLiteOpenHelper
{
    public static final String tname="mytable";
    public static final String Dbname="qrdb.db";

    public static final String col1="id";
    public static final String col2="code";
    public static final String col3="type";

    public Dbase(Context context) {
        super(context, Dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+tname+"(id INTEGER PRIMARY KEY AUTOINCREMENT, code TEXT,type TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +tname);
        onCreate(sqLiteDatabase);
    }

    public boolean insertdata(String code,String type)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,code);
        contentValues.put(col3,type);

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long res=sqLiteDatabase.insert(tname,null,contentValues);
        if (res==-1)
            return false;
        else
            return true;
    }

    public ArrayList<Listitem> getinformation()
    {
        ArrayList<Listitem> arraylist=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+tname,null);
        if(cursor!=null)
        {
            while (cursor.moveToNext())
            {
                int id=cursor.getInt(0);
                String code=cursor.getString(1);
                String type=cursor.getString(2);

                Listitem listitem=new Listitem(id,code,type);
                arraylist.add(listitem);

            }
        }
        cursor.close();
        return arraylist;
    }

    public void deleterow(int val)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("DELETE FROM "+tname+" WHERE "+col2+"="+"val");
    }


}
