# JFinalShiroPlugin

是针对@玛雅牛 JFinalShiroPlugin 在 **jfinal 3.3 **版本 做的一个扩展，支持shiro 标签。

## 使用方法

### 1. pom加入

clone 代码 mvn install （注意是1.3分支哟）


### 2. 配置jfinal config

如果使用jf template 的话，构造ShiroPlugin（engine），注意不再是原来 **Routes**对象啦。
```
public class LmsCoreConfig extends JFinalConfig {

    /**
     * 配置模板引擎
     *
     * @param me Engine
     * 如果公共模板中有引用shiro标签，那么ShiroKit.initDirective在要在它之前
     */
    public void configEngine(Engine me) {
        ShiroKit.initDirective(me);
        me.addSharedFunction("_common/common.html");
    }

    /**
     * 配置插件
     *
     * @param me Plugins
     */
    public void configPlugin(Plugins me) {
        ShiroPlugin shiroPlugin = new ShiroPlugin();
        shiroPlugin.setLoginUrl("/login");
        shiroPlugin.setUnauthorizedUrl("/unauthorized");
        me.add(shiroPlugin);
    }

    /**
     * 配置拦截器
     *
     * @param me Interceptors
     */
    public void configInterceptor(Interceptors me) {
        me.add(new ShiroInterceptor());
    }

}
```
### 3.页面使用

```
获取Subject Principal 信息
  #principal()


用户没有身份验证时显示相应信息，即游客访问信息。
 #guest()
   body
 #end


用户已经身份验证/记住我登录后显示相应的信息。
 #shiroUser()
   body
 #end

用户已经身份验证通过，即Subject.login登录成功，不是记住我登录的
 #authenticated()
    body
 #end
 

用户已经身份验证通过，即没有调用Subject.login进行登录，包括记住我自动登录的也属于未进行身份验证。
 #noAuthenticated()
    body
 #end

 验证当前Subject是否有该权限
 #hasPermission(permissionName)
    body
 #end
 
验证当前用户是否属于以下角色
#hasRole(roleName)
 body
#end
 
验证当前用户是否属于以下全部角色
 #hasAllRoles(roleName1,roleName2)
    body
 #end
 
验证当前用户是否属于以下任意一个角色
 #hasAnyRoles(roleName1,roleName2)
    body
 #end
 
验证当前Subject是否有下列全部权限
 #hasAllPermission(permissionName1,permissionName2)
    body
 #end
 
验证当前Subject是否没有该权限
  #lacksPermission(permissionName)
     body
  #end

验证当前Subject是否没有角色
 #lacksRole(roleName)
    body
 #end
```
### PS
例子可以参考：[http://git.oschina.net/log4j/LMS](http://git.oschina.net/log4j/LMS)
反馈帮助： QQ群237587118