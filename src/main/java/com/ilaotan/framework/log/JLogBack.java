package com.ilaotan.framework.log;

import com.jfinal.log.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tan on 2017/5/9.
 */
public class JLogBack extends Log {


    private Logger log;

    JLogBack(Class<?> clazz) {
        log = LoggerFactory.getLogger(clazz);
    }

    JLogBack(String name) {
        log = LoggerFactory.getLogger(name);
    }

    @Override
    public void info(String message) {
        this.log.info(message);
    }

    @Override
    public void info(String message, Throwable t) {
        this.log.info(message, t);
    }

    @Override
    public void debug(String message) {
        this.log.debug(message);
    }

    @Override
    public void debug(String message, Throwable t) {
        this.log.debug(message, t);
    }

    @Override
    public void warn(String message) {
        this.log.warn(message);
    }

    @Override
    public void warn(String message, Throwable t) {
        this.log.warn(message, t);
    }

    @Override
    public void error(String message) {
        this.log.error(message);
    }

    @Override
    public void error(String message, Throwable t) {
        this.log.error(message, t);
    }

    @Override
    public void fatal(String message) {
        this.log.error(message);
    }

    @Override
    public void fatal(String message, Throwable t) {
        this.log.error(message, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return this.log.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return this.log.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return this.log.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return this.log.isErrorEnabled();
    }

    @Override
    public boolean isFatalEnabled() {
        return this.log.isErrorEnabled();
    }
}
