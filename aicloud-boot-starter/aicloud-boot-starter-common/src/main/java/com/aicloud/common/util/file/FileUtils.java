/*
 * 修订记录:
 * @author 钟勋 2017-11-16 19:53 创建
 */
package com.aicloud.common.util.file;

import java.io.File;
import java.io.IOException;

/**
 * 文件工具类
 */
public class FileUtils {

    /**
     * 如果文件不存在，则创建该文件
     *
     * @param filePath 文件路径
     */
    public static void createFileIfAbsent(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            File dir = file.getParentFile();
            if (dir != null) {
                createDirIfAbsent(dir.getPath());
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IllegalStateException(String.format("创建文件[%s]出错：%s", filePath, e.getMessage()), e);
            }
            if (!file.exists()) {
                throw new IllegalStateException(String.format("创建文件[%s]失败，可能对该目录无写权限", dir.getPath()));
            }
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException(String.format("已存在目录[%s]，无法创建相同名称的文件", filePath));
        }
    }

    /**
     * 如果目录不存在，则创建该目录
     *
     * @param dirPath 目录路径
     */
    public static void createDirIfAbsent(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
            if (!dir.exists()) {
                throw new IllegalStateException(String.format("创建目录[%s]失败，可能对该目录无写权限", dirPath));
            }
        }
        if (dir.isFile()) {
            throw new IllegalArgumentException(String.format("已存在文件[%s]，无法创建相同名称的目录", dirPath));
        }
    }
}
