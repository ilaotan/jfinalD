package com.ilaotan.framework.beetl;

import org.beetl.core.Context;
import org.beetl.core.Function;

public class IsTrueFun implements Function {
    @Override
    public Object call(Object[] paras, Context ctx) {
        if (paras.length != 1) {
            throw new RuntimeException("参数错误，期望Object");
        }
        Object para = paras[0];
        if (para == null) {
            return Boolean.FALSE;
        }
        if ("true".equalsIgnoreCase(para.toString())) {
            return Boolean.TRUE;
        }
        if ("1".equals(para.toString())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}