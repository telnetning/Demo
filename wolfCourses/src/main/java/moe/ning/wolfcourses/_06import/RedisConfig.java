package moe.ning.wolfcourses._06import;

import org.springframework.context.annotation.Bean;

public class RedisConfig
{
    @Bean
    public RedisTemplate redisTemplate() {
        return new RedisTemplate();
    }
}
