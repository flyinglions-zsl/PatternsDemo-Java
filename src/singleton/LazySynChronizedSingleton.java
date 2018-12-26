package singleton;


/**
 * 得到实例化对象加锁(方法)的懒汉式模式--单例模式
 * 特点：
 *      线程安全
 *      也可以实现懒加载
 *      性能：synchronized 此时线程退化到串行执行(等上一个方法解锁后，下一个才能执行),性能低
 * */
public class LazySynChronizedSingleton {
    private static LazySynChronizedSingleton instance = null;

    private LazySynChronizedSingleton(){
    }

    public synchronized static LazySynChronizedSingleton getInstance(){
        if (instance == null){
            instance = new LazySynChronizedSingleton();
        }
        return instance;
    }

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(LazySynChronizedSingleton.getInstance());
            }).start();
        }
    }
}
