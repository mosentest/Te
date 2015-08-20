package org.moziqi.util;

import android.os.Environment;

/**
 * 文件与流处理工具类
 * Created by moziqi on 15-7-23.
 */
public class FileUtils {
    /**
     * 检测SD卡是否存在
     *
     * @return
     */
    public static boolean checkSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
