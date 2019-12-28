package com.wjx.springbootprac.po;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "t_user")
@Data
public class User implements UserDetails {
    @Id
    private Integer id;
    /**
      *用户名
      */
    private String username;
    /**
      *密码
      */
    private String password;

    /**
      *是否启用：0不启用,1启用
      */
    private boolean enabled;

    /**
      *是否锁定
      */
    private boolean locked;
    /**
     * 角色列表
     */
    @Transient
    private List<Role> roleList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authoritielist = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roleList) {
            authoritielist.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authoritielist;
    }

    //账户是否没有过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //是否没有锁定
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }
    //密码是否没有过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //是否启用
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
