package com.example.administrator.googleplaydemo.bean;

/**
 * Created by Administrator on 2017/3/29.
 */

public class AppListItemBean {

    /**
     * des : 【激情世界杯，天天好音质】世界杯期间在天天动听#用户评价#里5星好评并带上个人
     * downloadUrl : app/com.sds.android.ttpod/com.sds.android.ttpod.apk
     * iconUrl : app/com.sds.android.ttpod/icon.jpg
     * id : 1644521
     * name : 天天动听
     * packageName : com.sds.android.ttpod
     * size : 9514161
     * stars : 3.5
     */

    private String des;
    private String downloadUrl;
    private String iconUrl;
    private int id;
    private String name;
    private String packageName;
    private int size;
    private float stars;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
}
