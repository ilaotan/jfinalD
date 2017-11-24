package com.jfinal.ext.plugin.shiro.tag;

import com.jfinal.ext.plugin.shiro.util.ArrayUtil;
import com.jfinal.template.Env;
import com.jfinal.template.expr.ast.Expr;
import com.jfinal.template.expr.ast.ExprList;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jie on 2017/4/3.
 * 验证当前用户是否属于以下全部角色
 * #hasAllRoles(roleName1,roleName2)
 * body
 * #end
 */
@DefineDirective(tag = "hasAllRoles")
public class HasAllRolesTag extends SecureTag {
    private Expr[] exprs;


    @Override
    public void setExprList(ExprList exprList) {
        exprs = exprList.getExprArray();
    }


    @Override
    public void exec(Env env, Scope scope, Writer writer) {
        if (getSubject() != null && ArrayUtil.isNotEmpty(exprs)) {
            List<String> roles = new ArrayList<String>();
            for (Expr expr : exprs) {
                roles.add(expr.toString());
            }

            if (getSubject().hasAllRoles(roles)) {
                stat.exec(env, scope, writer);
            }

        }
    }

    @Override
    public boolean hasEnd() {
        return true;
    }


}