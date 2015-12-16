package com.jfinalD.application.front;

import java.sql.SQLException;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jfinalD.application.system.model.Menu;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
@ControllerBind(controllerKey="/",viewPath="/ftl/front")
public class IndexController extends Controller {
	
	//@Before({Tx.class,CacheInterceptor.class})//EvictInterceptor.class 清除缓存
	//@CacheName("myCache")
	public void index(){
		
		
	
		render("index.html");
	}
	public void faq(){
		render("faq.html");
	}
	
	
}
