
package com.jfinal.ext.plugin.shiro;

import org.apache.shiro.authz.AuthorizationException;

import java.util.List;

/**
 * 组合模式访问控制处理器
 * @author dafei
 *
 */
class CompositeAuthzHandler implements AuthzHandler {

	private final List<AuthzHandler> authzHandlers;

	public CompositeAuthzHandler(List<AuthzHandler> authzHandlers){
		this.authzHandlers = authzHandlers;
	}

	public void assertAuthorized() throws AuthorizationException {
		for(AuthzHandler authzHandler : authzHandlers){
			authzHandler.assertAuthorized();
		}
	}
}
