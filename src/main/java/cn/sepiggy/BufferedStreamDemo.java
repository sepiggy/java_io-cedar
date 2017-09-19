package cn.sepiggy;

import java.io.*;

/**
 * BufferedInputStream & BufferedOutputStream
 * 这两个包装流为IO提供了带缓冲区的操作, 一般打开文件进行写入或读取操作时,
 * 都会使用这个包装流, 提高IO性能.
 * <p>
 * 把数据从应用程序放入文件, 相当于将一缸水倒入到另一个缸中.
 * FileOutputStream.write()方法: 相当于一滴一滴的把水转移过去
 * DataOutputStream.writeXXX()方法: 会更加方便一些, 相当于一瓢一瓢的把水转移过去
 * BuffererOutputStream.write()方法: 最方便, 相当于一瓢一瓢的先放入桶中, 再从桶中倒入到另一个缸中
 */
public class BufferedStreamDemo {

    /**
     * 利用带缓冲的字节流进行文件拷贝
     *
     * @param srcFile  the src file
     * @param destFile the dest file
     * @throws IOException the io exception
     */
    public static void copyFileByBuffer(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("文件:" + srcFile + "不存在");
        }

        if (!srcFile.isFile()) {
            throw new IllegalArgumentException(srcFile + "不是文件");
        }

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(srcFile));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile))
        ) {
            byte[] buf = new byte[1024];
            int bytes;
            while ((bytes = in.read(buf, 0, buf.length)) != -1) {
                out.write(buf, 0, bytes);
            }
        }
    }
}
