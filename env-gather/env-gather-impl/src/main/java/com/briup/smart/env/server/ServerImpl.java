package com.briup.smart.env.server;

import com.briup.smart.env.Configuration;
import com.briup.smart.env.client.GatherImpl;
import com.briup.smart.env.entity.Environment;
import com.briup.smart.env.support.ConfigurationAware;
import com.briup.smart.env.support.PropertiesAware;
import com.briup.smart.env.util.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

/**
 * 服务端
 */
public class ServerImpl implements Server, PropertiesAware, ConfigurationAware {

    private String serverPort;
    private Configuration configuration;

    private String name = GatherImpl.class.getSimpleName();

    ServerSocket server = null;
    Socket socket = null;
    boolean falg = true;

    @Override
    public void receive() throws Exception {

        server = new ServerSocket(Integer.valueOf(serverPort));
        while (falg) {
            socket = server.accept();

            socketThread socketThread = new socketThread(socket);
            socketThread.run();
        }

    }

    @Override
    public void shutdown() throws Exception {}

    @Override
    public void init(Properties properties) throws Exception {
        serverPort = properties.getProperty("serverPort");
    }

    @Override
    public void setConfiguration(Configuration configuration) throws Exception {
        this.configuration = configuration;
    }

    class socketThread extends Thread {
        private Socket socket;
        private ObjectInputStream in = null;
        public socketThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                Log log = configuration.getLogger();
                DBStore dbStore = configuration.getDbStore();

                log.info(name + " 客户端已连接");
                log.info(name + " 信息开始接收");

                in = new ObjectInputStream(socket.getInputStream());
                Collection<Environment> environments = (Collection<Environment>) in.readObject ();

                log.info(name + " 信息接收完成");

                if (environments.size() == 0) {
                    log.info(name + " 无数据更新");
                    return;
                }
                dbStore.saveDB(environments);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
