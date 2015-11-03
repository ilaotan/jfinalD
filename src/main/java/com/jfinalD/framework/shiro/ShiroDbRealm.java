package com.jfinalD.framework.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.jfinal.kit.StrKit;
import com.jfinalD.application.system.model.Menu;
import com.jfinalD.application.system.model.Role;
import com.jfinalD.application.system.model.User;

public class ShiroDbRealm extends AuthorizingRealm {
    
    public ShiroDbRealm(){
        setAuthenticationTokenClass(CaptchaUsernamePasswordToken.class);
    }

	@Override
	public void setCacheManager(CacheManager cacheManager){
		super.setCacheManager(cacheManager);
		ShiroCache.setCacheManager(cacheManager);
	}
    
//    /* 
//	 * tanliansheng
//	 * 2015年1月15日09:04:34
//	 * 父类默认方法会拿null。覆盖一下父类方法 使用role的code当缓存的key
//	 * @see org.apache.shiro.realm.AuthorizingRealm#getAuthorizationCacheKey(org.apache.shiro.subject.PrincipalCollection)
//	 */
	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		ShiroUser user = (ShiroUser) principals.getPrimaryPrincipal();
		return "autz-"+user.getRoleName();
	}
    
    /**
     * 认证回调函数,登录时调用.
     */    
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
        CaptchaUsernamePasswordToken authcToken = (CaptchaUsernamePasswordToken) token;
        String accountName = authcToken.getUsername();
        if (StrKit.isBlank(accountName)) {
            throw new AuthenticationException("用户名不可以为空");
        }
        boolean isCaptchaBlank = StrKit.isBlank(authcToken.getCaptcha());
        if (isCaptchaBlank) {
            throw new IncorrectCaptchaException("验证码不可以为空!");
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        String md5Code = null;
        if(session != null){
            md5Code = (String)session.getAttribute(CaptchaRender.DEFAULT_CAPTCHA_MD5_CODE_KEY);
        }
        boolean isRight = CaptchaRender.validate(md5Code, authcToken.getCaptcha());
        if (!isRight) {
            throw new IncorrectCaptchaException("验证码错误!");
        }
        User user = User.dao.findByUsername(accountName);
        if (null == user) {
            throw new AuthenticationException("用户名或者密码错误");
        }
        if (user.getBoolean("is_locked")) {
            throw new LockedAccountException("该用户已被锁定");
        }
        ShiroUser principal = 
        		new ShiroUser(user.getLong("id"),user.getStr("username"),user.getStr("description"), user.getStr("rolename"));
        AuthenticationInfo authinfo = 
        		new SimpleAuthenticationInfo(principal,user.getStr("password"), getName());
        return  authinfo;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //User user = (User) principals.fromRealm(getName()).iterator().next();
    	ShiroUser simpleUser = (ShiroUser) principals.fromRealm(getName()).iterator().next();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if( null == simpleUser){
        	return info;
        }
        //role角色默认只有一个??? 还是多个角色
        info.addRoles(Role.dao.findRoleByUserId(simpleUser.getId()));
        //根据刚才塞进去的role 拿所有的url权限
        for (String role : info.getRoles()){
			info.addStringPermissions(Menu.dao.getMenuUrl(role));
		}
        return info;
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}
