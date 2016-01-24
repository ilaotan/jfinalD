package com.jfinalD.application.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Page;
import com.jfinalD.application.system.entity.TablePage;
import com.jfinalD.application.system.model.User;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
public class UserAdminController extends Controller {

	static Log log = Log.getLog(UserAdminController.class);
    
	public void index(){
		
		int pageNo = getParaToInt("pageNo", 1);
		int pageSize =getParaToInt("pageSize", 10);
		
		Page<User> userPage = User.dao.paginate(pageNo,pageSize);

		TablePage tp = new TablePage(pageNo,pageSize,userPage.getTotalRow(),userPage.getTotalPage());
		
		setAttr("page", userPage);
		setAttr("pageStr",tp.toString());
		
		render("index.html");
	}

	public void update(){
		int id = getParaToInt();
		if(id>0){
			setAttr("user", User.dao.findByUserId(id));
			render("update.html");
		}else{
			renderJson(id);
		}
	}
	public void updatePOST() {
		log.debug("已经提交一些东西了!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		render("index.html");
	}
	
	
}
