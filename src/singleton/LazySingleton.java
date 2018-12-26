package singleton;

/**
 * 懒汉式--单例模式
 * 特点：
 *      线程不安全，不能保证实例对象的一致性。
 *      （如：第一个线程在实例化时，if (instance == null)判空，然后实例化。
 *          第一个还没结束，第二个就进来，instance依然是null，继续判空，再实例化。）
 *      可以延迟加载
 *
 * */
public class LazySingleton {
    private static LazySingleton instance = null;
    private LazySingleton(){
    }

    public static LazySingleton getInstance(){
        if (instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(LazySingleton.getInstance());
            }).start();
        }
    }
}
