package com.jfinalD.web.route;

import com.jfinal.config.Routes;
import com.jfinalD.web.controller.AdminController;
import com.jfinalD.web.controller.UserController;

public class AdminRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		add("/admin",AdminController.class);
		add("/admin/user",UserController.class);
	}

}
