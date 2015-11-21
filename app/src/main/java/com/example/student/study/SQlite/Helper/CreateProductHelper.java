package com.example.student.study.SQlite.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by student on 2015/11/21.
 */
public class CreateProductHelper extends SQLiteOpenHelper{


    public CreateProductHelper(Context context) {
        super(context, "sample", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            String sql = "CREATE TABLE [user] (" +
                    "[id] INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "[name] VARCHAR(20), " +
                    "[add] VARCHAR(20), " +
                    "[tel] INTEGER " +
                    ");";

            db.execSQL(sql);
        }catch(Exception e){
            //no_operation
            //すでにデータベースが存在している場合の処理
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
