package moe.ning.readinglist.secutiry;

import moe.ning.readinglist.dao.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity // 禁用 springBoot 的默认 security 项，配合 Configuration
                   // 自定义配置，需要扩展 WebSecurityConfigurerAdapter
@EnableGlobalMethodSecurity (prePostEnabled = true) // 启用Security注解，例如最常用的@PreAuthorize
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    /*
     * 一个标准的 Java Config
     * 替代以前的 XML Config
     */
    @Autowired
    private ReaderRepository readerRepository;

    // configure(HttpSecurity http) Request 层面的配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //定义哪些 URL 需要被保护，那些不需要
            .antMatchers("/").access("hasRole('READER')")
            .antMatchers("/**").permitAll()
            .and()
            .formLogin()  // 定义用户需要登录时，转到的页面
            .loginPage("/login")
            .failureUrl("/login?error=true");
    }

    // configure(WebSecurity web) Web 层面的配置，
    // 一般用来配置不需要安全检查的路径
    public void configure(WebSecurity web) throws Exception {}

    // configure(AuthenicationManagerBuilder auth) 身份验证配置，
    // 用于注入自定义身份验证Bean和密码校验规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(new UserDetailsService()
            {
                @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
                {
                    return readerRepository.findById(username).orElse(null);
                }
            });
    }
    //TODO 学习 spring secutiry 结合 boot 配置
    //https://www.jianshu.com/p/08cc28921fd0
    //https://emacoo.cn/backend/spring-boot-security/
}
