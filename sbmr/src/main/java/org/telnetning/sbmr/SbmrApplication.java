package org.telnetning.sbmr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
@MapperScan("org.telnetning.sbmr.dao")
public class SbmrApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SbmrApplication.class, args);
    }

}
