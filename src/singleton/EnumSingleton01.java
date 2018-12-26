package singleton;

public enum  EnumSingleton01 {
    //常量，在加载的时候实例化一次，方法区中
    A,B,C,D; //类型：代表的就是当前这个enum对象

    int i=10; //基本数据类型
    String j="xxx";

    public static void print(){
        System.out.println("Enum-----method");
    }

    public static void main(String[] args) {
        A.print();
        B.print();
        C.print();
        D.print();
    }
}
