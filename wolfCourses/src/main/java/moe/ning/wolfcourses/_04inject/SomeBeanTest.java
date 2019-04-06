package moe.ning.wolfcourses._04inject;

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

        //使用 类名 的方式，可能会重复
//        SomeBean someBean = ctx.getBean(SomeBean.class);
//        System.out.println(someBean);
//        SomeBean someBean01 = ctx.getBean(SomeBean.class);
//        System.out.println(someBean01);

        SomeBean someBean = ctx.getBean("somebean", SomeBean.class);
        System.out.println(someBean.getOtherBean());

        SomeBean someBean2 = ctx.getBean("somebean2", SomeBean.class);
        System.out.println(someBean2.getOtherBean());

        SomeBean someBean3 = ctx.getBean("somebean3", SomeBean.class);
        System.out.println(someBean3.getOtherBean());

        SomeBean someBean4 = ctx.getBean("somebean4", SomeBean.class);
        System.out.println(someBean4.getOtherBean());
        ctx.close(); //保证容器正常关闭，不要被强制关闭，被强制关闭无法调用到 destory
    }
}
