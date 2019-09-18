
package com.shop.security;

import com.shop.dao.admin.AdminDao;
import com.shop.dao.resource.ResourceUrlsDao;
import com.shop.dao.roleadmin.RoleadminDao;
import com.shop.entity.SocialUserExt;
import com.shop.entity.admin.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class DemoUserDetailsService implements UserDetailsService, SocialUserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private ResourceUrlsDao resourceUrlsDao;
	@Autowired
	private RoleadminDao roleadminDao;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return buildUser(username);
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		logger.info("设计登录用户Id:" + userId);
		return buildUser(userId);
	}

	/**
	 * @description: 自定义实现的查询用户信息以及权限信息
	 * @author haoran.zhang
	 * @date 2019/08/04 20:26
	 * @param [userId]
	 * @return org.springframework.social.security.SocialUserDetails
	 */
	private SocialUserDetails buildUser(String userId) {
		// 根据用户名查找用户信息
		Map<String,Object> params=new HashMap<>();
		params.put("username",userId);
		Admin admin = adminDao.selectByUserName(params);
		if (null==admin) {
			throw new UsernameNotFoundException("用户:" + userId + ",不存在!");
		}
		Map<String,Object> reresourceParams=new HashMap<>();
		reresourceParams.put("id",admin.getId());

		logger.info("数据库密码是:"+admin.getPassword());
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		boolean enabled = true; // 可用性 :true:可用 false:不可用
		boolean accountNonExpired = true; // 过期性 :true:没过期 false:过期
		boolean credentialsNonExpired = true; // 有效性 :true:凭证有效 false:凭证无效
		boolean accountNonLocked = true; // 锁定性 :true:未锁定 false:已锁定
		//查询用户角色
		List<String>  roles=roleadminDao.getRolesByUserId(reresourceParams);
		for (String role:roles){
			//角色必须是ROLE_开头，可以在数据库中设置
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+role);
			grantedAuthorities.add(grantedAuthority);
			//获取权限
			List<String> permissionList = resourceUrlsDao.getUrlsByUserId(reresourceParams);
			for (String menu:permissionList) {
				GrantedAuthority authority = new SimpleGrantedAuthority(menu);
				grantedAuthorities.add(authority);
			}
		}
		//组装用户信息
		return new SocialUserExt(userId, admin.getPassword(),
				true, true, true, true,
				grantedAuthorities,admin.getId());
	}

}
