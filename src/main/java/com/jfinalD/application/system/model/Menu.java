package com.jfinalD.application.system.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinalD.application.system.model.base.BaseMenu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Menu extends BaseMenu<Menu> {
	public static final Menu dao = new Menu();

	public List<String> getMenuUrlByRoleId(int roleId){
		//Db.use("数据库2").tx(new IAtom() {
		//	@Override
		//	public boolean run() throws SQLException {
		//		return false;
		//	}
		//});
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

	public void test(){

	}
}
