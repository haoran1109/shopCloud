package com.shop.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The class Uac user token.
 *
 *
 */
@Data
public class UacUserToken implements Serializable {
	private static final long serialVersionUID = 4341237600124353997L;
	/**
	 * 父ID
	 */
	private Long pid;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 姓名
	 */
	private String userName;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 登陆人Ip地址
	 */
	private String loginIp;

	/**
	 * 登录地址
	 */
	private String loginLocation;

	/**
	 * 登录地址
	 */
	private Date loginTime;

	/**
	 * 操作系统
	 */
	private String os;

	/**
	 * 浏览器类型
	 */
	private String browser;

	/**
	 * 访问token
	 */
	private String accessToken;

	/**
	 * 刷新token
	 */
	private String refreshToken;

	/**
	 * 访问token的生效时间(秒)
	 */
	private long accessTokenValidity;

	/**
	 * 刷新token的生效时间(秒)
	 */
	private long refreshTokenValidity;

	/**
	 * 0 在线 10已刷新 20 离线
	 */
	private Integer status;

	/**
	 * 组织流水号
	 */
	private Long groupId;

	/**
	 * 组织名称
	 */
	private String groupName;

	/**
	 * 失效时间(秒)
	 */
	@Transient
	private Integer expiresIn;
	/**
	 * 用户权限信息 如有修改 需要重新登录set
	 */
	@Transient
	private  Set<String> grantedAuthorities;


}