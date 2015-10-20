package com.tan.web.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Page;
import com.tan.web.entity.TablePage;
import com.tan.web.model.UserModel;

@ControllerBind(controllerKey="/admin/user",viewPath="/ftl/admin/user")
public class UserAdminController extends Controller {
	
	private static final Logger LOG = Logger.getLogger(UserAdminController.class);
    
	public void index(){
		
		int pageNo = getParaToInt("pageNo", 1);
		int pageSize =getParaToInt("pageSize", 10);
		
		Page<UserModel> userPage = UserModel.dao.paginate(pageNo, pageSize, "select *", "from system_user");
		
		TablePage tp = new TablePage(pageNo,pageSize,userPage.getTotalRow(),userPage.getTotalPage(),userPage.getList());
		
		setAttr("page", userPage);
		setAttr("pageStr",tp.toString());
		
		render("index.html");
	}
	
	
}
