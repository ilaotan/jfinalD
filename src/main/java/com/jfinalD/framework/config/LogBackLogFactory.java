package com.jfinalD.framework.config;

import com.jfinal.log.ILogFactory;
import com.jfinal.log.Log;

/**
 * Created by tan on 2017/5/9.
 */
public class LogBackLogFactory implements ILogFactory {

    public Log getLog(Class<?> clazz) {
        return new JLogBack(clazz);
    }

    public Log getLog(String name) {
        return new JLogBack(name);
    }
}