package cn.sepiggy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 8080292600957788369L;

    private String stuNo;
    private String stuName;
    // 该元素不会进行 JVM 默认的序列化工作, 也可以自己完成这个元素的序列化
    private transient int stuAge;

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject(); // 把JVM能默认序列化的元素进行序列化操作
        s.writeInt(stuAge); // 自己完成 stuAge 的序列化
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject(); // 把JVM能默认反序列化的元素进行反序列化操作
        this.stuAge = s.readInt();
    }

    public Student() {
    }

    public Student(String stuNo, String stuName, int stuAge) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuAge = stuAge;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                '}';
    }
}
