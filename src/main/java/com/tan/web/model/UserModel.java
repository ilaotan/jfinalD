package com.tan.web.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
@TableBind(tableName = "system_user",configName = "main")
public class UserModel extends Model<UserModel> {
	public static final UserModel dao = new UserModel();
	
	public UserModel findByUsername(String username){
		
		return super.findFirst("select * from system_user where username=?",username);
	}
	
}
