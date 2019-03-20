package com.ilaotan.framework;

import com.ilaotan.framework.config.MyConfig;
import com.jfinal.core.JFinal;
import com.jfinal.server.undertow.UndertowServer;

/**
 * Created by tan on 2017/5/1.
 */
public class Application {

    /**
     * 建议使用 JFinal 手册推荐的方式启动项目
     * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     */
    public static void main(String[] args) {

        UndertowServer.start(MyConfig.class);
    }

}
