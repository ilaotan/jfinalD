package com.jfinalD.application.front;

import com.alibaba.fastjson.JSON;
import com.jfinal.core.Controller;
import com.jfinal.ext.plugin.route.ControllerBind;
import com.jfinalD.application.system.model.Role;

import java.util.List;

/**
 * Create by tanliansheng on 2015年10月29日
 */
@ControllerBind(controllerKey = "/", viewPath = "/front")
public class IndexController extends Controller {

    //@Before({Tx.class,CacheInterceptor.class})//EvictInterceptor.class 清除缓存
    //@CacheName("myCache")
    public void index() {

        render("index.html");
    }

    public void faq() {

        List<Role> roleList = Role.dao.findByCache("myCache","userALl","select * from sec_role");


        setAttr("gggg", JSON.toJSONString(roleList));

        render("faq.html");
    }

    public void jsonTest(){
        renderJson("heheheheh");
    }


}
