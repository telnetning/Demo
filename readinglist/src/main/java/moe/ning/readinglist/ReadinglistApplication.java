package moe.ning.readinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 开启组件扫描和自动配置
public class ReadinglistApplication
{

    public static void main(String[] args)
    {
        // 负责启动引导程序
        SpringApplication.run(ReadinglistApplication.class, args);
    }

}
