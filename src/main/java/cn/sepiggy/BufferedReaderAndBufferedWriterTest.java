package cn.sepiggy;

import org.junit.Test;

import java.io.*;

/**
 * BufferedReader ---> readLine 一次读一行, 但不识别换行
 * BufferedWriter ---> write(String) newLine() 一次写一行, 需要单独写出换行操作
 * PrintWriter ---> println(String) 一次写一行, 包括换行
 * PrintWriter 比 BufferedWriter 在构造和输出上都更方便
 */
public class BufferedReaderAndBufferedWriterTest {

    @Test
    public void test1() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("pom.xml")));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("demo/pom-copy-1.xml")));
             PrintWriter pw = new PrintWriter("demo/pom-copy-2.xml")) {
            String line;
            while ((line = br.readLine()) != null) {
                // 输出到控制台
                System.out.println(line);

                // 利用 BufferedWriter 输出到 pom-copy-1.xml
                bw.write(line);
                // 单独写出换行操作
                bw.newLine();
                bw.flush();

                // 利用 PrintWriter 输出到 pom-copy-2.xml
                pw.println(line);
                pw.flush();
            }
        }
    }
}
