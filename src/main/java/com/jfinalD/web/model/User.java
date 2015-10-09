package com.jfinalD.web.model;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User> {
	public static final User dao = new User();
	
	public User findByUsername(String username){
		
		return super.findFirst("select * from system_user where username=?",username);
	}
	
}
