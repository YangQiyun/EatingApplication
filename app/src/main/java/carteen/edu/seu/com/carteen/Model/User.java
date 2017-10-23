package carteen.edu.seu.com.carteen.Model;

import java.io.Serializable;

import carteen.edu.seu.com.carteen.R;

/**
 * Created by Mind on 2017/10/11.
 */
public class User implements Serializable{
    private int userId;
    private int userTel;
    private String usrPsw;
    private String usrNick;
    private int img;

    public User(String usrNick, int img) {
        this.usrNick = usrNick;
        this.img = img;
    }



    public User(){
        this.usrNick="取个名字好难";
        this.img= R.drawable.head1;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserTel() {
        return userTel;
    }

    public void setUserTel(int userTel) {
        this.userTel = userTel;
    }

    public String getUsrPsw() {
        return usrPsw;
    }

    public void setUsrPsw(String usrPsw) {
        this.usrPsw = usrPsw;
    }

    public String getUsrNick() {
        return usrNick;
    }

    public void setUsrNick(String usrNick) {
        this.usrNick = usrNick;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
