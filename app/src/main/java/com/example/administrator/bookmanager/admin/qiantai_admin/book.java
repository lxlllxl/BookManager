package com.example.administrator.bookmanager.admin.qiantai_admin;

/**
 * Created by Administrator on 2017/8/20.
 */

public class book {
    private String name;
    private int imageId;

    public book(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;

    }

    public String getName() {
        return name;
    }


    public int getImageId() {
        return imageId;
    }


}
