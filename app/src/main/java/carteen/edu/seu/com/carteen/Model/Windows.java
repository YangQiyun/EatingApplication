package carteen.edu.seu.com.carteen.Model;

import java.io.Serializable;

/**
 * Created by Mind on 2017/10/2.
 */
public class Windows implements Serializable{
    int winId;
    String winName;
    String winCantType;
    String winDes;
    int winImgUrl;

    public Windows(int winId,String winName,String winDes, String winCantType,int winImgUrl) {
        this.winId=winId;
        this.winName = winName;
        this.winCantType = winCantType;
        this.winImgUrl = winImgUrl;
        this.winDes = winDes;
    }

    public int getWinId() {
        return winId;
    }

    public void setWinId(int winId) {
        this.winId = winId;
    }

    public String getWinName() {
        return winName;
    }

    public void setWinName(String winName) {
        this.winName = winName;
    }

    public String getWinCantType() {
        return winCantType;
    }

    public void setWinCantType(String winCantType) {
        this.winCantType = winCantType;
    }

    public String getWinDes() {
        return winDes;
    }

    public void setWinDes(String winDes) {
        this.winDes = winDes;
    }

    public int getWinImgUrl() {
        return winImgUrl;
    }

    public void setWinImgUrl(int winImgUrl) {
        this.winImgUrl = winImgUrl;
    }
}
