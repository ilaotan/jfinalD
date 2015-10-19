package com.tan.web.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Logger;
import com.tan.web.entity.ValueItem;

@ControllerBind(controllerKey="/admin/menu",viewPath="/ftl/admin")
public class MenuController extends Controller {
	
	private static final Logger LOG = Logger.getLogger(MenuController.class);
    
	public void index(){
		
		render("menu/list.html");
	}
	
	/**
	 * 跳转到添加子菜单页面
	 */
	public void add(){
		
		List list = new ArrayList();
		list.add(new ValueItem("1","yes"));
		list.add(new ValueItem("2","no"));
		setAttr("yesnos", list);
		setAttr("ucenter_menu_parent_id", getParaToInt(0));
		render("menu/add.html");
	}
	
}
