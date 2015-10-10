package com.jfinalD.web.controller;

import java.sql.SQLException;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinalD.web.interceptor.ActionInterceptor;
import com.jfinalD.web.interceptor.ControllerInterceptor;
import com.jfinalD.web.model.Res;
import com.jfinalD.web.model.Role;
import com.jfinalD.web.model.User;


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
	
	public void clean() {
		renderText("清除上级(Controller)拦截器,意思就是这个方法不适用拦截器,仅限本次操作");
	}
//	@Clear(ClearLayer.ALL)
//	public void cleanAll() {
//		renderText("清除所有级别拦截器,意思就是这个方法不适用拦截器,仅限本次操作");
//	}
	
	public void testModel() {
		//测试一下存储
		new User().set("name", "张三").set("age", 11).save();
//		User.dao.deleteById(1);
		List<User> li = User.dao.find("select * from user");
		System.out.println("select * from user -->"+li);
		Page<User> page = User.dao.paginate(1, 10, "select *", "from user where age<?",18);
		System.out.println("分页"+page.getList());
		renderText("你好,testModel");
	}
	public void testDbRecord() {
		
		Record user = new Record().set("name", "李四").set("age", 17);
		Db.save("user", user);
		
		List<Record> user2 = Db.find("select * from user");
		System.out.println(user2);
		Page<Record> page = Db.paginate(1, 10, "select *", "from user where age<?",18);
		System.out.println("page===>"+page.getList());
		renderText("你好,testDbRecord");
	}
	public void testTx() {
		boolean b = Db.tx(new IAtom() {
				@Override
				public boolean run() throws SQLException {
					// TODO Auto-generated method stub
					
					return false;
				}
			});
	}
	@Before(Tx.class)
	public void testTx2() {
		
	}
	
	public void testCache() {
		Db.findByCache("", "key", "sql");
	}
}
