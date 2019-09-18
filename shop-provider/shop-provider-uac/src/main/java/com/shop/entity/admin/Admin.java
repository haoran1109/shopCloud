package com.shop.entity.admin;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.base.model.AbstractObject;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 【】持久化对象 数据库表：admin
 * 用户表
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:35:57
 * 
 */
public class Admin extends AbstractObject implements UserDetails {

    public static final long serialVersionUID = 1L;

    // 
    private Long id;
    // 
    private Date createdTime;
    // 
    private String password;
    // 
    private String username;

    /**
     * 用户有权访问的所有url，不持久化到数据库
     */
    @Transient
    private Set<String> urls = new HashSet<>();
    /**
     * 用户有权的所有资源id，不持久化到数据库
     */
    @Transient
    private Set<Long> resourceIds = new HashSet<>();

    /** 获取  属性 */
    public Long getId() {
        return id;
    }

    /** 设置  属性 */
    public void setId(Long id) {
        this.id = id;
    }

    /** 获取  属性 */
    public Date getCreatedTime() {
        return createdTime;
    }

    /** 设置  属性 */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /** 获取  属性 */
    @Override
    public String getPassword() {
        return password;
    }

    /** 设置  属性 */
    public void setPassword(String password) {
        this.password = password;
    }

    /** 获取  属性 */
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /** 设置  属性 */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Admin");
        sb.append("{id=").append(id);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", password=").append(password);
        sb.append(", username=").append(username);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Admin) {
            Admin admin = (Admin) obj;
            if (this.getId().equals(admin.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        String pkStr = "" + this.getId();
        return pkStr.hashCode();
    }

}