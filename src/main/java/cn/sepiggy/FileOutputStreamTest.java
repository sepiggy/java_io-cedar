package cn.sepiggy;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest {

    @Test
    public void test() throws IOException {
        // 如果该文件不存在, 则直接创建; 如果存在, 则删除后创建
        FileOutputStream out = new FileOutputStream("demo/out.dat");
        out.write('A');  // 写出'A'的低八位
        out.write('B');  // 写出'B'的低八位
        int a = 10; // write方法只能写八位, 那么写一个int需要写4次, 每次8位
        out.write(a >>> 24);
        out.write(a >>> 16);
        out.write(a >>> 8);
        out.write(a);
        byte[] bytes = "中国".getBytes("UTF-8");
        out.write(bytes);
        out.close();

        IOUtil.printHexByByteArray("demo/out.dat");
    }

}
