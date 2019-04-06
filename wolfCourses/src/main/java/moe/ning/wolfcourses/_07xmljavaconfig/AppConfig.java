package moe.ning.wolfcourses._07xmljavaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 同时需要 SomeBean 和 OtherBean
 * 并且有的是在 Java Config 中配置的
 * 有的是在 XML 中配置的
 */
@Configuration
@ImportResource ("classpath:moe/ning/wolfcourses/_07xmljavaconfig/Application.xml") //引入其它的资源配置
public class AppConfig
{
    @Bean
    public SomeBean someBean(OtherBean otherBean) {
        SomeBean sb = new SomeBean();
        sb.setOtherBean(otherBean);
        return sb;
    }
}
