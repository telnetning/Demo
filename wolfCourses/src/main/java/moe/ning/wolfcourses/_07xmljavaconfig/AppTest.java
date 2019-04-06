package moe.ning.wolfcourses._07xmljavaconfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppTest
{
    @Autowired
    private SomeBean sb;

    @Test
    public void test(){
        System.out.println(sb.getOtherBean());
    }
}
