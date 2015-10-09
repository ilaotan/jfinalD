package com.jfinalD.web.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class ActionInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		System.err.println("Action before Invoke");
		System.out.println("getControllerKey"+inv.getControllerKey());
		System.out.println("getActionKey"+inv.getActionKey());
		System.out.println("getController().getPara(\"name\")"+inv.getController().getPara("name"));
		System.out.println("getMethod().getName()"+inv.getMethod().getName());
		System.out.println("getViewPath"+inv.getViewPath());
		inv.invoke();
		System.err.println("Action after Invoke");
	}

}
