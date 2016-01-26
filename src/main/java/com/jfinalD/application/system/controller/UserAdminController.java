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

		int id = getParaToInt();
		String roleName = getPara("user.rolename");

		User oldUser = User.dao.findByUserId(id);

		//Controller 之中的 getModel()需要表单域名称对应于数据表字段名，而 getBean()则依赖于
		//setter方法，表单域名对应于setter方法去掉”set”前缀字符后剩下的字符道字母变小写
		User user = getBean(User.class);

		//先用比较笨的方法实现


		String passwd = getPara("passwd","");
		if(!"".equals(passwd)){
			//TODO:  需要更新密码

		}

		render("index.html");
	}
	
	
}
