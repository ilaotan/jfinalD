package com.jfinal.ext.plugin.shiro.tag;

import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;


/**
 * Created by jie on 2017/4/3.
 * 用户已经身份验证通过，即Subject.login登录成功，不是记住我登录的
 * #authenticated()
 * body
 * #end
 */
@DefineDirective(tag = "authenticated")
public class AuthenticatedTag extends SecureTag {

    @Override
    public void exec(Env env, Scope scope, Writer writer) {
        if (getSubject() != null && getSubject().isAuthenticated()) {
            stat.exec(env, scope, writer);
        }
    }

    @Override
    public boolean hasEnd() {
        return true;
    }
}
