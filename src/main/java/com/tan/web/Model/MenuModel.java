package com.tan.web.Model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
@TableBind(tableName = "system_menu",configName = "main")
public class MenuModel extends Model<MenuModel>{
	private static final long serialVersionUID = -5747359745192545106L;
	
	public static MenuModel dao = new MenuModel();
	
	public List<String> getResUrl(String roleName){
		
		String sql = "select sm.menu_url from system_role_menu rm " +
				"INNER JOIN system_role r on r.id = rm.role_id and r.name=? " +
				"INNER JOIN system_menu sm on sm.id = rm.menu_id";
		List<MenuModel> resList = find(sql,roleName);
		List<String> list = new ArrayList<String>();
		for(MenuModel res : resList){
			list.add(res.getStr("url"));
		}
		return list;
	}
}
