package com.tanlsh.jfinal.demo.config;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.druid.IDruidStatViewAuth;
import com.jfinal.render.ViewType;
import com.tanlsh.jfinal.demo.controller.HelloController;
import com.tanlsh.jfinal.demo.interceptor.GlobalInterceptor;
import com.tanlsh.jfinal.demo.model.User;
import com.tanlsh.jfinal.demo.route.AdminRoutes;

public class DemoConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants constant) {
		// TODO Auto-generated method stub
		loadPropertyFile("jdbc.properties");
		constant.setDevMode(getPropertyToBoolean("devMode", false));
		constant.setUrlParaSeparator("-");//设置参数分隔符
		
		constant.setViewType(ViewType.JSP); // 设置视图类型为Jsp，否则默认为FreeMarker
		constant.setError404View("/WEB-INF/pages/404.html");
		constant.setError500View("/WEB-INF/pages/500.html");
		constant.setBaseViewPath("/WEB-INF/pages/");
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		//访问路径是/druid/index.html
		DruidStatViewHandler dvh =  new DruidStatViewHandler("/druid", new IDruidStatViewAuth() {
			public boolean isPermitted(HttpServletRequest request) {//获得查看权限
//				HttpSession hs = request.getSession(false);
//				return (hs != null && hs.getAttribute("$admin$") != null);
				return true;
			}
		});
		me.add(dvh);
	}

	/* 
	 * 只有全局的拦截器在这里配置
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new GlobalInterceptor());
//		me.add(new SessionInViewInterceptor());//解决session在freemarker中不能取得的问题 获取方法：${session["manager"].username}
	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		// DruidPlugin
		DruidPlugin dp = new DruidPlugin(getProperty("jdbc.url"), getProperty("jdbc.username"), getProperty("jdbc.password"));
		WallFilter wall = new WallFilter();
		wall.setDbType("mysql");
		dp.addFilter(wall);
		dp.addFilter(new StatFilter());
		me.add(dp);
 
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		//数据库表与bean映射
		arp.addMapping("user", "user_id",User.class);	//
		me.add(arp);
			
	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		me.add("/hello",HelloController.class);
		me.add(new AdminRoutes()); // 具体的控制由AdminRoutes分发
		
	}

	@Override
	public void afterJFinalStart() {
		// TODO Auto-generated method stub
		super.afterJFinalStart();
	}

	@Override
	public void beforeJFinalStop() {
		// TODO Auto-generated method stub
		super.beforeJFinalStop();
	}

}
