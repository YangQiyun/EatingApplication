package carteen.edu.seu.com.carteen.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Mind on 2017/11/5.
 */
public class DBmanager {
    private String DB_NAME = "canteendb.db";
    private Context mContext;
    private static final String TAG = "DBmanager";

    public DBmanager(Context mContext) {
        this.mContext = mContext;
    }

    public  boolean delete(String packName){
        String sdpath=getSDPath();
        String dbPath = sdpath+"/data/data/" + packName+"/database/"
                + DB_NAME;
        String pathStr=sdpath+"/data/data/" + packName+"/database/";
        File jhPath=new File(dbPath);
        //查看数据库文件是否存在
        if(jhPath.exists()) {
            Log.i("carteen", "正在删除数据库");
            return jhPath.delete();
        }
        return  false;
    }
    //把assets目录下的db文件复制到dbpath下
    public SQLiteDatabase DBmanager(String packName) {
        String sdpath=getSDPath();
        String dbPath = sdpath+"/data/data/" + packName+"/database/"
        + DB_NAME;
        String pathStr=sdpath+"/data/data/" + packName+"/database/";
        File jhPath=new File(dbPath);
        //查看数据库文件是否存在
        if(jhPath.exists()){
            Log.i("carteen", "存在数据库");
            //存在则直接返回打开的数据库
            return SQLiteDatabase.openOrCreateDatabase(jhPath, null);
        }else {
            //不存在先创建文件夹
            File path = new File(pathStr);
            Log.i("carteen", "pathStr=" + path);
            if (path.mkdirs()) {
                Log.i("carteen", "创建成功");
            } else {
                Log.i("carteen", "创建失败");
            }
            ;
            try {
                //得到资源
                AssetManager am = mContext.getAssets();
                //得到数据库的输入流
                InputStream is = am.open("canteendb.db");
                Log.i("carteen", is + "");
                //用输出流写到SDcard上面
                FileOutputStream fos = new FileOutputStream(jhPath);
                Log.i("carteen", "fos=" + fos);
                Log.i("carteen", "jhPath=" + jhPath);
                //创建byte数组  用于1KB写一次
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                   // Log.i("carteen", "得到");
                    fos.write(buffer, 0, count);
                }
                //最后关闭就可以了
                fos.flush();
                fos.close();
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
            //如果没有这个数据库  我们已经把他写到SD卡上了，然后在执行一次这个方法 就可以返回数据库了
            return DBmanager(packName);
        }
    }

    //插入
    public boolean insert(SQLiteDatabase sqliteDB, int foodId, int userId, String fcmtTime, String foodcom){
        String[] colums=new String[]{"fcmtId"};
        Cursor cursor = sqliteDB.query("foodcomment", colums, null, null, null, null, null);
        Log.i(TAG, "insert: here");
        int fcmtId;
        if (cursor.moveToLast()) {
            fcmtId = cursor.getInt(cursor
                        .getColumnIndex("fcmtId"));
            cursor.close();
        }
        else
            return false;
        ContentValues values=new ContentValues();
        fcmtId++;
        values.put("foodId",foodId);
        values.put("userId",userId);
        values.put("fcmtTime",fcmtTime);
        values.put("foodcom",foodcom);
        values.put("fcmtId",fcmtId);
        try {
            sqliteDB.beginTransaction();
            long result=sqliteDB.insert("foodcomment",null,values);
            if(result==-1)
            {
                Log.d(TAG, "insert: "+result);
                return false;
            }
            else{
                sqliteDB.endTransaction();
                return true;
            }

        }
        catch (Exception e) {
            Log.d(TAG, "insert: "+e.getMessage());
        }
        return true;
    }


    //查询
    public String[] query(SQLiteDatabase sqliteDB, String[] columns, String selection, String[] selectionArgs) {
        ArrayList<String> result=new ArrayList<>();
        int i=0;
        try {
            String table = "windows";
            Cursor cursor = sqliteDB.query(table, columns, selection, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                boolean havenext=true;
                while (havenext) {
                    String string = cursor.getString(cursor
                            .getColumnIndex("winName"));
                    result.add(string);
                    havenext=cursor.moveToNext();
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] mResult=new String[result.size()];
        result.toArray(mResult);
        return mResult;
    }
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);// 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        }
        return sdDir.toString();
    }
}
