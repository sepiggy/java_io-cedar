package cn.sepiggy;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class IOUtilTest {

    @Test
    public void test1() throws IOException {
        IOUtil.printHex("pom.xml");
    }

    @Test
    public void test2() throws IOException {
        IOUtil.printHexByByteArray("pom.xml");
    }

    @Test
    public void test3() throws IOException {
        File srcFile = new File("pom.xml");
        File destFile = new File("pom_copy.xml");
        IOUtil.copyFile(srcFile, destFile);
    }
}