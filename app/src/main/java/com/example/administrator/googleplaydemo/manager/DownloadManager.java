package com.example.administrator.googleplaydemo.manager;

/**
 * Created by Administrator on 2017/3/31.
 */

import android.os.Environment;

import java.io.File;

/**
 * DownloadManger承担下载apk的所有功能 单例模式
 */
public class DownloadManager {

    //下载apk的存放路径，当应用被卸载时，该路径下的文件也会被删除
    private static final String DOWNLOAD_DIRECTORY = Environment.getExternalStorageDirectory()
            + "/Android/data/com.example.leon.googleplaydemo35/apk/";

    private static DownloadManager sDownloadManager;

    private DownloadManager() {}

    public static DownloadManager getInstance() {
        if (sDownloadManager == null) {
            synchronized (DownloadManager.class) {
                if (sDownloadManager == null) {
                    sDownloadManager = new DownloadManager();
                }
            }
        }
        return sDownloadManager;
    }

    /**
     * 创建apk下载下来存放的文件夹
     */
    public void createDownloadDir() {
        File file = new File(DOWNLOAD_DIRECTORY);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
