package moe.ning.wolfcourses._01Javaconfighello;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 */
public class SomeBeanTest
{
    @Test
    public void test() {
        //需要指定 Config.class 配置对象，直接指定或者 register + refresh
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        //ctx.register(Config.class);
        //ctx.refresh();

        SomeBean someBean = ctx.getBean(SomeBean.class);
        System.out.println(someBean);
        SomeBean someBean01 = ctx.getBean(SomeBean.class);
        System.out.println(someBean01);
    }
}
