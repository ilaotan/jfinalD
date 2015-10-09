package com.jfinalD.web.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class ControllerInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		System.err.println("Controller before Invoke");
		inv.invoke();
		System.err.println("Controller after Invoke");
	}

}
