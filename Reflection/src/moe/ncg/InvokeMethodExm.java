package moe.ncg;

import java.lang.reflect.Method;

public class InvokeMethodExm {
    public void staticAdd(Integer param1, Integer param2) {
        System.out.println(param1.intValue() + param2.intValue());
    }

    public void add(Integer param1, Integer param2) {
        System.out.println(param1.intValue() + param2.intValue());
    }

    public void stringAdd(String abc) {
        System.out.println("out" + abc);
    }

    public static void main(String[] args) throws Exception {
        // .claa 获取 Class 对象，再调用 GetMethod 获取方法
        // 确定一个方法不止需要方法名，还需要方法的参数及参数类型
        // 获取结果是一个 Method 对象
        Method addMth = InvokeMethodExm.class.getMethod("add", new Class[]{Integer.class, Integer.class});

        // invoke 第一个参数，表明从哪一个实例调用，方法的结果可能跟实例的上下文有关系的
        addMth.invoke(InvokeMethodExm.class.newInstance(), 1, 2);

        // 如果是静态类，那么第一个参数逻辑上实际被忽略
        Method staticMth = InvokeMethodExm.class.getMethod("staticAdd", new Class[]{Integer.class, Integer.class});
        staticMth.invoke(InvokeMethodExm.class.newInstance(), 1, 2);

        Method anotherMth = InvokeMethodExm.class.getMethod("stringAdd", String.class);
        anotherMth.invoke(InvokeMethodExm.class.newInstance(), " --test");
    }
}
