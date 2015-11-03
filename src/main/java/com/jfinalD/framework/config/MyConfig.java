package com.jfinalD.framework.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.ext.plugin.shiro.tags.ShiroTags;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.druid.IDruidStatViewAuth;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinalD.framework.handler.SessionIdHandler;
import com.jfinalD.framework.handler.XssHandler;
import com.jfinalD.framework.interceptor.GlobalInterceptor;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
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
    
	@Override
	public void configPlugin(Plugins me) {

		//这是默认的
//		DruidPlugin dpOne = new DruidPlugin(getProperty("jdbc.url"), getProperty("jdbc.username"), getProperty("jdbc.password"));
//		WallFilter wallOne = new WallFilter();
//		wallOne.setDbType("mysql");
//		dpOne.addFilter(wallOne);
//		dpOne.addFilter(new StatFilter());
//		me.add(dpOne);
//		ActiveRecordPlugin arpOne = new ActiveRecordPlugin(dpOne);
//		me.add(arpOne);
//		if (isDevMode())arpOne.setShowSql(true);
///////////////////////////
//		arpOne.addMapping("system_res", Res.class);
//		arpOne.addMapping("system_role", Role.class);
//		arpOne.addMapping("system_user", User.class);
///////////////////////////
//		
//		DruidPlugin dpTwo = new DruidPlugin(getProperty("jdbc.url2"), getProperty("jdbc.username"), getProperty("jdbc.password"));
//		WallFilter wallTwo = new WallFilter();
//		wallTwo.setDbType("mysql");
//		dpTwo.addFilter(wallTwo);
//		dpTwo.addFilter(new StatFilter());
//		me.add(dpTwo);
//		ActiveRecordPlugin arpTwo = new ActiveRecordPlugin("main2",dpTwo);
//		me.add(arpTwo);
//		arpTwo.setTransactionLevel(2);
//		if (isDevMode())arpTwo.setShowSql(true);
/////////////////////////////
//		arpTwo.addMapping("test", Test.class);
//////////////////////////////

		// 多数据源 
		DruidPlugin dp = new DruidPlugin(getProperty("jdbc.url"), getProperty("jdbc.username"), getProperty("jdbc.password"));
		WallFilter wall = new WallFilter();
		wall.setDbType("mysql");
		dp.addFilter(wall);
		dp.addFilter(new StatFilter());
		me.add(dp);
		//默认的名字就是main
		AutoTableBindPlugin atbp = new AutoTableBindPlugin("main",dp);
		if (isDevMode()) atbp.setShowSql(true);
		atbp.autoScan(false);
		me.add(atbp);
		
		/*
		 * 默认的单@Before(Tx.class)只对主数据源的事务有效 如果希望这个db2也支持事务 需要使用@TxConfig("db2")指定配置 这两个一块用 
		 * or 使用Db.use(dsName).tx(...)-
		 * */
		DruidPlugin dp2 = new DruidPlugin(getProperty("jdbc.url2"), getProperty("jdbc.username"), getProperty("jdbc.password"));
		WallFilter wall2 = new WallFilter();
		wall2.setDbType("mysql");
		dp2.addFilter(wall2);
		dp2.addFilter(new StatFilter());
		me.add(dp2);
		AutoTableBindPlugin atbp2 = new AutoTableBindPlugin("db2",dp2);
		if (isDevMode()) atbp2.setShowSql(true);
		atbp2.autoScan(false);
		me.add(atbp2);
		
		//加载Shiro插件
		me.add(new ShiroPlugin(this.routes));
		//加载Ecache插件
		me.add(new EhCachePlugin());
		//加载Redis插件
		me.add(new RedisPlugin("myRedis","127.0.0.1", 6379,0,"ilaotan123456qwer",0));

	}

	@Override
	public void configHandler(Handlers me) {
		
		//无cookie时,会在url上添加;sessionId 这里做下判断,去除
        me.add(new SessionIdHandler());
		
        me.add(new XssHandler("/admin1")); // `/admin*`为排除的目录

		//访问路径是/druid/index.html
		DruidStatViewHandler dvh =  new DruidStatViewHandler("/druid", new IDruidStatViewAuth() {
			
			public boolean isPermitted(HttpServletRequest request) {//获得查看权限
				Subject subject = SecurityUtils.getSubject();
				 if(subject.isAuthenticated()){
					 return true;
				 }
				 return false;
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
		
		me.add(new SessionInViewInterceptor());//解决session在freemarker中不能取得的问题 获取方法：${session["manager"].username}
	}

	@Override
	public void afterJFinalStart() {
		FreeMarkerRender.getConfiguration().setSharedVariable("shiro", new ShiroTags());
//		FreeMarkerRender.getConfiguration().setSharedVariable("myKit", new ShiroTags());
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
	
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 8082, "/", 5);
	}
	

}
