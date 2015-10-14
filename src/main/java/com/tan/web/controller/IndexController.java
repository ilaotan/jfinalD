package com.tan.web.controller;

import java.sql.SQLException;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.tan.web.model.Res;
import com.tan.web.model.Test;
import com.tan.web.service.TestService;

@ControllerBind(controllerKey="/",viewPath="/ftl/front")
public class IndexController extends Controller {
	
	//@Before({Tx.class,CacheInterceptor.class})//EvictInterceptor.class 清除缓存
	//@CacheName("myCache")
	public void index() throws SQLException{
//		System.err.println("url传来的"+getPara("name"));//http://localhost:8080/jfinalDemo/hello?name=3232
//		System.err.println("下标方法"+getPara(0));//http://localhost:8080/jfinalDemo/hello/xx1-xx2-xx3
//		
//		renderText("你好,老谭");
//		renderText("你好,老谭2");
		
//		User val = User.dao.findByUsername("tanlsh");
//		List<String> val = Role.dao.findRoleByUserId(1L);
		List<String> val = Res.dao.getResUrl("admin");
		setAttr("val",val);
		

//		TestService ts = enhance(TestService.class);
//		ts.testInsert("小明111");
		
		render("index.html");
//		renderJsp("a.jsp");
	}

	
}
