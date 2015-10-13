package com.tan.web.config;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.druid.IDruidStatViewAuth;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.tan.web.controller.IndexAdminController;
import com.tan.web.handler.ActionExtentionHandler;
import com.tan.web.interceptor.GlobalInterceptor;

public class MyConfig extends JFinalConfig {
	
	/**
     * 供Shiro插件使用。
     */
    Routes routes;
	
	@Override
	public void configConstant(Constants constant) {
		loadPropertyFile("config.properties");
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
		
		me.add(new AutoBindRoutes());
		
//		me.add("/",IndexController.class,"ftl");
//		me.add("/account",LoginController.class,"ftl/account");
//		
//		adminRoute(me);
	}
	//后台路由配置
    public void adminRoute(Routes me) {
        me.add("/admin", IndexAdminController.class, "ftl/admin");
    }
    
	@Override
	public void configPlugin(Plugins me) {
		
		DruidPlugin dp = new DruidPlugin(getProperty("jdbc.url"), getProperty("jdbc.username"), getProperty("jdbc.password"));
		WallFilter wall = new WallFilter();
		wall.setDbType("mysql");
		dp.addFilter(wall);
		dp.addFilter(new StatFilter());
		me.add(dp);
		AutoTableBindPlugin atbp = new AutoTableBindPlugin("db1",dp);
		if (isDevMode()) atbp.setShowSql(true);
		atbp.autoScan(false);
		me.add(atbp);
		
		//多数据源
		DruidPlugin dp2 = new DruidPlugin(getProperty("jdbc.url2"), getProperty("jdbc.username"), getProperty("jdbc.password"));
		WallFilter wall2 = new WallFilter();
		wall2.setDbType("mysql");
		dp2.addFilter(wall2);
		dp2.addFilter(new StatFilter());
		me.add(dp2);
		AutoTableBindPlugin atbp2 = new AutoTableBindPlugin("db2",dp2);
		if (isDevMode()) atbp2.setShowSql(true);
//		atbp2.addScanPackages("com.test.model.ds1");
		atbp2.autoScan(false);
		me.add(atbp2);
		
		//加载Shiro插件
		//me.add(new ShiroPlugin(routes));
		ShiroPlugin shiroPlugin = new ShiroPlugin(this.routes);
		me.add(shiroPlugin);
		
		me.add(new EhCachePlugin());
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
		
		//处理shiro的?;JSESSIONID             TEST
		me.add(new ActionExtentionHandler());
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
