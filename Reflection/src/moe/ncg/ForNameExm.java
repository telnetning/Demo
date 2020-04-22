package moe.ncg;

class Airplan {
    @Override
    public String toString() {
        return "In Airplan";
    }
}

public class ForNameExm {
    public static void main(String[] args) throws Exception {
        // 全量限定，forName 有多个实现，这里直接调用，使用了默认的类加载器
        Class cls = Class.forName("moe.ncg.Airplan");
        System.out.println(cls);
        Object obj = cls.newInstance();
        System.out.println(obj.toString());

        // 对比传统的创建方式
        // Airplan airplan = new Airplan();
    }
}
