package com.briup.smart.env;

import com.briup.smart.env.client.Client;
import com.briup.smart.env.client.Gather;
import com.briup.smart.env.server.DBStore;
import com.briup.smart.env.server.Server;
import com.briup.smart.env.support.ConfigurationAware;
import com.briup.smart.env.support.PropertiesAware;
import com.briup.smart.env.util.Backup;
import com.briup.smart.env.util.Log;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.*;

/**
 * 配置模块
 */
public class ConfigurationImpl implements Configuration {

    private static final ConfigurationImpl configuration = new ConfigurationImpl();
    public static ConfigurationImpl getConfiguration() {
        return configuration;
    }

    private Map<String, PropertiesAware> map = new HashMap<>();
    private String fileName = "D:\\corejava\\env-gather\\env-gather-impl\\src\\main\\resources\\conf.xml";

    private ConfigurationImpl() {
        try {
            Document document = new SAXReader().read(fileName);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                String name = element.getName();
                String aClass = element.attributeValue("class");

                PropertiesAware propertiesAware = (PropertiesAware) Class.forName(aClass).newInstance();
                map.put(name, propertiesAware);

                List<Element> elements1 = element.elements();
                Properties properties = new Properties();
                for (Element element1 : elements1) {
                    String name1 = element1.getName();
                    String text = element1.getText();
                    properties.setProperty(name1, text);
                }
                propertiesAware.init(properties);
            }
            for (Object o : map.values()) {
                if (o instanceof ConfigurationAware) {
                    ConfigurationAware configurationAware = (ConfigurationAware) o;
                    configurationAware.setConfiguration(this);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Log getLogger() throws Exception {
        return (Log) map.get("logger");
    }

    @Override
    public Server getServer() throws Exception {
        return (Server) map.get("server");
    }

    @Override
    public Client getClient() throws Exception {
        return (Client) map.get("client");
    }

    @Override
    public DBStore getDbStore() throws Exception {
        return (DBStore) map.get("dbStore");
    }

    @Override
    public Gather getGather() throws Exception {
        return (Gather) map.get("gather");
    }

    @Override
    public Backup getBackup() throws Exception {
        return (Backup) map.get("backup");
    }
}
