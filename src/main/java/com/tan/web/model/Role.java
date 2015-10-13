package com.tan.web.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class Role extends Model<Role>{
	private static final long serialVersionUID = -5747359745192545106L;
	
	public static Role dao = new Role();
	
	
	public List<String> findRoleByUserId(Long id){
		List<Role> roleList = super.find("select r.name from system_user_role ur INNER JOIN system_role r where ur.user_id=?",id);
		List<String> list = new ArrayList<String>();
		for(Role role : roleList){
			list.add(role.getStr("name"));
		}
		return list;
	}
}
