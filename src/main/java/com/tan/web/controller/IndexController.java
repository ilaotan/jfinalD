package com.tan.web.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.tan.web.model.Res;

@ControllerBind(controllerKey="/",viewPath="/ftl/front")
public class IndexController extends Controller {
	
	public void index(){
//		System.err.println("url传来的"+getPara("name"));//http://localhost:8080/jfinalDemo/hello?name=3232
//		System.err.println("下标方法"+getPara(0));//http://localhost:8080/jfinalDemo/hello/xx1-xx2-xx3
//		
//		renderText("你好,老谭");
//		renderText("你好,老谭2");
		
//		User val = User.dao.findByUsername("tanlsh");
//		List<String> val = Role.dao.findRoleByUserId(1L);
		List<String> val = Res.dao.getResUrl("admin");
		setAttr("val",val);
		render("index.html");
//		renderJsp("a.jsp");
	}

	
}
