package com.jfinal.ext.plugin.shiro.tag;

import com.jfinal.kit.LogKit;
import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;

import java.io.IOException;

/**
 * Created by jie on 2017/4/3.
 * 获取Subject Principal 信息
 * #principal()
 */
@DefineDirective(tag = "principal")
public class PrincipalTag extends SecureTag {

    @Override
    public void exec(Env env, Scope scope, Writer writer) {
        if (getSubject() != null && getSubject().getPrincipal() != null) {
            Object principal = getSubject().getPrincipal();
            try {
                writer.write(principal.toString());
            }
            catch (IOException e) {
                LogKit.error("PrincipalTag IOException");
                e.printStackTrace();
            }
        }
    }

}
