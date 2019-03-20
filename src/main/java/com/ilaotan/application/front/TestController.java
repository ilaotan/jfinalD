package com.ilaotan.application.front;

import com.alibaba.fastjson.JSON;
import com.jfinal.core.Controller;
import com.jfinal.ext.plugin.route.ControllerBind;
import com.ilaotan.application.system.model.Role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Create by tanliansheng on 2015年10月29日
 */
@ControllerBind(controllerKey = "/park", viewPath = "/front")
public class TestController extends Controller {

    public static Logger LOG = LoggerFactory.getLogger(TestController.class);


    public void token() {

        String xxx  = super.getRequest().getParameter("jdata");

        renderJson("heheheheh");
    }


}
