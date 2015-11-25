package com.jfinalD.framework.config;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
public class Constants{

	public static final String SESSION_USER = "user";
	
	public static final String ERROR = "error";
	

	/***
	 * 分布式session开关 请在redis.properties 配置ip和端口
	 */
	public static boolean OPEN_REDIS = true;

	public static  String LOGIN ="/admin/login/";
	
	public static final String ADMIN_PREFIX = "/admin";
	
	/**
	 * 验证码名称
	 */
	public static final String CAPTCHA_PARAM = "captcha";
	
    public static class RequestMethod {
        public static final String GET = "get";
        public static final String POST = "post";
    }
    //shiro使用的用来获得登录界面传来的username和password
    public static final String USERNAME = "user.username";
    public static final String PASSWORD = "user.password";
    public static final String PRIVATESALT = "qw65";
}
