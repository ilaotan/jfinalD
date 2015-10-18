package com.tan.web.common;

public class Constants
{

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

}
