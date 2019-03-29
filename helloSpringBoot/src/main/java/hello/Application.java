package hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * 例子来源： https://spring.io/guides/gs/spring-boot/
 *
 * SpringBootApplication 注解相当于加入了以下注解
 * 1) @Configuration 告诉 Spring Boot 该类可以作为 bean 的来源
 * 2) @EnableAutoConfiguration 告诉 Spring Boot 将所有符合自动配置条件的
 *      bean 定义加载到 IoC 容器
 * 3) @ComponentScan 告诉 Spring 查找 package 包中的其它 组件、服务、配置
 */
@SpringBootApplication
public class Application
{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName: beanNames) {
                System.out.println(beanName);
            }
        };
    }

    @Bean
    public String printSomeThings() {
        return "Hi, SpringBoot!";
    }
}
