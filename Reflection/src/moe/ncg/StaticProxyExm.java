package moe.ncg;

/**
 * 代理模式
 * 通过代理模式操作真实的对象，原因可能是权限控制等
 *
 * 一般涉及的角色：
 * 1) 抽象角色：声明真实对象和代理对象的共同接口
 * 2) 代理角色：包含对真实对象的引用，可以操作真实对象，并且可以
 * 在操作中增加一下附加操作
 * 3) 真实角色：最终引用的角色
 */

// 抽象角色
abstract class Subject01 {
    public abstract void request();
}

// 真实角色
class RealSubject extends Subject01 {
    public void request() {
        System.out.println("From Real Subject.");
    }
}

// 客户端
class Client {
    public static void main() {
        Subject01 subject = new ProxySubject();
        subject.request();
    }
}

// 代理角色
class ProxySubject extends Subject01 {
    private RealSubject realSubject;

    @Override
    public void request() {
        preRequest();
        if(realSubject == null) {
            realSubject = new RealSubject();
        }
        realSubject.request();
        postRequest();
    }

    private void preRequest() {
        System.out.println("Pre Request");
    }

    private void postRequest() {
        System.out.println("Post Request");
    }
}

public class StaticProxyExm {
    public static void main(String[] args) {
        Client client = new Client();
        client.main();
    }
}

/**
 * 静态代理的优点：保证业务重用性，其实所有代理模式都有这个有点
 * 缺点：一个接口只服务于一种类型的对象，如果要代理的方法很多，需要为每一种方法都进行代理，
 * 程序规模较大时就无法胜任; 如果接口增加一个方法，除了所有实现类需要实现这个方法外，所有代理类也需要实现。
 */
