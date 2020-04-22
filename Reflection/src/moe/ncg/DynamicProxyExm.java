package moe.ncg;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 需要使用的类：
 * 1) java.lang.reflect.Proxy
 *    主要的类， 提供了一组静态方法，为一组接口动态生成代理类及其对象
 *
 *    // 获取指定代理对象所关联的调用处理器
 *    static InvocationHandle getInvocationHJjandlere(Object proxy)
 *
 *    // 获取管理于指定类装载器和一组接口的动态代理类的类对象
 *    static Class getProxyClass(ClassLoader loader, Class[] interfaces)
 *
 *    // 判定指定类对象是否为一个动态代理类
 *    static boolean isProxyClass(Class cl)
 *
 *    // 用于为指定类加载器、一组接口及调用处理器生成动态代理类实例
 *    static Object newProxyInstance(ClassLoader loader, Class[] interfaces, InvocationHandleer h)
 *
 * 2) java.lang.refect.InvocationHandler
 *    调用处理器接口，定义了 invoke 方法，用于集中处理再动态代理类对象上的方法调用
 *
 *    // 调用一个代理对象的方法
 *    Object invoke(Object proxy, Method method, Object[] args)
 */

interface Subject02 {
    public void request();
}

class RealSubject02 implements Subject02 {
    public RealSubject02() {}
    public void request() {
        System.out.println("From real subject");
    }
}

class DynamicSubject implements InvocationHandler {
    private Object sub;
    public DynamicSubject() {}
    public DynamicSubject(Object obj) {
        sub = obj;
    }

    /**
     * 实现对某个代理对象的方法的执行
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before calling " + method);
        method.invoke(sub, args);
        System.out.println("After calling " + method);
        return null;
    }
}

class Client02 {
    public static void main() {
        RealSubject02 rs = new RealSubject02(); // 指定被代理类
        InvocationHandler ds = new DynamicSubject(rs); // 生成动态代理处理器，代理 RealSubject02
        Class cls = rs.getClass(); // 以下一次性生成

        // 获取代理类实例，指定类加载器，接口以及代理处理器
        Subject02 subject = (Subject02) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), ds);

        // 通过代理，调用方法
        subject.request();
    }
}
public class DynamicProxyExm {
    public static void main(String[] args) {
        Client02 client = new Client02();
        client.main();
    }
}
