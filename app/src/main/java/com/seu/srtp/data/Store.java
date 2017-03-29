package com.seu.srtp.data;

/**
 * Created by Mind on 2017/3/23.
 */
public class Store {
    private String StoreName;
    private String StoreDescription;
    private String StorePosition;
    private int StorePhotoId;

    public Store(String storeName, String storeDescription, String storePosition,int storePhotoId) {
        StoreName = storeName;
        StoreDescription = storeDescription;
        StorePosition = storePosition;
        StorePhotoId=storePhotoId;
    }
    public Store(){}

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public void setStoreDescription(String storeDescription) {
        StoreDescription = storeDescription;
    }

    public void setStorePosition(String storePosition) {
        StorePosition = storePosition;
    }

    public String getStoreName() {
        return StoreName;
    }

    public String getStoreDescription() {
        return StoreDescription;
    }

    public String getStorePosition() {
        return StorePosition;
    }

    public int getStorePhotoId() {
        return StorePhotoId;
    }

    public void setStorePhotoId(int storePhotoId) {
        StorePhotoId = storePhotoId;
    }
}
