package moe.ning.wolfcourses._04inject;

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
     * 第一种注入方式，直接调用依赖类的方法
      * @return
     */
    @Bean
    public SomeBean somebean() {
        SomeBean sb = new SomeBean();
        sb.setOtherBean(otherBean());
        return sb;
    }

    @Bean
    public SomeBean somebean2() {
        SomeBean sb = new SomeBean();
        sb.setOtherBean(otherBean());
        return sb;
    }

    /**
     * Config 类给到容器之后，容器管理类
     * 所以即使上面两个方法都要注入 otherBean()
     * 但是它们实际上是从容器中取到的单例的 OtherBean
     * @return
     */
    @Bean
    public OtherBean otherBean() {
        return new OtherBean();
    }

    /**
     * 第三种注入方式，直接写在方法参数里
     * @param ob
     * @return
     */
    @Bean
    public SomeBean somebean3(OtherBean ob) {
        SomeBean sb = new SomeBean();
        sb.setOtherBean(ob);
        return sb;
    }

    /**
     * 第二种方式，直接 new
     * 这种方式和之前的两种方式拿到的对象不是同一个，即，不是
     * 拿的容器管理的 单例 的
     */
    @Bean
    public SomeBean somebean4(){
        SomeBean sb = new SomeBean();
        sb.setOtherBean(new OtherBean());
        return sb;
    }
}
