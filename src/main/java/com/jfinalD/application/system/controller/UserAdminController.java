package com.jfinalD.application.system.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Page;
import com.jfinalD.application.system.entity.TablePage;
import com.jfinalD.application.system.model.UserModel;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
@ControllerBind(controllerKey="/admin/user",viewPath="/ftl/admin/user")
public class UserAdminController extends Controller {
	
	private static final Logger LOG = Logger.getLogger(UserAdminController.class);
    
	public void index(){
		
		int pageNo = getParaToInt("pageNo", 1);
		int pageSize =getParaToInt("pageSize", 10);
		
		Page<UserModel> userPage = UserModel.dao.paginate(pageNo, pageSize, "select *", "from system_user");
		
		TablePage tp = new TablePage(pageNo,pageSize,userPage.getTotalRow(),userPage.getTotalPage());
		
		setAttr("page", userPage);
		setAttr("pageStr",tp.toString());
		
		render("index.html");
	}
	
	
}
