
package com.jfinal.ext.plugin.shiro;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.StrKit;

public class ShiroInterceptor implements Interceptor {

	public void intercept(Invocation ai) {
		AuthzHandler ah = ShiroKit.getAuthzHandler(ai.getActionKey());
		// 存在访问控制处理器。
		if (ah != null) {
			try {
				// 执行权限检查。
				ah.assertAuthorized();
			} catch (UnauthenticatedException lae) {
				// RequiresGuest，RequiresAuthentication，RequiresUser，未满足时，抛出未经授权的异常。
				// 如果没有进行身份验证，返回HTTP401状态码,或者跳转到默认登录页面
				if(StrKit.notBlank(ShiroKit.getLoginUrl())){
					//保存登录前的页面信息,只保存GET请求。其他请求不处理。
					if(ai.getController().getRequest().getMethod().equalsIgnoreCase("GET")){
						ai.getController().setSessionAttr(ShiroKit.getSavedRequestKey(), ai.getActionKey());
					}
					ai.getController().redirect(ShiroKit.getLoginUrl());
				}else{
					ai.getController().renderError(401);
				}
				return;
			} catch (AuthorizationException ae) {
				// RequiresRoles，RequiresPermissions授权异常
				// 如果没有权限访问对应的资源，返回HTTP状态码403，或者调转到为授权页面
				if(StrKit.notBlank(ShiroKit.getUnauthorizedUrl())){
					ai.getController().redirect(ShiroKit.getUnauthorizedUrl());
				}else{
					ai.getController().renderError(403);
				}
				return;
			}
		}
		// 执行正常逻辑
		ai.invoke();
	}
}
