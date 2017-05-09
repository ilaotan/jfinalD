package com.jfinalD.framework.config;

import com.jfinal.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

/**
 * Created by tan on 2017/5/9.
 */
public class JLogBack extends Log {


    private Logger log;

    private static final String callerFQCN = JLogBack.class.getName();

    JLogBack(Class<?> clazz) {
        log = LoggerFactory.getLogger(clazz);
    }

    JLogBack(String name) {
        log = LoggerFactory.getLogger(name);
    }

    public static JLogBack getLog(Class<?> clazz) {
        return new JLogBack(clazz);
    }

    public static JLogBack getLog(String name) {
        return new JLogBack(name);
    }

    public void info(String message) {
        log.info(callerFQCN, Level.INFO, message, null);
    }

    public void info(String message, Throwable t) {
        log.info(callerFQCN, Level.INFO, message, t);
    }

    public void debug(String message) {
        log.debug(callerFQCN, Level.DEBUG, message, null);
    }

    public void debug(String message, Throwable t) {
        log.debug(callerFQCN, Level.DEBUG, message, t);
    }

    public void warn(String message) {
        log.warn(callerFQCN, Level.WARN, message, null);
    }

    public void warn(String message, Throwable t) {
        log.warn(callerFQCN, Level.WARN, message, t);
    }

    public void error(String message) {
        log.error(callerFQCN, Level.ERROR, message, null);
    }

    public void error(String message, Throwable t) {
        log.error(callerFQCN, Level.ERROR, message, t);
    }

    public void fatal(String message) {
        log.trace(callerFQCN, Level.TRACE, message, null);
    }

    public void fatal(String message, Throwable t) {
        log.trace(callerFQCN, Level.TRACE, message, t);
    }

    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    public boolean isFatalEnabled() {
        return log.isTraceEnabled();
    }

}
