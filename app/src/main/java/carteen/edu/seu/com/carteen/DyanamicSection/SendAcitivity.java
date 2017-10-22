package carteen.edu.seu.com.carteen.DyanamicSection;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import carteen.edu.seu.com.carteen.Activity.BaseActivity;
import carteen.edu.seu.com.carteen.DyanamicSection.util.ImageAddGridViewAdapter;
import carteen.edu.seu.com.carteen.DyanamicSection.util.UploadPhotoUtil;
import carteen.edu.seu.com.carteen.MyApplication;
import carteen.edu.seu.com.carteen.R;
import carteen.edu.seu.com.carteen.Utils.ImageDisplayFragment;
import carteen.edu.seu.com.carteen.Utils.ImagePagerAdapter;
import carteen.edu.seu.com.carteen.Utils.ImgAddGridView;
import carteen.edu.seu.com.carteen.Utils.OnSingleTapDismissBigPhotoListener;
import carteen.edu.seu.com.carteen.Utils.PhotoViewAttacher;
import carteen.edu.seu.com.carteen.Utils.UploadViewPager;

/**
 * Created by Mind on 2017/10/22.
 */
public class SendAcitivity extends BaseActivity implements View.OnClickListener,
        OnSingleTapDismissBigPhotoListener {
    private final int UPLOAD_TAKE_PICTURE=5;
    private ProgressDialog progressDlg;
    private final int NONE=0,TAKE_PICTURE=1,LOCAL_PICTURE=2;
    private final int SAVE_THEME_IMAGE=8;
    private final int SHOW_TAKE_PICTURE=9;
    private final int SHOW_LOCAL_PICTURE=10;
    private FrameLayout edit_photo_fullscreen_layout;
    private RelativeLayout edit_photo_outer_layout,display_big_image_layout,show_upload_pic_layout;
    private Animation get_photo_layout_out_from_up,get_photo_layout_in_from_down;
    private TextView take_picture,select_local_picture,position_in_total,upload;
    private ImageView delete_image;
    private String takePictureUrl;
    private Intent intent;
    private ImgAddGridView add_image_gridview;
    private int picToAdd=-1;
    private int addPicCount=1,addTakePicCount=1,viewpagerPosition;
    private List<String> uploadImgUrlList=new ArrayList<>();
    private List<Drawable>addPictureList=new ArrayList<>();
    private List<String>pictureUrlList=new ArrayList<>();
    private ImageAddGridViewAdapter imageAddGridViewAdapter;
    private ImagePagerAdapter imagePagerAdapter;
    private UploadViewPager image_viewpager;
    private boolean isBigImageShow=false,isShowUploadPic=false,addPic=false,clearFormerUploadUrlList=true;
    private EditText theme_title_edit,theme_desc_edit;
    private int apId;//作品集id
    private ArrayList<String> imgList;
    private ArrayList<String> finalImgList;
    private FrameLayout layout_upload;
    private Handler handler;
    private int type=-1;//类型 1为上传个人照片 2为发布作品集 3为给作品集添加图片  4为发起论坛  5发动态
    private TextView title;
    private ImageButton back;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        //requestFragment.httpRequest(null,CommonUrl.imgSelfAndSets);
        type=1;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},0);
        }

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//箭头的点击事件
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title=(TextView)findViewById(R.id.lost_title);

        edit_photo_fullscreen_layout=(FrameLayout)findViewById(R.id.edit_photo_fullscreen_layout);
        edit_photo_fullscreen_layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                hideBigPhotoLayout();
            }
        });
        edit_photo_outer_layout=(RelativeLayout)findViewById(R.id.edit_photo_outer_layout);
        TextView cancelEditPhoto=(TextView)edit_photo_outer_layout.findViewById(R.id.cancel_upload);
        cancelEditPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                hideBigPhotoLayout();
            }
        });
        display_big_image_layout=(RelativeLayout)findViewById(R.id.display_big_image_layout);
        show_upload_pic_layout=(RelativeLayout)findViewById(R.id.show_upload_pic_layout);
        take_picture=(TextView)findViewById(R.id.take_picture);
        position_in_total=(TextView)findViewById(R.id.position_in_total);
        select_local_picture=(TextView)findViewById(R.id.select_local_picture);
        upload=(TextView)findViewById(R.id.upload);
        upload.setVisibility(View.VISIBLE);
        theme_title_edit=(EditText)findViewById(R.id.lost_title);
        theme_desc_edit=(EditText)findViewById(R.id.lost_content);
        LinearLayout ll=(LinearLayout)findViewById(R.id.theme_desc_edit_layout);
        layout_upload=(FrameLayout)findViewById(R.id.layout_upload);
        delete_image=(ImageView)findViewById(R.id.delete_image);
        add_image_gridview=(ImgAddGridView)findViewById(R.id.add_image_gridview);
        add_image_gridview.setExpanded(true);
        image_viewpager=(UploadViewPager)findViewById(R.id.image_viewpager);
        upload.setOnClickListener(this);
        delete_image.setOnClickListener(this);
        ImageDisplayFragment.showNetImg=false;
        addPictureList.add(getResources().getDrawable(R.mipmap.theme_add_picture_icon));
        imageAddGridViewAdapter=new ImageAddGridViewAdapter(this, addPictureList);
        add_image_gridview.setAdapter(imageAddGridViewAdapter);
        //mProgress = (ProgressBar) root.findViewById(R.id.uploading_photo_progress);
        add_image_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //这里是添加图片的按钮的回调
                if (position == 0) {
                    if (addPicCount == 10){
                        Toast.makeText(SendAcitivity.this,"不可添加更多照片了",Toast.LENGTH_SHORT).show();
                    } else {
                        //点击添加图片
                        edit_photo_fullscreen_layout.setVisibility(View.VISIBLE);
                        get_photo_layout_in_from_down = AnimationUtils.loadAnimation(SendAcitivity.this, R.anim.search_layout_in_from_down);
                        edit_photo_outer_layout.startAnimation(get_photo_layout_in_from_down);
                    }
                } else {
                    //点击图片查看大图
                    showImageViewPager(position, pictureUrlList, uploadImgUrlList, "local", "upload");
                    viewpagerPosition = position - 1;
                }
            }
        });

        take_picture.setOnClickListener(this);
        select_local_picture.setOnClickListener(this);

        handler=new Handler(){

            // 选完图片后就是在这里处理的
            @Override
            public void handleMessage(Message msg){
                switch(msg.what){
                    //在图库选中了本地的图
                    case SHOW_LOCAL_PICTURE:
                        //获取到资源位置
                        Uri uri=intent.getData();
                        String photo_local_file_path=getPath_above19(SendAcitivity.this.getApplicationContext(), uri);
                        //程序员啊不要见得风就是雨，要有自己的判断，用户选出来的文件要判断它是不是真的图片
                        //如果不是，这个错的东西你再帮他传一遍，等于你也有责任吧
                        if (!(photo_local_file_path.toString().toLowerCase().endsWith("jpg")||photo_local_file_path.toString().toLowerCase().endsWith("png")
                                ||photo_local_file_path.toString().toLowerCase().endsWith("jpeg")||photo_local_file_path.toString().toLowerCase().endsWith("gif"))){
                            Toast.makeText(SendAcitivity.this,"不支持此格式的上传",Toast.LENGTH_SHORT).show();
                            break;
                        }
                        addPic=true;
                        if(clearFormerUploadUrlList){
                            if(uploadImgUrlList.size()>0){
                                uploadImgUrlList.clear();
                            }
                            clearFormerUploadUrlList=false;
                        }
                        Bitmap bitmap2= UploadPhotoUtil.getInstance().trasformToZoomBitmapAndLessMemory(photo_local_file_path);
                        if (bitmap2==null)
                            finish();
                        addPictureList.add(new BitmapDrawable(getResources(), bitmap2));
                        uploadImgUrlList.add(photo_local_file_path);
                        imageAddGridViewAdapter.changeList(addPictureList);
                        imageAddGridViewAdapter.notifyDataSetChanged();
                        addPicCount++;

                        break;
                }
            }
        };

        final Button buttonfind= (Button) findViewById(R.id.btn_find);
        final Button buttonlost= (Button) findViewById(R.id.btn_lost);
        buttonfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isSelected()){
                    v.setSelected(false);
                    buttonlost.setSelected(true);
                }
                else {
                    v.setSelected(true);
                    buttonlost.setSelected(false);
                }
            }
        });
        buttonlost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isSelected()){
                    v.setSelected(false);
                    buttonfind.setSelected(true);
                }
                else {
                    v.setSelected(true);
                    buttonfind.setSelected(false);
                }
            }
        });
    }

    //监听返回键，有弹出层时关闭弹出层，否则停止activity
    @Override
    public void onBackPressed() {
        if (display_big_image_layout.getVisibility()==View.GONE)
            finish();
        else
            hideBigPhotoLayout();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }


    //是否为外置存储器
    public static boolean isExternalStorageDocument(Uri uri){
        return"com.android.externalstorage.documents".equals(uri.getAuthority());
    }
    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri){
        return"com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri){
        return"com.android.providers.media.documents".equals(uri.getAuthority());
    }
    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.*/

    public static boolean isGooglePhotosUri(Uri uri){
        return"com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[]selectionArgs){
        Cursor cursor=null;
        final String column="_data";
        final String[]projection={column};
        try{
            cursor=context.getContentResolver().query(uri,projection,selection,selectionArgs, null);
            if(cursor!=null&&cursor.moveToFirst()){
                final int index=cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        }finally{
            if(cursor!=null)
                cursor.close();
        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath_above19(final Context context,final Uri uri){
        final boolean isKitKat=Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if(isKitKat&& DocumentsContract.isDocumentUri(context, uri)){
            // ExternalStorageProvider
            if(isExternalStorageDocument(uri)){
                final String docId=DocumentsContract.getDocumentId(uri);
                final String[]split=docId.split(":");
                final String type=split[0];
                if("primary".equalsIgnoreCase(type)){
                    return Environment.getExternalStorageDirectory()+"/"+split[1];
                }

            }
            // DownloadsProvider
            else if(isDownloadsDocument(uri)){
                final String id=DocumentsContract.getDocumentId(uri);
                final Uri contentUri= ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context,contentUri,null,null);
            }
            // MediaProvider
            else if(isMediaDocument(uri)){
                final String docId=DocumentsContract.getDocumentId(uri);
                final String[]split=docId.split(":");
                final String type=split[0];
                Uri contentUri=null;
                if("image".equals(type)){
                    contentUri= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                }else if("video".equals(type)){
                    contentUri=MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                }else if("audio".equals(type)){
                    contentUri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection="_id=?";
                final String[]selectionArgs=new String[]{
                        split[1]
                };
                return getDataColumn(context,contentUri,selection,selectionArgs);
            }
        }
        // MediaStore (and general)
        else if("content".equalsIgnoreCase(uri.getScheme())){
            // Return the remote address
            if(isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(context,uri,null,null);
        }
        // File
        else if("file".equalsIgnoreCase(uri.getScheme())){
            return uri.getPath();
        }
        return null;
    }

    //加监听，等到view完全显示了再去做调整
    /*public void onViewCreated(final View view, Bundle saved) {
        super.onViewCreated(view, saved);
        final ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                ViewGroup.MarginLayoutParams big_picLayoutParam = (ViewGroup.MarginLayoutParams) display_big_image_layout.getLayoutParams();
                big_picLayoutParam.bottomMargin = upload.getHeight();
                display_big_image_layout.setLayoutParams(big_picLayoutParam);
            }
        });
    }*/

    public void showImageViewPager(int position,
                                   final List<String>pictureUrlList,final List<String>localUrlList,
                                   final String flag,final String str){
        List<String>urlList;
        if(flag.equals("net")){
            urlList=pictureUrlList;
        }else{
            urlList=localUrlList;
        }
        display_big_image_layout.setVisibility(View.VISIBLE);
        imagePagerAdapter=new ImagePagerAdapter(SendAcitivity.this.getSupportFragmentManager(),urlList);
        image_viewpager.setAdapter(imagePagerAdapter);
        imagePagerAdapter.notifyDataSetChanged();
        image_viewpager.setOffscreenPageLimit(imagePagerAdapter.getCount());
        image_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            @Override
            public void onPageSelected(int position) {

                viewpagerPosition = position;
                if (flag.equals("net")) {
                    position_in_total.setText((position + 1) + "/" + pictureUrlList.size());
                } else {
                    position_in_total.setText((position + 1) + "/" + localUrlList.size());
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }
        });
        if(str.equals("display")){
            image_viewpager.setCurrentItem(position);
            delete_image.setVisibility(View.GONE);
            position_in_total.setText((position+1)+"/"+urlList.size());
            layout_upload.setVisibility(View.GONE);
            isBigImageShow=true;

        }else{
            image_viewpager.setCurrentItem(position-1);
            delete_image.setVisibility(View.VISIBLE);
            position_in_total.setText((position)+"/"+urlList.size());
            layout_upload.setVisibility(View.GONE);
            isBigImageShow=true;

        }
        PhotoViewAttacher.setOnSingleTapToPhotoViewListener(this);
    }

    // 下面函数是选中了要上传图片或者拍照打完钩后运行的
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(resultCode==NONE)
            return;
        //耗时操作传到handler里去处理
        if(requestCode==TAKE_PICTURE){
            handler.sendEmptyMessage(SHOW_TAKE_PICTURE);
            return;
        }
        if(resultCode== Activity.RESULT_OK){
            this.intent=intent;
            handler.sendEmptyMessage(SHOW_LOCAL_PICTURE);
        }
    }

    @Override
//选择本地图片 拍照 取消 三个按钮 发表按钮
    public void onClick(View view){
        Intent intent;
        switch(view.getId()){
            case R.id.take_picture:
                edit_photo_fullscreen_layout.setVisibility(View.GONE);
                takePictureUrl= MyApplication.photo_path+"picture_take_0"
                        +addTakePicCount+".jpg";
                File file=new File(takePictureUrl);
                intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));
                startActivityForResult(intent,TAKE_PICTURE);
                addTakePicCount++;
                break;
            case R.id.select_local_picture:
                edit_photo_fullscreen_layout.setVisibility(View.GONE);
                intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        "image/*");
                startActivityForResult(intent,LOCAL_PICTURE);
                break;
            /*case R.id.cancel_upload:
                edit_photo_fullscreen_layout.setVisibility(View.GONE);*/
            case R.id.upload://第一次握手：按发表键后
                //检查用户是否登录
                break;
            case R.id.delete_image:
                if(uploadImgUrlList.size()==0){
                    return;
                }
                uploadImgUrlList.remove(viewpagerPosition);
                imagePagerAdapter.changeList(uploadImgUrlList);
                imagePagerAdapter.notifyDataSetChanged();
                addPictureList.remove(viewpagerPosition+1);
                imageAddGridViewAdapter.changeList(addPictureList);
                imageAddGridViewAdapter.notifyDataSetChanged();
                position_in_total.setText((viewpagerPosition+1)+"/"+uploadImgUrlList.size());
                if(uploadImgUrlList.size()==0){
                    display_big_image_layout.setVisibility(View.GONE);
                    layout_upload.setVisibility(View.VISIBLE);
                    isBigImageShow=false;
                }
                addPicCount--;
                break;
        }
    }

    private boolean checkInput() throws ParseException {


        if (type>1&&(theme_title_edit.getText().toString().equals("")||theme_title_edit.getText().length()>20)){
           Toast.makeText(SendAcitivity.this,"请正确输入标题（20字以内）",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (type>1&&(theme_desc_edit.getText().length()<15)){
            Toast.makeText(SendAcitivity.this,"请输入15字以上的详细描述",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (uploadImgUrlList.size()<1){
            Toast.makeText(SendAcitivity.this,"请至少配上一张图片",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



    @Override
    public void onDismissBigPhoto(){
        hideDisplayBigImageLayout();
    }

    private void hideDisplayBigImageLayout(){
        display_big_image_layout.setVisibility(View.GONE);
        isBigImageShow=false;
        layout_upload.setVisibility(View.VISIBLE);
    }



    public void hideBigPhotoLayout(){
        display_big_image_layout.setVisibility(View.GONE);
        edit_photo_fullscreen_layout.setVisibility(View.GONE);
    }

}
