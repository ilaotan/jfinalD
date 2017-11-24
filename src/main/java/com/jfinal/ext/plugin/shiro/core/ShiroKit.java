package com.jfinal.ext.plugin.shiro.core;

import com.jfinal.ext.plugin.shiro.tag.DefineDirective;
import com.jfinal.ext.plugin.shiro.util.ClassUtil;
import com.jfinal.kit.LogKit;
import com.jfinal.template.Directive;
import com.jfinal.template.Engine;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;


/**
 * ShiroKit. (Singleton, ThreadSafe)
 *
 * @author dafei
 */
public class ShiroKit {

    /**
     * 登录成功时所用的页面。
     */
    private static String successUrl = "/";

    /**
     * 登录成功时所用的页面。
     */
    private static String loginUrl = "/login.html";


    /**
     * action的扩展名，比如.action或者 .do
     */
    private static String extName = "";


    /**
     * 登录成功时所用的页面。
     */
    private static String unauthorizedUrl = "/401.jsp";


    /**
     * Session中保存的请求的Key值
     */
    private static String SAVED_REQUEST_KEY = "jfinalShiroSavedRequest";


    /**
     * 用来记录那个action或者actionpath中是否有shiro认证注解。
     */
    private static ConcurrentMap<String, AuthzHandler> authzMaps = null;

    /**
     * 禁止初始化
     */
    private ShiroKit() {
    }

    static void init(ConcurrentMap<String, AuthzHandler> maps) {
        authzMaps = maps;
    }

    static AuthzHandler getAuthzHandler(String actionKey) {
        /*
		if(authzMaps.containsKey(controllerClassName)){
			return true;
		}*/
        return authzMaps.get(actionKey);
    }

    public static final String getSuccessUrl() {
        return successUrl;
    }

    public static final void setSuccessUrl(String successUrl) {
        ShiroKit.successUrl = successUrl;
    }

    public static final String getLoginUrl() {
        return loginUrl;
    }


    public static final void setLoginUrl(String loginUrl) {
        ShiroKit.loginUrl = loginUrl;
    }

    public static final String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public static final void setUnauthorizedUrl(String unauthorizedUrl) {
        ShiroKit.unauthorizedUrl = unauthorizedUrl;
    }

    /**
     * Session中保存的请求的Key值
     * @return
     */
    public static final String getSavedRequestKey() {
        return SAVED_REQUEST_KEY;
    }

    /**
     * 扩展名，比如.action／.do
     *
     * @param extName
     */
    public static final void setExtName(String extName) {
        ShiroKit.extName = extName;
    }

    public static final String getExtName() {
        return extName;
    }

    //	private static final String defineDirective = "com.github.jieblog.plugin.shiro.tag";

    public static void initDirective(Engine me) {

        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(DefineDirective.class.getPackage().getName(), true,
				DefineDirective.class);
        for (Class<?> clazz : classes) {
            DefineDirective defineDirective = clazz.getAnnotation(DefineDirective.class);
            String tag = defineDirective.tag();
            if (tag != null && !"".equals(tag)) {
                me.addDirective(tag, (Directive) ClassUtil.newInstance(clazz));
            }
            else {
                LogKit.error("自定义Directive的标签为空无效：" + clazz.getName());
            }
        }
    }

}
