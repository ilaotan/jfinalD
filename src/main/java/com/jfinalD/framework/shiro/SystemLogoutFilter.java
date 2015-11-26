package com.jfinalD.framework.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import com.jfinal.kit.StrKit;
import com.jfinalD.framework.config.Constants;

public class SystemLogoutFilter extends LogoutFilter {
	/**
	 * 返回URL
	 */
	public static final String RETURN_URL = "returnUrl";

	protected String getRedirectUrl(ServletRequest req, ServletResponse resp,Subject subject) {
		HttpServletRequest request = (HttpServletRequest) req;
		String redirectUrl = request.getParameter(RETURN_URL);
		if (StrKit.isBlank(redirectUrl)) {
			request.getRequestURI();
			if (request.getRequestURI().startsWith(request.getContextPath() + Constants.ADMIN_PREFIX)) {
				redirectUrl = Constants.ADMIN_LOGIN;
			} else {
				redirectUrl = getRedirectUrl();
			}
		}
		return redirectUrl;
	}


	
}
