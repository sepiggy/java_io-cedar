package cn.sepiggy;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * java.io.File 类只用于表示文件(目录)的信息(名称, 大小等), 不能用于文件内容的访问
 */
public class FileTest {

    @Test
    public void test1() {
        File file = new File("test");
        System.out.println(file.exists());
        if (!file.exists()) {
            file.mkdirs();
        } else {
            file.delete();
        }
    }

    /**
     * 判断File是目录还是文件
     * File.isDirectory():
     * 判断是否是一个目录, 如果是目录返回 true; 如果不是目录或者目录不存在返回 false
     * File.isFile():
     * 判断是否是一个文件
     */
    @Test
    public void test2() {
        File file = new File("pom.xml");
        boolean isDirectory = file.isDirectory();
        boolean isFile = file.isFile();
        System.out.println("isDirectory = " + isDirectory);
        System.out.println("isFile = " + isFile);
    }

    @Test
    public void test3() {
        File file = new File("java_io/readme.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.delete();
        }
    }

    /**
     * File类常用API
     *
     * @throws IOException the io exception
     */
    @Test
    public void test4() throws IOException {
        File file = new File("java_io/readme.txt");
        System.out.println(file);
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.getParentFile().getAbsolutePath());
    }
}
