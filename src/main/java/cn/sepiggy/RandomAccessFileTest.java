package cn.sepiggy;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 1 RandomAccessFile:
 * 1-1 Java提供的对文件内容的访问, 既可以读文件, 也可以写文件.
 * 1-2 支持随机访问文件, 可以访问文件的任意位置.
 * <p>
 * 2 Java文件模型:
 * 在硬盘上的文件是 byte byte byte 存储的, 是数据的集合.
 * <p>
 * 3 打开文件的两种模式:
 * 3-1 "rw" 读写
 * 3-2 "r" 只读
 * RandomAccessFile raf = new RandomAccessFile(file, "rw")
 * <p>
 * 4 RandomAccessFile 通过文件指针进行文件的访问, 打开文件时, 指针在开头, pointer=0
 * <p>
 * 5 写方法
 * raf.write(int) ---> 只写一个字节(后8位), 同时指针指向下一个位置, 准备再次写入
 * <p>
 * 6 读方法
 * int b = raf.read() ---> 读一个字节
 * <p>
 * 7 文件读写完成以后一定要关闭
 */
public class RandomAccessFileTest {

    @Test
    public void testWrite() throws IOException {
        File demo = new File("demo");
        if (!demo.exists()) {
            demo.mkdir();
        }

        File file = new File(demo, "raf.dat");
        if (!file.exists()) {
            file.createNewFile();
        }

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        // 指针的位置
        System.out.println(raf.getFilePointer());

        // 只写了一个字节(后8位)
        raf.write('A');
        System.out.println(raf.getFilePointer());
        raf.write('B');
        System.out.println(raf.getFilePointer());

        int i = 0x7fffffff;
        // 用 write 方法每次只能写一个字节, 如果要把 i 写进去就得写 4 次
        raf.write(i >>> 24);
        raf.write(i >>> 16);
        raf.write(i >>> 8);
        raf.write(i);
        System.out.println(raf.getFilePointer());

        // 其实可以通过 writeInt 方法直接写一个 int
        raf.writeInt(i);
        System.out.println(raf.getFilePointer());

        String s = "中";
        byte[] bytes = s.getBytes("GBK");
        raf.write(bytes);
        System.out.println("raf.length() = " + raf.length());
        raf.close();
    }

    /**
     * 读文件时, 必须把指针移到头部
     *
     * @throws IOException the io exception
     */
    @Test
    public void testRead() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("pom.xml", "r");
        System.out.println("raf.getFilePointer() = " + raf.getFilePointer());

        byte[] buf = new byte[(int) raf.length()];
        // 一次性读取, 把文件中的内容都读到字节数组中
        raf.read(buf);
        System.out.println(Arrays.toString(buf));
        String s = new String(buf);
        System.out.println(s);
        raf.close();
    }
}
