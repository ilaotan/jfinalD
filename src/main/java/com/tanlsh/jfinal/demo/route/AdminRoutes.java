package com.tanlsh.jfinal.demo.route;

import com.jfinal.config.Routes;
import com.tanlsh.jfinal.demo.controller.AdminController;
import com.tanlsh.jfinal.demo.controller.UserController;

public class AdminRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		add("/admin",AdminController.class);
		add("/admin/user",UserController.class);
	}

}
