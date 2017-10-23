package carteen.edu.seu.com.carteen.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import carteen.edu.seu.com.carteen.R;

/**
 * Created by Mind on 2017/10/11.
 */
public class LostInfor implements Serializable{
    private int lostid;
    private int lostCanteen;//0tao 1 mei 2 ju
    private String content;
    private String time;
    private User lostuser;
    private List<Integer> imageList;
    private List<Comment> commentList;


    public LostInfor(int lostCanteen, User lostuser, String time, String content) {
        this.lostCanteen = lostCanteen;
        this.lostuser = lostuser;
        this.time = time;
        this.content = content;
    }

    public LostInfor() {
        User user=new User();
        user.setUsrNick("是狐狸呀");
        user.setImg(R.drawable.find_four);
       this.lostuser=user;
        this.time="2017-8-9";
        this.content="我在食堂捡到了一个键盘好像!!!!!";
        this.lostCanteen=0;
        this.commentList=new ArrayList<>();
        commentList.add(new Comment());
        commentList.add(new Comment());
        this.imageList=new ArrayList<>();
        imageList.add(R.drawable.jianpan1);
        imageList.add(R.drawable.jianpan2);
    }

    public List<Integer> getImageList() {
        return imageList;
    }

    public void setImageList(List<Integer> imageList) {
        this.imageList = imageList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public int getLostid() {
        return lostid;
    }

    public void setLostid(int lostid) {
        this.lostid = lostid;
    }

    public int getLostCanteen() {
        return lostCanteen;
    }

    public void setLostCanteen(int lostCanteen) {
        this.lostCanteen = lostCanteen;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getLostuser() {
        return lostuser;
    }

    public void setLostuser(User lostuser) {
        this.lostuser = lostuser;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
