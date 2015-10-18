package com.tan.web.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
@TableBind(tableName = "system_menu",configName = "main")
public class Menu extends Model<Menu>{
	private static final long serialVersionUID = -5747359745192545106L;
	
	public static Menu dao = new Menu();
	
	public List<String> getResUrl(String roleName){
		
		String sql = "select res.url from system_role_menu rr " +
				"INNER JOIN system_role r on r.id = rr.role_id and r.name=? " +
				"INNER JOIN system_menu res on res.id = rr.res_id";
		List<Menu> resList = find(sql,roleName);
		List<String> list = new ArrayList<String>();
		for(Menu res : resList){
			list.add(res.getStr("url"));
		}
		return list;
	}
}
