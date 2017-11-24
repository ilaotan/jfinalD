package com.jfinal.ext.plugin.shiro.tag;

import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;


/**
 * Created by jie on 2017/4/3.
 * 用户已经身份验证通过，即没有调用Subject.login进行登录，包括记住我自动登录的也属于未进行身份验证。
 * #noAuthenticated()
 * body
 * #end
 */
@DefineDirective(tag = "noAuthenticated")
public class NoAuthenticatedTag extends SecureTag {

    @Override
    public void exec(Env env, Scope scope, Writer writer) {
        if (getSubject() != null && !getSubject().isAuthenticated()) {
            stat.exec(env, scope, writer);
        }
    }

    @Override
    public boolean hasEnd() {
        return true;
    }
}
