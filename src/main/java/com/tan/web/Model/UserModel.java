package com.tan.web.Model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

@TableBind(tableName = "system_user",configName = "main")
public class UserModel extends Model<UserModel> {
	public static final UserModel dao = new UserModel();
	
	public UserModel findByUsername(String username){
		
		return super.findFirst("select * from system_user where username=?",username);
	}
	
}
