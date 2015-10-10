package com.jfinalD.web.config;

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
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import com.jfinalD.ext.shiro.ShiroInterceptor;
import com.jfinalD.ext.shiro.ShiroPlugin;
import com.jfinalD.web.controller.IndexController;
import com.jfinalD.web.interceptor.GlobalInterceptor;
import com.jfinalD.web.model.Res;
import com.jfinalD.web.model.Role;
import com.jfinalD.web.model.User;
import com.jfinalD.web.route.AdminRoutes;

public class MyConfig extends JFinalConfig {
	
	/**
     * 供Shiro插件使用。
     */
    Routes routes;
	
	@Override
	public void configConstant(Constants constant) {
		loadPropertyFile("jdbc.properties");
		constant.setDevMode(getPropertyToBoolean("devMode", true));
		constant.setUrlParaSeparator("-");//设置参数分隔符
		
//		constant.setViewType(ViewType.JSP); // 设置视图类型为Jsp，否则默认为FreeMarker
		constant.setError404View("/WEB-INF/pages/404.html");
		constant.setError500View("/WEB-INF/pages/500.html");
		// for shiro
		constant.setError401View("/WEB-INF/pages/401.html");//没有身份验证时
		constant.setError403View("/WEB-INF/pages/403.html");//美欧权限时
		
	}

	@Override
	public void configRoute(Routes me) {
		//给shiro用的
		this.routes = me;
		
		me.add("/",IndexController.class,"ftl");
		me.add(new AdminRoutes()); // 具体的控制由AdminRoutes分发
		
	}

	
	@Override
	public void configHandler(Handlers me) {
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
		//添加shiro的全局变量
		me.add(new ShiroInterceptor());
		
		me.add(new GlobalInterceptor());
		
//		me.add(new SessionInViewInterceptor());//解决session在freemarker中不能取得的问题 获取方法：${session["manager"].username}
	}

	@Override
	public void configPlugin(Plugins me) {
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
		arp.addMapping("system_user",User.class);	//
		arp.addMapping("system_res",Res.class);	//
		arp.addMapping("system_role",Role.class);	//
		me.add(arp);
		
		//加载Shiro插件
		//me.add(new ShiroPlugin(routes));
		ShiroPlugin shiroPlugin = new ShiroPlugin(this.routes);
		shiroPlugin.setLoginUrl("/login.do");
		shiroPlugin.setSuccessUrl("/index.do");
		shiroPlugin.setUnauthorizedUrl("/login.do");
		me.add(shiroPlugin);
		
//		me.add(new EhCachePlugin());
	}


	@Override
	public void afterJFinalStart() {
		super.afterJFinalStart();
	}

	@Override
	public void beforeJFinalStop() {
		super.beforeJFinalStop();
	}
	
	//判断是哪种环境
	private boolean isDevMode(){
		String osName = System.getProperty("os.name");
		return osName.indexOf("Windows") != -1;
	}

}
