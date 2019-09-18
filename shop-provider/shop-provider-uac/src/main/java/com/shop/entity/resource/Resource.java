package com.shop.entity.resource;

import java.util.Date;

import com.base.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：resource
 * 权限表
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:35:58
 * 
 */
public class Resource extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Long id;
    // 
    private Date createdTime;
    // 
    private String icon;
    // 
    private String link;
    // 
    private String name;
    // 
    private Integer sort;
    // 
    private String type;
    // 
    private Long parentId;

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
    public String getIcon() {
        return icon;
    }

    /** 设置  属性 */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /** 获取  属性 */
    public String getLink() {
        return link;
    }

    /** 设置  属性 */
    public void setLink(String link) {
        this.link = link;
    }

    /** 获取  属性 */
    public String getName() {
        return name;
    }

    /** 设置  属性 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取  属性 */
    public Integer getSort() {
        return sort;
    }

    /** 设置  属性 */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /** 获取  属性 */
    public String getType() {
        return type;
    }

    /** 设置  属性 */
    public void setType(String type) {
        this.type = type;
    }

    /** 获取  属性 */
    public Long getParentId() {
        return parentId;
    }

    /** 设置  属性 */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Resource");
        sb.append("{id=").append(id);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", icon=").append(icon);
        sb.append(", link=").append(link);
        sb.append(", name=").append(name);
        sb.append(", sort=").append(sort);
        sb.append(", type=").append(type);
        sb.append(", parentId=").append(parentId);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Resource) {
            Resource resource = (Resource) obj;
            if (this.getId().equals(resource.getId())) {
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