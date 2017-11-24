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
@ControllerBind(controllerKey = "/", viewPath = "/front")
public class IndexController extends Controller {

    public static Logger LOG = LoggerFactory.getLogger(IndexController.class);

    //@Before({Tx.class,CacheInterceptor.class})//EvictInterceptor.class 清除缓存
    //@CacheName("myCache")
    public void index() {

        LOG.error("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
        render("index.html");
    }

    public void faq() {

        List<Role> roleList = Role.dao.findByCache("myCache", "userALl", "select * from sec_role");

        LOG.error("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");

        setAttr("gggg", JSON.toJSONString(roleList));

        render("faq.html");
    }

    public void jsonTest() {
        renderJson("heheheheh");
    }


}
