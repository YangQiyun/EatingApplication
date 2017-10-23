package carteen.edu.seu.com.carteen.Model;

import java.io.Serializable;

/**
 * Created by Mind on 2017/10/2.
 */
public class Food implements Serializable{
    int foodId;
    String foodName;
    int foodCantId;
    int foodWinId;
    double foodPrice;
    int foodCmtNum;//评论数
    int foodGrade;
    int foodIsSpecial;
    int foodImg;
    String fooddescription;

    public Food(String foodName, int foodWinId, double foodPrice, int foodCmtNum, int foodGrade, int foodIsSpecial, int foodImg,int foodId) {
        this.foodId=foodId;
        this.foodName = foodName;
        this.foodWinId = foodWinId;
        this.foodPrice = foodPrice;
        this.foodCmtNum = foodCmtNum;
        this.foodGrade = foodGrade;
        this.foodIsSpecial = foodIsSpecial;
        this.foodImg = foodImg;
    }

    public String getFooddescription() {
        return fooddescription;
    }

    public void setFooddescription(String fooddescription) {
        this.fooddescription = fooddescription;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodCantId() {
        return foodCantId;
    }

    public void setFoodCantId(int foodCantId) {
        this.foodCantId = foodCantId;
    }

    public int getFoodWinId() {
        return foodWinId;
    }

    public void setFoodWinId(int foodWinId) {
        this.foodWinId = foodWinId;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getFoodCmtNum() {
        return foodCmtNum;
    }

    public void setFoodCmtNum(int foodCmtNum) {
        this.foodCmtNum = foodCmtNum;
    }

    public int getFoodGrade() {
        return foodGrade;
    }

    public void setFoodGrade(int foodGrade) {
        this.foodGrade = foodGrade;
    }

    public int getFoodIsSpecial() {
        return foodIsSpecial;
    }

    public void setFoodIsSpecial(int foodIsSpecial) {
        this.foodIsSpecial = foodIsSpecial;
    }

    public int getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(int foodImg) {
        this.foodImg = foodImg;
    }
}
