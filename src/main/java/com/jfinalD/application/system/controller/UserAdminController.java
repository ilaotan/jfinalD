package com.jfinalD.application.system.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Page;
import com.jfinalD.application.system.entity.TablePage;
import com.jfinalD.application.system.model.User;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
@ControllerBind(controllerKey="/admin/user",viewPath="/ftl/admin/user")
public class UserAdminController extends Controller {
	
	private static final Logger LOG = Logger.getLogger(UserAdminController.class);
    
	public void index(){
		
		int pageNo = getParaToInt("pageNo", 1);
		int pageSize =getParaToInt("pageSize", 10);
		
		Page<User> userPage = User.dao.paginate(pageNo, pageSize, "select u.username,u.is_locked,u.id,r.name,r.description ", "from system_user u "+
				"LEFT JOIN system_user_role ur on ur.user_id = u.id "+
				"LEFT JOIN system_role r on r.id = ur.role_id ");
		
		TablePage tp = new TablePage(pageNo,pageSize,userPage.getTotalRow(),userPage.getTotalPage());
		
		setAttr("page", userPage);
		setAttr("pageStr",tp.toString());
		
		render("index.html");
	}
	
	public void update(){
		int id = getParaToInt("id",0);
		if(id>0){
			setAttr("user", User.dao.findByUserId(id));
			render("update.html");
		}
		
	}
	
	
}
