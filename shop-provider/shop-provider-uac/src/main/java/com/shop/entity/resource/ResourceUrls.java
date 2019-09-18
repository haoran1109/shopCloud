package com.shop.entity.resource;


import com.base.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：resource_urls
 * 权限具体的URL表
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:35:59
 * 
 */
public class ResourceUrls extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Long id;
    // 
    private Long resourceId;
    // 
    private String urls;

    /** 获取  属性 */
    public Long getId() {
        return id;
    }

    /** 设置  属性 */
    public void setId(Long id) {
        this.id = id;
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
    public String getUrls() {
        return urls;
    }

    /** 设置  属性 */
    public void setUrls(String urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ResourceUrls");
        sb.append("{id=").append(id);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", urls=").append(urls);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ResourceUrls) {
            ResourceUrls resourceUrls = (ResourceUrls) obj;
            if (this.getId().equals(resourceUrls.getId())) {
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