package com.jfinal.ext.plugin.shiro.tag;

import java.lang.annotation.*;

/**
 * Created by jie on 2017/4/11.
 * 自定义扩展Directive注解方便统一管理
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Inherited 不继承
public @interface DefineDirective {
    /**
     * 标签名称
     *
     * @return String
     */
    String tag() default "";
}
