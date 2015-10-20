package com.tan.web.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
@TableBind(tableName = "system_role",configName = "main")
public class RoleModel extends Model<RoleModel>{
	private static final long serialVersionUID = -5747359745192545106L;
	
	public static RoleModel dao = new RoleModel();
	
	
	public List<String> findRoleByUserId(Long id){
		List<RoleModel> roleList = super.find("select r.name from system_user_role ur INNER JOIN system_role r where ur.user_id=?",id);
		List<String> list = new ArrayList<String>();
		for(RoleModel role : roleList){
			list.add(role.getStr("name"));
		}
		return list;
	}
}
