package com.jfinalD.framework.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
public class ActionExtentionHandler extends Handler {
    
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
//		if(target.indexOf(";JSESSIONID")>-1){
//			target = target.substring(0, target.indexOf(";JSESSIONID"));
//		}
//		if(target.indexOf("JSESSIONID")>-1 && target.indexOf(";JSESSIONID")==-1){
//			target = target.replace(";JSESSIONID", "-;JSESSIONID");
//		}
//        if (target.endsWith(".html"))
//            target = target.substring(0, target.length() - 5);
        nextHandler.handle(target, request, response, isHandled);
    }
}