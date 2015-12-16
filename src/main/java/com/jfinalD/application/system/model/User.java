package com.jfinalD.application.system.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
@TableBind(tableName = "system_user",configName = "main")
public class User extends Model<User> {
	public static final User dao = new User();
	
	public User findByUsername(String username){
		
		return super.findFirst("select u.id,r.id as roleId,username,password,salt,is_locked,r.description,r.name as rolename from system_user u " +
				"INNER JOIN system_user_role ur on u.id = ur.user_id " +
				"INNER JOIN system_role r on ur.role_id = r.id " +
				"where username=? and is_locked=0",username);
	}
	
	public User findByUserId(int id){
		return super.findFirst("select u.id,u.username,u.password,u.salt,u.is_locked from system_user u where u.id=?",id);
	}
	
}
