package cn.sepiggy;

import java.io.*;

/**
 * 1 InputStream 抽象了应用程序读取数据的方式
 * OutputStream 抽象了应用程序写出数据的方式
 * <p>
 * 2 EOF = END  读到-1就读到了结尾
 * <p>
 * 3 输入流基本方法
 * int b = in.read(): 读取一个字节无符号填充到int的低八位; -1是EOF.
 * in.read(byte[] buf): 读取数据填充到字节数组buf
 * in.read(byte[] buf, int start, int size): 读取数据到字节数组buf, 从buf的start位置开始存放size长度的数据
 * <p>
 * 4 输出流基本方法
 * out.write(int b): 写出一个byte到流, 这里是b的低8位
 * out.write(byte[] buf): 将buf字节数组都写入到流
 * out.write(byte[] buf, int start, int size): 字节数组buf从start位置开始写size长度的字节到流
 * <p>
 * 5 FileInputStream ---> 具体实现了在文件上读取数据, 把文件作为字节流进行读操作
 * <p>
 * 6 FileOutputStream ---> 具体实现了向文件中写出byte数据的方法, 把文件作为字节流进行写操作
 */
public class IOUtil {

    /**
     * 读取指定文件内容, 按照16进制输出到控制台
     * 并且每输出10个byte换行
     * 使用 int b = in.read() API 实现
     *
     * @param fileName the file name
     */
    public static void printHex(String fileName) throws IOException {
        // 把文件作为字节流进行读操作
        FileInputStream in = new FileInputStream(fileName);
        int b;
        int i = 1;
        while ((b = in.read()) != -1) {
            if (b <= 0xf) {
                // 单位数前面补0
                System.out.print("0");
            }
            // 将整型b转换为16进制表示的字符串
            System.out.print(Integer.toHexString(b) + " ");
            if (i++ % 10 == 0) {
                System.out.println();
            }
        }
        in.close();
    }

    /**
     * 读取指定文件内容, 按照16进制输出到控制台
     * 并且每输出10个byte换行
     *
     * @param fileName the file name
     */
    public static void printHexByByteArray(String fileName) throws IOException {
        FileInputStream in = new FileInputStream(fileName);
        byte[] buf = new byte[20 * 1024];
        // 从in中批量读取字节, 放入到buf这个字节数组中, 从第0个位置开始放, 最多放buf.length个.
        // 返回值read是实际读到的字节的个数.(字节数组不够放, 字节数组放不满)
        /*
        int bytes = in.read(buf, 0, buf.length); // 一次性读完, 说明字节数组足够大
        int j = 1;
        for (int i = 0; i < bytes; i++) {
            if (buf[i] <= 0xf) {
                System.out.print("0");
            }
            System.out.print(Integer.toHexString(buf[i]) + " ");
            if (j++ % 10 == 0) {
                System.out.println();
            }
        }
        */
        int bytes = 0;
        int j = 1;
        while ((bytes = in.read(buf, 0, buf.length)) != -1) {
            for (int i = 0; i < bytes; i++) {
                if (buf[i] <= 0xf) {
                    System.out.print("0");
                }
                System.out.print(Integer.toHexString(buf[i] & 0xff) + " ");
                if (j++ % 10 == 0) {
                    System.out.println();
                }
            }
        }
    }

    /**
     * 拷贝文件, 从srcFile到destFile
     *
     * @throws IOException the io exception
     */
    public static void copyFile(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("文件:" + srcFile + "不存在");
        }
        if (!srcFile.isFile()) {
            throw new IllegalArgumentException(srcFile + "不是文件");
        }
        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(destFile);

        System.out.println("Copy start...");

        byte[] buf = new byte[1024];
        int bytes;

        while ((bytes = in.read(buf, 0, buf.length)) != -1) {
            out.write(buf, 0, bytes);
            out.flush();
        }
        System.out.println("Copy completed...");
        in.close();
        out.close();
    }
}
