package cn.sepiggy;

import org.junit.Test;

import java.io.*;

/**
 * 字符流
 * 1 编码问题
 * 2 认识文本和文本文件
 * 2-1 java的文本(char)是16位无符号整数, 是字符的unicode编码(双字节编码)
 * <p>
 * 2-2 文件是byte byte byte ...的数据序列
 * <p>
 * 2-3 文本文件是文本(char)序列按照某种编码方案(UTF-8, UTF-16BE, GBK)序列化为byte的存储结果
 * <p>
 * 3 字符流(Reader Writer) ---> 操作的是文本文件
 * 3-1 字符的处理, 一次处理一个字符
 * 3-2 字符的底层依然是基本的字节序列
 * 3-3 字符流的基本实现
 * InputStreamReader 完成byte流解析为char流, 按照编码解析
 * OutputStreamWriter 提供char流到byte流, 按照编码处理
 */
public class ISRAndOSWTest {

    /**
     * InputStreamReader
     *
     * @throws IOException the io exception
     */
    @Test
    public void test1() throws IOException {
        FileInputStream in = new FileInputStream("demo/file.txt");
        InputStreamReader isr = new InputStreamReader(in, "GBK");
        // int c;
        // while ((c = isr.read()) != -1) {
        //     System.out.print((char)c);
        // }

        char[] buffer = new char[1024];
        int chars;
        // 批量读取, 放入buffer这个字符数组, 从第0个位置开始放, 最多放buffer.length个
        // 返回的是实际读到的字符的个数
        while ((chars = isr.read(buffer, 0, buffer.length)) != -1) {
            // 构造成一个字符串, 这里不用指定编码, 因为编码在构造 InputStreamReader 时已经指定过了
            String s = new String(buffer, 0, chars);
            System.out.print(s);
        }
    }


    /**
     * OutputStreamWriter
     *
     * @throws IOException the io exception
     */
    @Test
    public void test2() throws IOException {
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("demo/osw.txt"), "UTF-8")) {
            osw.write("中国");
            osw.flush();
        }
    }
}
