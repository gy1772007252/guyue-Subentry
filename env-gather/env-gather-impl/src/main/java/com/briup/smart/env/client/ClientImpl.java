package com.briup.smart.env.client;

import com.briup.smart.env.Configuration;
import com.briup.smart.env.entity.Environment;
import com.briup.smart.env.support.ConfigurationAware;
import com.briup.smart.env.support.PropertiesAware;
import com.briup.smart.env.util.Log;

import java.io.*;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

/**
 * 客户端
 */
public class ClientImpl implements Client, PropertiesAware, ConfigurationAware {

    private String host;
    private String port;
    private Configuration configuration;

    private String name = ClientImpl.class.getSimpleName();

    Socket socket = null;
    ObjectOutputStream out = null;

    @Override
    public void send(Collection<Environment> c) throws Exception {

        Log log = configuration.getLogger();
        if (c == null) {
            return;
        }

        socket = new Socket(host, Integer.valueOf(port));

        log.info(name + " 服务器已连接");
        log.info(name + " 信息开始传送");

        out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(c);
        out.flush();

        log.info(name + " 信息传送完成");

        shutdown();

    }

    @Override
    public void shutdown() throws Exception {

        if (out != null) {
            out.close();
        }
        if (socket != null) {
            socket.close();
        }

    }

    @Override
    public void init(Properties properties) throws Exception {
        host = properties.getProperty("host");
        port = properties.getProperty("port");
    }

    @Override
    public void setConfiguration(Configuration configuration) throws Exception {
        this.configuration = configuration;
    }
}
