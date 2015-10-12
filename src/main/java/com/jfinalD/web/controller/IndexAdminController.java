package com.jfinalD.web.controller;

import com.jfinal.core.Controller;


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
