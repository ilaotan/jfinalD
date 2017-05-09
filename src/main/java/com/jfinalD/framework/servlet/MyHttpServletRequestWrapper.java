package com.jfinalD.framework.servlet;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 适配shiro的httprequet 用来防xss注入
 * Create by tanliansheng on 2015年11月3日
 */
public class MyHttpServletRequestWrapper extends ShiroHttpServletRequest {


    public MyHttpServletRequestWrapper(HttpServletRequest wrapped, ServletContext servletContext,
                                       boolean httpSessions) {
        super(wrapped, servletContext, httpSessions);
    }


    /**
     * 重写并过滤getParameter方法
     */
    @Override
    public String getParameter(String name) {
        return HtmlFilter.getBasicHtmlandimage(super.getParameter(name));
    }

    /**
     * 重写并过滤getParameterValues方法
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (null == values) {
            return null;
        }
        String[] newValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            newValues[i] = HtmlFilter.getBasicHtmlandimage(values[i]);
        }
        return newValues;
    }

    /**
     * 重写并过滤getParameterMap方法
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> paraMap = super.getParameterMap();
        // 对于paraMap为空的直接return
        if (null == paraMap || paraMap.isEmpty()) {
            return paraMap;
        }
        Map<String, String[]> newParaMap = new HashMap<String, String[]>();
        for (Entry<String, String[]> entry : paraMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            if (null == values) {
                continue;
            }
            String[] newValues = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                newValues[i] = HtmlFilter.getBasicHtmlandimage(values[i]);
            }
            newParaMap.put(key, newValues);
        }
        return newParaMap;
    }
}
