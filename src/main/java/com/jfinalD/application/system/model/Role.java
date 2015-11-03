package com.jfinalD.application.system.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
/** 
 * Create by tanliansheng on 2015年10月29日
 */
@TableBind(tableName = "system_role",configName = "main")
public class Role extends Model<Role>{
	private static final long serialVersionUID = -5747359745192545106L;
	
	public static Role dao = new Role();
	
	
	public List<String> findRoleByUserId(Long id){
		List<Role> roleList = super.find("select r.name from system_user_role ur INNER JOIN system_role r on ur.role_id = r.id where ur.user_id=?",id);
		List<String> list = new ArrayList<String>();
		for(Role role : roleList){
			list.add(role.getStr("name"));
		}
		return list;
	}
}
