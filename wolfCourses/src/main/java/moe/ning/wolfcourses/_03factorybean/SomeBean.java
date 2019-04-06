package moe.ning.wolfcourses._03factorybean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// @Scope 在这里定义 Scope
public class SomeBean
{
    @PostConstruct
    public void init() {
        System.out.println("=====init====");
    }

    @PreDestroy
    public void destory() {
        System.out.println("=====destory====");
    }
}
