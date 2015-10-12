package com.jfinalD.web.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinalD.web.common.Constants;
import com.jfinalD.web.model.Res;


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
		render("front/index.html");
//		renderJsp("a.jsp");
	}

	
}
