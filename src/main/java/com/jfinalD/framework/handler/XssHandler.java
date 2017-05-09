package com.jfinalD.framework.handler;

import com.jfinal.handler.Handler;
import com.jfinal.kit.StrKit;
import com.jfinalD.framework.servlet.MyHttpServletRequestWrapper;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一XSS处理
 * 适配shiro的httprequet 用来防xss注入
 * Create by tanliansheng on 2015年11月3日
 */
public class XssHandler extends Handler {

    // 排除的url，使用的target.startsWith匹配的
    private String exclude;

    public XssHandler(String exclude) {
        this.exclude = exclude;
    }

    @Override
    public void handle(String target, HttpServletRequest request,
                       HttpServletResponse response, boolean[] isHandled) {
        // 对于非静态文件，和非指定排除的url实现过滤
        if (target.indexOf('.') == -1 && StrKit.notBlank(exclude) && !target.startsWith(exclude)) {
            ShiroHttpServletRequest ssr = (ShiroHttpServletRequest) request;
            request = new MyHttpServletRequestWrapper(ssr, ssr.getServletContext(), ssr.isHttpSessions());
        }
        next.handle(target, request, response, isHandled);
    }
}
