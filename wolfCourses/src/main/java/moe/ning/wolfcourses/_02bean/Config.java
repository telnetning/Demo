package moe.ning.wolfcourses._02bean;

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
    /**
     * 方法的名字 就是 bean 的 id
     * @return
     */
    @Bean
    public SomeBean somebean() {
        return new SomeBean();
    }

    @Bean
    public SomeBean somebean2() {
        return new SomeBean();
    }

//    @Bean(name = "sb", initMethod = "init", destroyMethod = "destory")
//    public SomeBean somebean3() {
//        return new SomeBean();
//    }

    //类中的方法加上了 PostConstruct 和 PreDestory 后不用在手动指定
    @Bean(name = "sb")
    public SomeBean somebean3() {
        return new SomeBean();
    }
}
