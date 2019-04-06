package moe.ning.wolfcourses._03factorybean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sound.midi.SysexMessage;

/**
 *
 */
public class SomeBeanTest
{
    @Test
    public void test() {
        //需要指定 Config.class 配置对象，直接指定或者 register + refresh
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        //获取 somebean 和以前方式一样
        SomeBean someBean = ctx.getBean("somebean", SomeBean.class);
        System.out.println(someBean);

        //获取工厂本身
        SomeBeanFactory someBeanFactory = ctx.getBean("&somebean", SomeBeanFactory.class);
        System.out.println(someBeanFactory);
    }
}
