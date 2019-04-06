package moe.ning.wolfcourses._06import;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import ({RedisConfig.class, DatasourceConfig.class }) //意思是引入其它的配置对象
public class AppConfig
{
}
