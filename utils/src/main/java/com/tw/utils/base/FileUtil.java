package com.tw.utils.base;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

/**
 * @Description 处理文件的公用类
 * @createDate
 */
public final class FileUtil {
    public static final int TYPE_AVAIABLE = 0;
    public static final int TYPE_TOTAL = 1;

    /**
     * check sd card remount result and return the root directory
     *
     * @return null if sdcard not mounted
     */
    public static File getExternalStorageDirectory() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {// SD卡正常挂载
            return Environment.getExternalStorageDirectory();
        }
        return null;
    }


    /**
     * 获取sdcard的大小(long 类型)
     *
     * @param context
     * @param type
     * @return -1 if sdcard not mounted
     */
    public static long getSdCardSizeLong(Context context, int type) {
        if (ObjectUtil.isNull(getExternalStorageDirectory())) {
            return -1;
        }
        StatFs statFs = new StatFs(getExternalStorageDirectory().getAbsolutePath());
        long blockSize = 0l;
        long avaiableBlocks = 0l;
        long totalBlocks = 0l;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = statFs.getBlockSizeLong();
            avaiableBlocks = statFs.getAvailableBlocksLong();
            totalBlocks = statFs.getBlockCountLong();
        } else {
            blockSize = statFs.getBlockSize();
            avaiableBlocks = statFs.getAvailableBlocks();
            totalBlocks = statFs.getBlockCount();
        }
        long totalSize = blockSize * totalBlocks;
        long avaiableSize = blockSize * avaiableBlocks;
        if (type == TYPE_TOTAL) {
            return totalSize;
        } else if (type == TYPE_AVAIABLE) {
            return avaiableSize;
        } else {
            throw new IllegalArgumentException("Illegal type = " + type);
        }
    }


    /**
     * 格式化文件大小
     *
     * @param context
     * @param size
     * @return 格式化后的大小
     */
    public static String formatFileSize(Context context, long size) {
        return Formatter.formatFileSize(context, size);
    }
}
