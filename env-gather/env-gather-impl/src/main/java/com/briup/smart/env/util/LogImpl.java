package com.briup.smart.env.util;

import com.briup.smart.env.support.PropertiesAware;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * 日志模块
 */
public class LogImpl implements Log, PropertiesAware {

    Logger logger = Logger.getRootLogger();

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void fatal(String message) {
        logger.fatal(message);
    }

    @Override
    public void init(Properties properties) throws Exception {}
}
