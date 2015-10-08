package com.tanlsh.jfinal.demo.controller;

import com.jfinal.core.Controller;

public class UserController extends Controller {
	public void index() {
		renderText("admin/user的Controller");
	}
}
