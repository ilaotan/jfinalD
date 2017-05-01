package com.jfinalD.framework;

import com.jfinal.core.JFinal;

/**
 * Created by tan on 2017/5/1.
 */
public class Application {

    /**
     * 建议使用 JFinal 手册推荐的方式启动项目
     * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     */
    public static void main(String[] args) {

        //JFinal.start("src/main/webapp", 8081, "/", 5);
        // IDEA下启动要去掉最后一个参数 否则不支持热加载
        JFinal.start("src/main/webapp", 8081, "/");
    }

}
