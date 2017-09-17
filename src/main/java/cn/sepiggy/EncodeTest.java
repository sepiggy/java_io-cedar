package cn.sepiggy;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class EncodeTest {

    String s = "慕课ABC";

    /**
     * 转换成字节序列, 用的是项目默认的编码 UTF-8
     * "慕课ABC" 对应的 UTF-8 编码是 "e6 85 95 e8 af be 41 42 43"
     * 可以总结出:
     * UTF-8编码, 中文占用3个字节, 英文占用1个字节
     */
    @Test
    public void test1() {
        byte[] bytes = s.getBytes();
        printInHex(bytes);
    }

    /**
     * 转换成字节序列, 用的是指定编码 GBK
     * <p>
     * "慕课ABC" 对应的 GBK 编码是 "c4 bd bf ce 41 42 43"
     * 可以总结出:
     * GBK编码, 中文占用2个字节, 英文占用1个字节
     *
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    @Test
    public void test2() throws UnsupportedEncodingException {
        byte[] bytes = s.getBytes("GBK");
        printInHex(bytes);

    }

    /**
     * Java 是双字节编码, 即使用 UTF-16BE
     * "慕课ABC" 对应的 UTF-16BE 编码是 "61 55 8b fe 0 41 0 42 0 43"
     * 可以总结出:
     * UTF-16BE编码, 中文占用2个字节, 英文占用2个字节
     *
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    @Test
    public void test3() throws UnsupportedEncodingException {
        byte[] bytes = s.getBytes("UTF-16BE");
        printInHex(bytes);
    }

    /**
     * 当字节序列是某种编码时, 若将之转化为字符串, 也需要使用这种编码方式, 否则会出现乱码
     * 文本文件就是字节序列
     */
    @Test
    public void test4() throws UnsupportedEncodingException {
        byte[] bytes = "你好, 世界, Hello, World".getBytes("UTF-16BE");
        String s1 = new String(bytes);
        // 乱码
        System.out.println(s1);
        String s2 = new String(bytes, "UTF-16BE");
        // 正常
        System.out.println(s2);
    }

    private void printInHex(byte[] bytes) {
        for (byte b : bytes) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
        System.out.println();
    }

}
