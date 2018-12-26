package singleton;


/**
 *
 * 声明类的时候，成员变量中不声明实例变量，而放到内部静态类中，
 * */
public class HolderSingleton {
    private  HolderSingleton(){
    }

    private static class Holder{
        private static HolderSingleton instance = new HolderSingleton();
    }

    public static HolderSingleton getInstance(){
        return Holder.instance;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(HolderSingleton.getInstance());
        }
    }
}
