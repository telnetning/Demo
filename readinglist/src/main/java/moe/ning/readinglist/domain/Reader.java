package moe.ning.readinglist.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Reader implements UserDetails
{
    private static final long serialVersionUID = 1L;

    @Id
    private String username;
    private String fullname;
    private String password;

    @Override public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getFullname()
    {
        return fullname;
    }

    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    @Override public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    //UserDetails methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 给予 reader 权限
        return Arrays.asList(new SimpleGrantedAuthority("READER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        //账户不会过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //账户不会被锁定
        return true;
    }

    @Override
    public boolean isEnabled() {
        //账户不会被撤销
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}

