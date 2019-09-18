package com.shop.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.Collection;
/**
 * @description: 自定义用户拓展信息表
 * @author haoran.zhang
 * @date 2019/08/09 15:57
 * @param
 * @return
 */
public class SocialUserExt extends SocialUser {

    public SocialUserExt(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long id) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
    }

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
