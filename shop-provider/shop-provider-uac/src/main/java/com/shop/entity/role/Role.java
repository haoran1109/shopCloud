package com.shop.entity.role;

import java.util.Date;

import com.base.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：role
 * 角色表
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:36:00
 * 
 */
public class Role extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Long id;
    // 
    private Date createdTime;
    // 
    private String name;

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

    /** 获取  属性 */
    public String getName() {
        return name;
    }

    /** 设置  属性 */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Role");
        sb.append("{id=").append(id);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Role) {
            Role role = (Role) obj;
            if (this.getId().equals(role.getId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getId();
        return pkStr.hashCode();
    }

}