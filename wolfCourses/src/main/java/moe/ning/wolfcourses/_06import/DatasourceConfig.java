package moe.ning.wolfcourses._06import;

import org.springframework.context.annotation.Bean;

public class DatasourceConfig
{
    @Bean
    public Datasource datasource() {
        return new Datasource();
    }
}
