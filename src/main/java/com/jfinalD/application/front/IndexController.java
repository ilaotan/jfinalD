package com.jfinalD.application.front;

import com.jfinal.core.Controller;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
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
