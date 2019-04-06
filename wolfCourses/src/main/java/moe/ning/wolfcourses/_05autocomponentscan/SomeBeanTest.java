package moe.ning.wolfcourses._05autocomponentscan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 之前是手动创建上下文，这里使用 Spring 自己的测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class SomeBeanTest
{
    @Autowired
    private SomeBean someBean;

    @Test
    public void test() {
        System.out.println(someBean.getOtherBean());
    }
}
