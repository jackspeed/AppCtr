package com.ycj.ycjlibrary.utils;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;

/**
 * Created by adming on 2017/5/19.
 */

public class YFileUtils {
    /**
     * 通知媒体库更新文件夹
     *
     * @param context
     * @param filePath 文件绝对路径，、/sda/aaa/jjj.jpg
     */
    public void scanFile(Context context, String filePath) {
        try {
            MediaScannerConnection.scanFile(context,
                    new String[]{filePath}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("*******", "Scanned " + path + ":");
                            Log.i("*******", "-> uri=" + uri);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
