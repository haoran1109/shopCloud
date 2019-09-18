package com.shop.entity.roleresource;

import java.util.Date;

import com.base.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：roleresource
 * 角色权限关系表
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:36:00
 * 
 */
public class Roleresource extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Long id;
    // 
    private Date createdTime;
    // 
    private Long resourceId;
    // 
    private Long roleId;

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
    public Long getResourceId() {
        return resourceId;
    }

    /** 设置  属性 */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /** 获取  属性 */
    public Long getRoleId() {
        return roleId;
    }

    /** 设置  属性 */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Roleresource");
        sb.append("{id=").append(id);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", roleId=").append(roleId);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Roleresource) {
            Roleresource roleresource = (Roleresource) obj;
            if (this.getId().equals(roleresource.getId())) {
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