
package com.base.dto;

import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Set;


@Data
public class LoginAuthDto implements Serializable {
	private static final long serialVersionUID = -1137852221455042256L;
	private Long userId;
	private String loginName;
	private String userName;
	private Long groupId;
	private String groupName;
	private Set<String> grantedAuthorities;

	public LoginAuthDto() {
	}

	public LoginAuthDto(Long userId, String loginName, String userName) {
		this.userId = userId;
		this.loginName = loginName;
		this.userName = userName;
	}

	public LoginAuthDto(Long userId, String loginName, String userName, Long groupId, String groupName) {
		this.userId = userId;
		this.loginName = loginName;
		this.userName = userName;
		this.groupId = groupId;
		this.groupName = groupName;
	}
}
