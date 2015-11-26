package com.jfinalD.framework.shiro;

import java.io.Serializable;

public class ShiroUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private final Integer id;  //user表id
	private final String username;
	private final String description;
	private final Integer roleId;// role表id
	private final String roleName;//role的name
	
	
	@Override
	public String toString() {
		return "ShiroUser [id=" + id + ", username=" + username + ", description=" + description + ", roleId=" + roleId
				+ ", roleName=" + roleName + "]";
	}

	public ShiroUser(Integer id, String username, String description, Integer roleId, String roleName) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getDescription() {
		return description;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

}
