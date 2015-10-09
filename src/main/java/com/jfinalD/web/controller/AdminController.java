package com.jfinalD.web.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;

import com.jfinal.core.Controller;

public class AdminController extends Controller {
	
	public void index() {
		renderText("admin的Controller");
	}
}
