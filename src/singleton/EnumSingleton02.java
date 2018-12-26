package singleton;

public class EnumSingleton02 {
    private EnumSingleton02(){}

    private enum EnumHolder{
        INSTANCE;
        private static EnumSingleton02 instance = new EnumSingleton02();

        private EnumSingleton02 getInstance(){
            return INSTANCE.instance;
        }
    }

    public static EnumSingleton02 getInstance(){
        return EnumHolder.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        System.out.println(EnumSingleton02.getInstance());
        System.out.println(EnumSingleton02.getInstance());
        System.out.println(EnumSingleton02.getInstance());
        System.out.println(EnumSingleton02.getInstance());
        System.out.println(EnumSingleton02.getInstance());
    }
}
