package cn.sepiggy;

import org.junit.Test;

import java.io.*;

/**
 * 对象的序列化, 反序列化
 * 1) 对象的序列化, 就是将 Object 转换成 byte 序列, 反之叫对象的反序列化
 * <p>
 * 2) 序列化流 (ObjectOutputStream), 是过滤流, 核心方法 writeObject()
 * 反序列化 (ObjectInputStream), 是过滤流, 核心方法 readObject()
 * <p>
 * 3) 对象必须实现序列化接口 (Serializable) , 才能进行序列化, 否则会报出异常
 * 这个接口里没有任何方法, 只是一个标准
 */
public class ObjectOutputStreamTest {

    /**
     * 序列化一个 Student 对象, 并保存到 student.dat 中
     *
     * @throws IOException the io exception
     */
    @Test
    public void test1() throws IOException {
        String file = "demo/student.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            Student stu = new Student("10001", "张三", 20);
            oos.writeObject(stu);
        }
    }

    /**
     * 从 student.dat 中反序列化
     *
     * @throws IOException the io exception
     */
    @Test
    public void test2() throws IOException, ClassNotFoundException {
        String file = "demo/student.dat";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Student stu = (Student) ois.readObject();
            System.out.println(stu);
        }
    }

    /**
     * 序列化中子类和父类的构造函数调用问题
     * <p>
     * 一个类实现了序列化接口, 其子类都可以进行序列化
     *
     * @throws IOException the io exception
     */
    @Test
    public void test3() throws IOException, ClassNotFoundException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("demo/obj1.dat"))) {
            Foo2 foo2 = new Foo2();
            oos.writeObject(foo2);
            oos.flush();
        }

        // 反序列化是否递归调用父类的构造函数
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("demo/obj1.dat"))) {
            Foo2 foo2 = (Foo2) ois.readObject();
            System.out.println(foo2);
        }
    }

    class Foo implements Serializable {
        public Foo() {
            System.out.println("foo...");
        }
    }

    class Foo1 extends Foo {
        public Foo1() {
            System.out.println("foo1...");
        }
    }

    class Foo2 extends Foo1 {
        public Foo2() {
            System.out.println("foo2...");
        }
    }
}
