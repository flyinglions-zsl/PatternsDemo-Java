package singleton;


/**
 * 得到实例化类加锁(类)的懒汉式模式--单例模式
 * 特点：
 *      线程安全
 *      也可以实现懒加载
 *      性能：synchronized 性能不行
 *          例： 当两个线程同时执行时可能出现， 1.都拿到了if (instance == null)，都可以进去(instance初始值为null)
 *                                          2.第一个线程：加锁，然后实例化，释放锁
 *                                          3.当第一个解锁，第二个拿到锁时，加锁，第二个的又会实例化对象，即实例化两次(问题所在)，释放锁。
 *           问题所在:会有可能使得实例化多次，引出DCL(双重锁--Double-Check-Locking)
 * */
public class LazySynChronizedClassSingleton {

    private static LazySynChronizedClassSingleton instance = null;

    private LazySynChronizedClassSingleton(){
    }

    public static LazySynChronizedClassSingleton getInstance(){
        if (instance == null){
            synchronized (LazySynChronizedClassSingleton.class) {
                instance = new LazySynChronizedClassSingleton();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(LazySynChronizedClassSingleton.getInstance());
            }).start();
        }
    }
}
