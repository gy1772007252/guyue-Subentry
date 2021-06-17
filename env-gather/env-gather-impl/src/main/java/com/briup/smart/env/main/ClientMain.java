package com.briup.smart.env.main;

import com.briup.smart.env.ConfigurationImpl;
import com.briup.smart.env.client.Client;
import com.briup.smart.env.util.Log;

//客户端入口类
public class ClientMain {
	public static void main(String[] args) {
		String name = ClientMain.class.getSimpleName();

		ConfigurationImpl configuration = ConfigurationImpl.getConfiguration();
		Log logger = null;
		try {
			logger = configuration.getLogger();
			Client client = configuration.getClient();
			client.send(configuration.getGather().gather());
		} catch (Exception e) {
			logger.error(name + " " + e.getMessage());
		}
	}
}
