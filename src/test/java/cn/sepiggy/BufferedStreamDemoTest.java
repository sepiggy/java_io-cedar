package cn.sepiggy;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class BufferedStreamDemoTest {

    @Test
    public void test() throws IOException {
        BufferedStreamDemo.copyFileByBuffer(new File("pom.xml"), new File("pom-copy-1.xml"));
    }

}