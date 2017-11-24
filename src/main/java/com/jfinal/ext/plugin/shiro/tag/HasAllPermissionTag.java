package com.jfinal.ext.plugin.shiro.tag;

import com.jfinal.ext.plugin.shiro.util.ArrayUtil;
import com.jfinal.template.Env;
import com.jfinal.template.expr.ast.Expr;
import com.jfinal.template.expr.ast.ExprList;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;


/**
 * Created by jie on 2017/4/3.
 * 验证当前Subject是否有下列全部权限
 * #hasAllPermission(permissionName1,permissionName2)
 * body
 * #end
 */
@DefineDirective(tag = "hasAllPermission")
public class HasAllPermissionTag extends SecureTag {
    private Expr[] exprs;

    @Override
    public void setExprList(ExprList exprList) {
        exprs = exprList.getExprArray();
    }

    @Override
    public void exec(Env env, Scope scope, Writer writer) {
        if (getSubject() != null && ArrayUtil.isNotEmpty(exprs)) {
            boolean hasAllPermission = true;
            for (Expr expr : exprs) {
                if (!getSubject().isPermitted(expr.toString())) {
                    hasAllPermission = false;
                    break;
                }
            }

            if (hasAllPermission) {
                stat.exec(env, scope, writer);
            }
        }
    }

    @Override
    public boolean hasEnd() {
        return true;
    }
}
