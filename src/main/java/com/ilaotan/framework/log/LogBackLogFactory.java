package com.ilaotan.framework.log;

import com.jfinal.log.ILogFactory;
import com.jfinal.log.Log;

/**
 * Created by tan on 2017/5/9.
 */
public class LogBackLogFactory implements ILogFactory {

    @Override
    public Log getLog(Class<?> clazz) {
        return new JLogBack(clazz);
    }

    @Override
    public Log getLog(String name) {
        return new JLogBack(name);
    }
}