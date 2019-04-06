package moe.ning.wolfcourses._01Javaconfighello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Java Config：使用 Java 代码代替 XML 配置
 * @Configuration 把一个类标记成 spring 的配置对象
 *
 */
@Configuration
public class Config
{
    @Bean
    public SomeBean somebean() {
        return new SomeBean();
    }
}
