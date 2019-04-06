package moe.ning.wolfcourses._06import;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {DatasourceConfig.class, RedisConfig.class})
@ContextConfiguration(classes = AppConfig.class)
public class ImportTest
{
    @Autowired
    private Datasource ds;

    @Autowired
    private RedisTemplate rt;

    @Test
    public void test() {
        System.out.println(ds);
        System.out.println(rt);
    }
}
