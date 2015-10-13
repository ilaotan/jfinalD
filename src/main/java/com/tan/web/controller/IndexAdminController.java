package com.tan.web.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

@ControllerBind(controllerKey="/admin",viewPath="/ftl/admin")
public class IndexAdminController extends Controller {
	
	public void index(){
		setAttr("val","这里是admin的index");
		render("index.html");
	}
	
	public void test(){
		setAttr("val","这是来自/admin/test的值");
		render("index.html");
	}
	
}
