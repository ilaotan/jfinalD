package com.jfinalD.application.system.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinalD.application.system.entity.MenuTree;
import com.jfinalD.application.system.entity.MenuTreeCheck;
import com.jfinalD.application.system.entity.TablePage;
import com.jfinalD.application.system.model.RoleModel;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
@ControllerBind(controllerKey="/admin/role",viewPath="/ftl/admin/role")
public class RoleAdminController extends Controller {
	
	private static final Logger LOG = Logger.getLogger(RoleAdminController.class);
    
	public void index(){
		
		int pageNo = getParaToInt("pageNo", 1);
		int pageSize =getParaToInt("pageSize", 10);
		
		Page<RoleModel> rolePage = RoleModel.dao.paginate(pageNo, pageSize, "select *", "from system_role where id<?",2);
		
		TablePage tp = new TablePage(pageNo,pageSize,rolePage.getTotalRow(),rolePage.getTotalPage());
		
		setAttr("page", rolePage);
		setAttr("pageStr",tp.toString());
		
		render("index.html");
	}
	
	public void menu(){
		final int roleId = getParaToInt();
		setAttr("roleid", roleId);
		setAttr("tree", new MenuTree(0, "/", "根菜单", null, new MenuTreeCheck(){
			@Override
			public boolean isCheck(int menuId) {
				String sql = "select count(1) from system_role_menu where role_id=? and menu_id=?";
				long count = Db.queryLong(sql, roleId, menuId);
				
				return count == 1 ? true : false;
			}
		}));
		render("menu.html");
	}
}
