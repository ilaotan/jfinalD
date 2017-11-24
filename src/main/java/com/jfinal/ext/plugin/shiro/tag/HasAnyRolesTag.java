package com.jfinal.ext.plugin.shiro.tag;

import com.jfinal.ext.plugin.shiro.util.ArrayUtil;
import com.jfinal.template.Env;
import com.jfinal.template.expr.ast.Expr;
import com.jfinal.template.expr.ast.ExprList;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;


/**
 * Created by jie on 2017/4/3.
 * 验证当前用户是否属于以下任意一个角色
 * #hasAnyRoles(roleName1,roleName2)
 * body
 * #end
 */
@DefineDirective(tag = "hasAnyRoles")
public class HasAnyRolesTag extends SecureTag {
    private Expr[] exprs;


    @Override
    public void setExprList(ExprList exprList) {
        exprs = exprList.getExprArray();
    }


    @Override
    public void exec(Env env, Scope scope, Writer writer) {
        if (getSubject() != null && ArrayUtil.isNotEmpty(exprs)) {
            for (Expr expr : exprs) {
                if (getSubject().hasRole(expr.toString())) {
                    stat.exec(env, scope, writer);
                    break;
                }
            }
        }
    }

    @Override
    public boolean hasEnd() {
        return true;
    }


}