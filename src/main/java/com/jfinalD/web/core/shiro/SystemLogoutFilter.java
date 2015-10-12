package com.jfinalD.web.core.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import com.jfinal.config.Constants;
import com.jfinalD.web.utils.StringUtils;

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
