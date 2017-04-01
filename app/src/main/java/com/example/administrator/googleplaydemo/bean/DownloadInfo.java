package com.example.administrator.googleplaydemo.bean;

/**
 * Created by Administrator on 2017/4/1.
 */

/**
 * 保存一个apk下载过程中的所有数据,公共的数据模块
 */
public class DownloadInfo {

    private int status;//下载状态
    private long progress;//下载进度
    private String packageName;//下载apk的包名
    private int size;//下载apk的大小
    private String filePath;//apk的文件路径
    private String downloadUrl;//apk的下载链接

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
