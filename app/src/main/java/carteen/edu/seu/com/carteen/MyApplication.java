package carteen.edu.seu.com.carteen;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

import carteen.edu.seu.com.carteen.Utils.DBmanager;

/**
 * Created by Bob on 15/8/18.
 */
public class MyApplication extends Application {

    private static MyApplication instance;
    public static int screenWidth, screenHeight;
    public static String cache_image_path, photo_path;
    public static File cacheImageDir, photoDir;
    public static DBmanager dBmanager;
    public static SQLiteDatabase sqLiteDatabase;
    public static MyApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {

        super.onCreate();

        instance = this;
        File cachefile=getCacheDir();


        getScreenDimension();
        initImageLoader(this);

        /**
         *
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第一步 初始化
             */
         //   RongIM.init(this);

            if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

               // DemoContext.init(this);
            }
        }

        //初始化数据库
        dBmanager=new DBmanager(getApplicationContext());
        sqLiteDatabase=dBmanager.DBmanager("canteen");
    }

    public void getScreenDimension() {
        WindowManager mWM = ((WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        mWM.getDefaultDisplay().getMetrics(mDisplayMetrics);
        screenWidth = mDisplayMetrics.widthPixels;
        screenHeight = mDisplayMetrics.heightPixels;
    }

    //先初始化UniversalImageLoader
    private void initImageLoader(Context context) {
        File cacheDir = createCacheDir();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .threadPriority(Thread.NORM_PRIORITY - 1)
                .threadPoolSize(3)
                .memoryCacheExtraOptions(480, 800)
                .diskCacheExtraOptions(480, 800, null)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCacheSize( 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                // .discCacheFileCount(200)
                // .defaultDisplayImageOptions(options)
                .diskCache(new UnlimitedDiskCache(cacheDir)).build();

        // Initialize ImageLoader with configuration.
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
    }

    private File createCacheDir() {

        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            cache_image_path = sdcardDir.getPath() + "/shacus/cache/images/";
            cacheImageDir = new File(cache_image_path);
            photo_path = sdcardDir.getPath() + "/shacus/cache/photoes/";
            photoDir = new File(photo_path);
        } else {
            photo_path= "/storage/emulated/0"+"/shacus/cache/photoes/";
            cacheImageDir = new File("/storage/emulated/0"+"/shacus/cache/images");
            photoDir = new File(photo_path);
        }
        if (!cacheImageDir.exists()) {
            cacheImageDir.mkdirs();
        }
        if (!photoDir.exists()) {
            photoDir.mkdirs();
        }
        return cacheImageDir;
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 获取Context
     * @return 返回Context的对象
     */
    public static Context getContext(){
        return instance.getApplicationContext();
    }
}

