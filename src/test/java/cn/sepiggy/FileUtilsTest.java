package cn.sepiggy;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileUtilsTest {

    @Test
    public void test1() throws IOException {
        FileUtils.listDirectory(new File("."));
    }

}