package com.jfinal.ext.plugin.shiro.tag;

import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;


/**
 * Created by jie on 2017/4/3.
 * 用户已经身份验证/记住我登录后显示相应的信息。
 * #shiroUser()
 * body
 * #end
 */
@DefineDirective(tag = "shiroUser")
public class ShiroUserTag extends SecureTag {

    @Override
    public void exec(Env env, Scope scope, Writer writer) {
        if (getSubject() != null && getSubject().getPrincipal() != null) {
            stat.exec(env, scope, writer);
        }
    }

    @Override
    public boolean hasEnd() {
        return true;
    }
}
