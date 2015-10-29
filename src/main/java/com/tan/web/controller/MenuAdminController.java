package com.tan.web.controller;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Logger;
import com.tan.web.entity.MenuTree;
import com.tan.web.entity.ValueItem;
import com.tan.web.model.MenuModel;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
@ControllerBind(controllerKey="/admin/menu",viewPath="/ftl/admin/menu")
public class MenuAdminController extends Controller {
	
	private static final Logger LOG = Logger.getLogger(MenuAdminController.class);
    
	public void index(){
		setAttr("tree", new MenuTree(0, "/", "根菜单", null,null));
		
		render("index.html");
	}
	
	/**
	 * 跳转到添加子菜单页面
	 */
	public void add(){
		
		List list = new ArrayList();
		list.add(new ValueItem("1","yes"));
		list.add(new ValueItem("2","no"));
		setAttr("yesnos", list);
		setAttr("menu_parent_id", getParaToInt(0));
		render("add.html");
	}
	
	public void save(){
		boolean b = new MenuModel()
			.set("menu_name",getPara("row.menu_name"))
			.set("menu_url", getPara("row.menu_url"))
			.set("menu_sn", getParaToInt("row.menu_sn"))
			.set("menu_type", getPara("row.menu_type"))
			.set("menu_parent_id", getParaToInt("row.menu_parent_id"))
			//TODO:修改者的信息
			.save();
				
		renderJson(b);
	}
	@Before(GET.class)
	public void update(){
		
		List list = new ArrayList();
		list.add(new ValueItem("1","yes"));
		list.add(new ValueItem("2","no"));
		setAttr("yesnos", list);
		setAttr("menu_parent_id", getParaToInt(0));
		MenuModel mm = MenuModel.dao.findById(getParaToInt(0));
		this.setAttr("row", mm);
		render("add.html");
	}
	
	@Before(POST.class)
	public void updatePost(){
		//update  
		 boolean b = MenuModel.dao.findById(getParaToInt("row.menu_parent_id"))
		 	.set("menu_name",getPara("row.menu_name"))
			.set("menu_url", getPara("row.menu_url"))
			.set("menu_sn", getParaToInt("row.menu_sn"))
			.set("menu_type", getPara("row.menu_type"))
			//TODO:修改者的信息
		 	.update();
		 renderJson(b);
	}
	
	public void delete(){
		 boolean b = MenuModel.dao.deleteAllById(getParaToInt(0));
		 renderJson(b);
	}
}
