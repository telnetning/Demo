package moe.ning.wolfcourses._03factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java Config：使用 Java 代码代替 XML 配置
 * @Configuration 把一个类标记成 spring 的配置对象
 *
 * 以前 spring 的 bean
 * <bean id="" class="" name="bean01,bean02" init-method="" destory-method="" scope="">
 *   <property name="" value="">
 *   </property>
 * </bean>
 */
@Configuration
public class Config
{
    @Bean
    public SomeBeanFactory somebean() { //somebean 这个 id 注册在 工厂生产的对象上，而不是工厂上
        return new SomeBeanFactory();
    }
}
