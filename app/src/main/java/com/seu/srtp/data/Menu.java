package com.seu.srtp.data;

/**
 * Created by Mind on 2017/3/23.
 */
public class Menu {
    private String MenuName;
    private String MenuPrice;
    private int MenuImage;
    private String MenuDescription;

    public Menu(String menuName, String menuPrice, int menuImage, String menuDescription) {
        MenuName = menuName;
        MenuPrice = menuPrice;
        MenuImage = menuImage;
        MenuDescription = menuDescription;
    }

    public Menu(){}

    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String menuName) {
        MenuName = menuName;
    }

    public String getMenuPrice() {
        return MenuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        MenuPrice = menuPrice;
    }

    public int getMenuImage() {
        return MenuImage;
    }

    public void setMenuImage(int menuImage) {
        MenuImage = menuImage;
    }

    public String getMenuDescription() {
        return MenuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        MenuDescription = menuDescription;
    }
}
