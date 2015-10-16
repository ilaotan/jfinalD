package com.jfinal.ext.plugin.shiro.tags;

import freemarker.template.SimpleHash;

/**
在freemarker中使用

@Override
public void afterJFinalStart() {
	FreeMarkerRender.getConfiguration().setSharedVariable("shiro", new ShiroTags());
	super.afterJFinalStart();
}

已经登陆判断
<@shiro.authenticated>
  <li><a href="/user/center"><@shiro.principal name="full_name"/></a></li>
      |
  <li><a href="/signout">退出</a></li>
</@shiro.authenticated>

没有登陆判断
<@shiro.notAuthenticated>
  <li><a href="/">登陆</a></li>
</@shiro.notAuthenticated>

显示登陆异常
<@shiro.isLoginFailure name="shiroLoginFailure">
  <@shiro.loginException name="shiroLoginFailure"/>
</@shiro.isLoginFailure>

判断角色
<@shiro.hasRole name="ROLE_ADMIN">
我是admin
</@shiro.hasRole>

判断权限
<@shiro.hasPermission name="P_ORDER_CONTROL">
  <li><a href="/order/branch" class="<#if activebar=='branch'>nav-active</#if>">全部订单</a></li>
</@shiro.hasPermission>

 */
public class ShiroTags extends SimpleHash {
  public ShiroTags() {
    put("authenticated", new AuthenticatedTag());
    put("guest", new GuestTag());
    put("hasAnyRoles", new HasAnyRolesTag());
    put("hasPermission", new HasPermissionTag());
    put("hasRole", new HasRoleTag());
    put("lacksPermission", new LacksPermissionTag());
    put("lacksRole", new LacksRoleTag());
    put("notAuthenticated", new NotAuthenticatedTag());
    put("principal", new PrincipalTag());
    put("user", new UserTag());
    put("isLoginFailure", new IsLoginFailureTag());
    put("loginException", new LoginExceptionTag());
    put("loginUsername", new LoginUsernameTag());
  }
}