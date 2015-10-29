package com.tan.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.tan.web.common.Constants;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
public class GlobalInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		HttpServletRequest request = inv.getController().getRequest();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		
		inv.getController().getRequest().setAttribute("ctx", basePath);
		
		inv.invoke();
	}

}
