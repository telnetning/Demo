package moe.ning.wolfcourses._05autocomponentscan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Java Config：使用 Java 代码代替 XML 配置
 * @Configuration 把一个类标记成 spring 的配置对象
 *
 * @Component 自动完成 spring 组件的扫描
 * 为什么不需要加 basePackages ， 因为 @Component 模式会
 * 扫描被标注的类的对应的包以及其子包中的所有的类
 *
 * 当然，也可以设置 basePackages 定义扫描路径
 *
 * spring 的推荐结构：作为主配置对象的类，一般放在代码的根目录下面
 */

@Configuration
@ComponentScan
public class Config
{
}
