package com.seu.srtp.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.io.File;

/**
 * Created by swallowman on 2017/3/15.
 */

public class DBer {
    private SQLiteDatabase self;
    //给定名字，创建数据库
    public DBer(String DBname){
        try{
            File DB=new File(Configuration.DB_PATH + DBname);
            try {
                self=SQLiteDatabase.openOrCreateDatabase(DB,null);
            }catch (SQLiteException e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //创建表
    private void creatAll(){
        //创建表SQL语句
        String user_table="create table user(id integer primary key autoincrement,account integer,password text)";
        //执行SQL语句
        self.execSQL(user_table);
    }

    //插入数据
}
