package cn.sepiggy;

import org.junit.Test;

import java.io.*;

/**
 * DataOutputStream / DataInputStream
 * 对 "流" 功能的扩展, 可以更加方便的读取 int, long, 字符等类型数据
 * <p>
 * DataOutputStream 常用 API:
 * writeInt()/writeDouble()/writeUTF()
 * DataInputStream 也有相应的读取操作的 API
 */
public class DataStreamTest {

    /**
     * DataOutputStream
     *
     * @throws IOException the io exception
     */
    @Test
    public void test1() throws IOException {
        String file = "demo/dos.dat";
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
        dos.writeInt(10);
        dos.writeInt(-10);
        dos.writeLong(10L);
        dos.writeDouble(10.5);
        // 采用 UTF-8 编码写出
        dos.writeUTF("中国");
        // 采用 UTF-16BE 编码写出
        dos.writeChars("中国");
        dos.close();
        IOUtil.printHex(file);
    }

    /**
     * DataInputStream
     *
     * @throws IOException the io exception
     */
    @Test
    public void test2() throws IOException {
        String file = "demo/dos.dat";
        IOUtil.printHex(file);
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        int i = dis.readInt();
        System.out.println(i);
        i = dis.readInt();
        System.out.println(i);
        long l = dis.readLong();
        System.out.println(l);
        double d = dis.readDouble();
        System.out.println(d);
        String s = dis.readUTF();
        System.out.println(s);
        dis.close();
    }
}
