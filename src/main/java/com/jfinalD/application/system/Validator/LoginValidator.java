package com.jfinalD.application.system.Validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.jfinalD.application.system.model.User;
import com.jfinalD.framework.config.Constants;


public class LoginValidator extends Validator {

    @Override
    protected void validate(Controller c) {
        validateRequired(Constants.USERNAME, "error", "用户名不能为空");
        validateRequired(Constants.PASSWORD, "error", "密码不能为空");
        validateRequired("captcha", "error", "验证码不能为空");
    }

    @Override
    protected void handleError(Controller c) {
        User user = c.getModel(User.class);
        c.setAttr("user", user);
        c.render("/ftl/front/login.html");
    }

}
