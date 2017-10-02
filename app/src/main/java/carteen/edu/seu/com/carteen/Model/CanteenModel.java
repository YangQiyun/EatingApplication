package carteen.edu.seu.com.carteen.Model;

import java.io.Serializable;

/**
 * Created by Mind on 2017/10/2.
 */
public class CanteenModel implements Serializable{
    int cantId;
    int cantType;//0桃园1梅园2橘园
    String cantName;
    String cantManagerName;
    int cantManagerTel;

    public int getCantId() {
        return cantId;
    }

    public void setCantId(int cantId) {
        this.cantId = cantId;
    }

    public int getCantType() {
        return cantType;
    }

    public void setCantType(int cantType) {
        this.cantType = cantType;
    }

    public String getCantName() {
        return cantName;
    }

    public void setCantName(String cantName) {
        this.cantName = cantName;
    }

    public String getCantManagerName() {
        return cantManagerName;
    }

    public void setCantManagerName(String cantManagerName) {
        this.cantManagerName = cantManagerName;
    }

    public int getCantManagerTel() {
        return cantManagerTel;
    }

    public void setCantManagerTel(int cantManagerTel) {
        this.cantManagerTel = cantManagerTel;
    }
}
