package com.briup.smart.env.main;

import com.briup.smart.env.ConfigurationImpl;
import com.briup.smart.env.server.Server;
import com.briup.smart.env.util.Log;

//服务器入口类
public class ServerMain {
	public static void main(String[] args) {
		String name = ServerMain.class.getSimpleName();

		ConfigurationImpl configuration = ConfigurationImpl.getConfiguration();
		Log logger = null;
		try {
			logger = configuration.getLogger();
			Server server = configuration.getServer();
			server.receive();
		} catch (Exception e) {
			logger.error(name + " " + e.getMessage());
		}
	}
}
