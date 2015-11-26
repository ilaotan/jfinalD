package com.jfinalD.application.system.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
/** 
 * Create by tanliansheng on 2015年10月29日
 */
@TableBind(tableName = "system_menu",configName = "main")
public class Menu extends Model<Menu>{
	private static final long serialVersionUID = -5747359745192545106L;
	
	public static Menu dao = new Menu();
	
	public List<String> getMenuUrlByRoleId(int roleId){
		
		String sql = "select sm.menu_url from system_role_menu rm " +
				"INNER JOIN system_menu sm on sm.id = rm.menu_id " + 
				"where rm.role_id= ?";
		List<Menu> resList = find(sql,roleId);
		List<String> list = new ArrayList<String>();
		for(Menu res : resList){
			String url = res.getStr("menu_url");
			// 一般不会出现这种情况
			if("--".equals(url)){
				continue;
			}
			list.add(url);
		}
		return list;
	}
	/** 先查一遍 ,如果存在子菜单,并删除菜单成功时,一并删除system_role_menu中关联的数据
	 * @param id
	 * @return
	 */
	public boolean deleteAllById(int id){
		
		List<Menu> menuChild = dao.find("select * from system_menu where menu_parent_id = ?",id);
		if(menuChild!=null && !menuChild.isEmpty()){
			int i = Db.update("delete from system_menu where menu_parent_id = ?",id);
			if(i>0){
				String ids =id+",";
				String sql = "delete from system_role_menu where menu_id in ";
				for(Menu menu:menuChild){
					ids +=menu.get("id")+",";
				}
				ids = ids.substring(0,ids.length()-1);
				ids = "("+ids+")";
				sql = sql+ids;
				Db.update(sql);
			}
		}
		return dao.deleteById(id);
	}
}
