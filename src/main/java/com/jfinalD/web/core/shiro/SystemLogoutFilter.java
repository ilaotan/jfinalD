/**
 * Copyright (c) 2014-2015 爱维宝贝web端团队   All rights reserved
 *
 * Base on awframework,powered by aiwei web team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.jfinalD.web.core.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import com.jfinal.config.Constants;
import com.jfinalD.web.utils.StringUtils;

/**
 * user filter 主要为了控制不同类型用户的跳转
 * @author Chenz
 * @date 2014-11-6 16:32:59
 *
 */
public class SystemLogoutFilter extends LogoutFilter {
	/**
	 * 返回URL
	 */
	public static final String RETURN_URL = "returnUrl";

	protected String getRedirectUrl(ServletRequest req, ServletResponse resp,Subject subject) {
		HttpServletRequest request = (HttpServletRequest) req;
		String redirectUrl = request.getParameter(RETURN_URL);
		if (StringUtils.isBlank(redirectUrl)) {
			String type = request.getParameter("type");
//			if (request.getRequestURI().startsWith(request.getContextPath() + Constants.ADMIN_PREFIX)) {
			if(!StringUtils.isBlank(type) && type.equals("system")){
//				redirectUrl = Constants.ADMIN_LOGIN_URL;
			} else if(!StringUtils.isBlank(type) && type.equals("contractor")){
//				redirectUrl = Constants.CONTRACTOR_LOGIN_URL;
			} else {
				redirectUrl = getRedirectUrl();
			}
		}
		return redirectUrl;
	}


	
}
