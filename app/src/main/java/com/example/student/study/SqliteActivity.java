package com.example.student.study;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.example.student.study.SQlite.Helper.CreateProductHelper;


public class SqliteActivity extends ActionBarActivity {

    CreateProductHelper mHelper = new CreateProductHelper(this);
    SQLiteDatabase mDb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        //インスタンス生成　*書き込み用
        //insert , delete, update
        mDb = mHelper.getWritableDatabase();

        try{
            //トランザクション開始
            mDb.beginTransaction();

            //データ登録
            ContentValues val = new ContentValues();
            val.put("name", "kato");
            val.put("add", "tokyo");
            val.put("tel", "080-0000-0000");
            mDb.insert("users", null, val);
            //トランザクションのコミット
            mDb.setTransactionSuccessful();

        }catch(Exception e){

        }finally{
            mDb.endTransaction();
        }

        //データの更新
        //データの削除
        //データの表示

        mDb = mHelper.getReadableDatabase();

        try{
            //列名定義
            String columns[] = {"id", "name", "add", "tel"};
            //データ取得
            Cursor cursor = mDb.query(
                    "user", columns, null, null, null, null,"id", null);

            while(cursor.moveToNext()){
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String add = cursor.getString(2);
                String tel = cursor.getString(3);

                Toast.makeText(this,"id:" + id + "name:" + name ,Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){}finally{
            mDb.close();
        }

    }


}
