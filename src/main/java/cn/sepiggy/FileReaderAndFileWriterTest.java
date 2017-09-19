package cn.sepiggy;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileReader / FileWriter:
 * 直接对文件进行字符流的读写, 不必使用 InputStreamReader 和 FileInputStream 进行构造了
 */
public class FileReaderAndFileWriterTest {

    @Test
    public void test1() throws IOException {
        try (FileReader fr = new FileReader("pom.xml");
             FileWriter fw = new FileWriter("demo/pom-copy.xml", true)) {
            char[] buffer = new char[1024];
            int chars;

            while ((chars = fr.read(buffer, 0, buffer.length)) != -1) {
                fw.write(buffer, 0, chars);
                fw.flush();
            }
        }
    }

}
