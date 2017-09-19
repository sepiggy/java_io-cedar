package cn.sepiggy;

import java.io.File;
import java.io.IOException;

/**
 * 列出File的一些常用操作:
 * 比如过滤, 遍历等操作
 */
public class FileUtils {

    /**
     * 列出指定目录下(包括其子目录)的所有文件
     * <p>
     * File.list() 方法用于列出当前目录下的子目录和文件, 返回值是 String[]
     * File.listFiles() 方法用于列出当前目录下的子目录和文件, 返回值是 File[]
     *
     * @param dir the dir
     * @throws IOException the io exception
     */
    public static void listDirectory(File dir) throws IOException {
        if (!dir.exists()) {
            throw new IllegalArgumentException("目录:" + dir + "不存在.");
        }

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir + "不是目录.");
        }

        // String[] fileNames = dir.list();
        // for (String fileName : fileNames) {
        //     System.out.println(dir + "/" + fileName);
        // }

        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 递归
                    listDirectory(file);
                } else {
                    System.out.println(file);
                }
            }
        }
    }
}
