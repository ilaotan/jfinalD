package com.jfinalD.framework.shiro;

import java.io.Serializable;

public class ShiroUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private final Long id;
	private final String username;
	private final String description;
	private String roleName;
	
	
	public ShiroUser(Long id, String username, String description, String roleName) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.roleName = roleName;
	}
	
	@Override
	public String toString() {
		return "ShiroUser [id=" + id + ", username=" + username + ", description=" + description + ", roleName="
				+ roleName + "]";
	}

	public final Long getId() {
		return id;
	}

	public final String getUsername() {
		return username;
	}

	public final String getDescription() {
		return description;
	}

	public final String getRoleName() {
		return roleName;
	}

	public final void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
