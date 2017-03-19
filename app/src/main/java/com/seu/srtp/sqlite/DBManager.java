package com.seu.srtp.sqlite;

/**
 * Created by swallowman on 2017/3/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.seu.srtp.data.User;

public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    /**
     * add user
     * @param user
     */
    public boolean add(User user) {
        db.beginTransaction();  //开始事务
        try{
            String sql_query="SELECT * FROM user WHERE account="+user.account;
            Cursor cursor = db.rawQuery(sql_query, null);
            if(cursor.getCount()!=0)
                return false;
            else{
                try {
                    String sql_add="INSERT INTO user(account,password) VALUES ('"+user.account+"','"+user.password+"')";
                    db.execSQL(sql_add);
                    db.setTransactionSuccessful();  //设置事务成功完成
                    return true;
                } catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        }finally{
            db.endTransaction();    //结束事务
        }
    }
    public User queryPsd(int account){
        User user=new User();
        String psd=null;
        String sql = "SELECT * FROM user WHERE account = "+account ;
        try{
            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToFirst();
            user.set_id(cursor.getInt(cursor
                    .getColumnIndex("_id")));
            user.setAccount(cursor.getInt(cursor
                    .getColumnIndex("account")));
            user.setPassword(cursor.getString(cursor
                        .getColumnIndex("password")));
        }catch (Exception e){
           e.printStackTrace();
        }
        return user;
    }

    /**
     * update user's psd
     * @param user
     */
    public void updatePsd(User user) {
        ContentValues cv = new ContentValues();
        cv.put("password", user.password);
        db.update("user", cv, "password = ?", new String[]{user.password});
    }

    /**
     * delete old user
     * @param user
     */
    public void deleteOldUser(User user) {
        db.delete("user", "account = ?", new String[]{String.valueOf(user.account)});
    }

    /**
     * query all persons, return list
     * @return List<User>
     */
    public boolean query() {
        User user=new User();
        String psd=null;
        String sql = "SELECT * FROM user";
        try{
            Cursor cursor = db.rawQuery(sql, null);
            if(cursor.getCount()==0)
                return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * query all user, return cursor
     * @return  Cursor
     */
    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM user", null);
        return c;
    }
    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}