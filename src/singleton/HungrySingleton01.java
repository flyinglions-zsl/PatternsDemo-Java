package singleton;

/**
 * 饿汉式--单例模式
 * 特点：
 *      线程安全，类加载时就实例化(只实例了一次)
 *      性能较好
 *      没有延迟加载，长时间不使用，会占用内存（因为是static变量，一直存在于内存中）
 * */
public class HungrySingleton01 {
    private static HungrySingleton01 instance = new HungrySingleton01();

    public HungrySingleton01(){

    }
    public static HungrySingleton01 getInstance(){
        return instance;
    }

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println(HungrySingleton01.getInstance());
            }).start();
        }
    }

}
