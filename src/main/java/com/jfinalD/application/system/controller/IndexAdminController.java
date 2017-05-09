package com.jfinalD.application.system.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.plugin.route.ControllerBind;

import java.sql.SQLException;

/**
 * Create by tanliansheng on 2015年10月29日
 */
@ControllerBind(controllerKey = "/admin", viewPath = "admin")
public class IndexAdminController extends Controller {

    //@Before({Tx.class,CacheInterceptor.class})//EvictInterceptor.class 清除缓存
    //@CacheName("myCache")
    public void index() throws SQLException {
//		System.err.println("url传来的"+getPara("name"));//http://localhost:8080/jfinalDemo/hello?name=3232
//		System.err.println("下标方法"+getPara(0));//http://localhost:8080/jfinalDemo/hello/xx1-xx2-xx3
//		
//		renderText("你好,老谭");
//		renderText("你好,老谭2");

//		User val = User.dao.findByUsername("tanlsh");
//		List<String> val = Role.dao.findRoleByUserId(1L);
//		List<String> val = Menu.dao.getMenuUrlByRoleId(1);
//		setAttr("val",val);


//		TestService ts = enhance(TestService.class);
//		ts.testInsert("小明111");

        render("index.html");
//		renderJsp("a.jsp");
    }


}
