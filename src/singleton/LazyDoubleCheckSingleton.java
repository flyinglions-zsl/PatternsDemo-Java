package singleton;


import java.net.Socket;
import java.sql.Connection;

/**
 * DCL(双重锁--Double-Check-Locking)------作两次判断
 * 双重锁的懒汉式模式--单例模式
 * 特点：
 *      线程安全
 *      也可以实现懒加载
 *      性能： 性能比较好
 *            例： 当两个线程同时执行时可能出现， 1.都拿到了if (instance == null)，都可以进去(instance初始值为null)
 *  *                                          2.第一个线程：加锁，判断，然后实例化，释放锁
 *  *                                          3.当第一个解锁，第二个拿到锁时，加锁，判断(第一个线程的对象返回了，则不会为空)不会实例化对象，释放锁。
 *       即保证了对象的唯一性
 *
 *       情形：当要调用成员变量时,因为指令重排空指针异常
 *
 *       问题：如：
 *          实例化时(自定义顺序)：
     *          //conn;
     *         //socket;
     *         //instance = new LazyDoubleCheckSingleton();
 *          指令重排可能：
 *              // instance = new LazyDoubleCheckSingleton();
 *              //conn;
 *              //socket;
 *
 *              当重排后：有两个线程一起执行，如上例子，第二个线程不会再重新实例化对象，
 *              第一个线程没执行完，但第二个线程拿到了第一次实例的（也是唯一一次）对象。
 *              此时第二个线程的对象中的变量不会有值(因为指令重排了，变量操作再实例对象之后操作，没有值)，
 *              此时，当前线程就会引发空指针问题。
 *
 *        解决： 加volatile关键字。
 *         (1)volatile之前的代码不能调整到他的后面
 *        （2）volatile之后的代码不能调整到他的前面（as if seria）
 *        （3）霸道（位置不变化）
 * */
public class LazyDoubleCheckSingleton {

    //private static LazyDoubleCheckSingleton instance = null;
    private volatile static LazyDoubleCheckSingleton instance = null;

    Connection conn;
    Socket socket;

    private LazyDoubleCheckSingleton(){
        //conn;
        //socket;
        //编译时 instance = new LazyDoubleCheckSingleton();
    }

    public static LazyDoubleCheckSingleton getInstance(){
        if (instance == null){
            synchronized (LazyDoubleCheckSingleton.class){
                if (instance == null) {
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(LazyDoubleCheckSingleton.getInstance());
            }).start();
        }
    }
}
