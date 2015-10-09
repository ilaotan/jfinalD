package com.jfinalD.web.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class GlobalInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		System.err.println("全局 before Invoke");
		inv.invoke();
		System.err.println("全局 after Invoke");
	}

}
