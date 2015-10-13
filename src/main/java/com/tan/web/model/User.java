package com.tan.web.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

@TableBind(tableName = "system_user",configName = "main")
public class User extends Model<User> {
	public static final User dao = new User();
	
	public User findByUsername(String username){
		
		return super.findFirst("select * from system_user where username=?",username);
	}
	
}
