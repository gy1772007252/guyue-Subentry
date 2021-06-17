package com.briup.smart.env.client;

import com.briup.smart.env.Configuration;
import com.briup.smart.env.entity.Environment;
import com.briup.smart.env.support.ConfigurationAware;
import com.briup.smart.env.support.PropertiesAware;
import com.briup.smart.env.util.Backup;
import com.briup.smart.env.util.Log;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 * 采集模块
 */
public class GatherImpl implements Gather, PropertiesAware, ConfigurationAware {

    private static String dataFilePath;
    private static String fileName = "data_len-file";
    private Configuration configuration;

    private String name = GatherImpl.class.getSimpleName();

    @Override
    public Collection<Environment> gather() throws Exception {

        Log log = configuration.getLogger();
        Backup backup = configuration.getBackup();

        Collection<Environment> collection = new ArrayList<>();

        File file = new File("env-gather-impl\\src\\main\\resources\\" + dataFilePath);
        if (!file.exists()) {
            log.error(name + "采集文件不存在！");
            return null;
        }
        long length = file.length();

        BufferedReader in = new BufferedReader(new FileReader(file));

        log.info(name + "读取备份文件");
        Object load = backup.load(fileName, Backup.LOAD_UNREMOVE);
        if (load != null) {
            in.skip((Long) load);
            in.readLine();
        }

        log.info(name + " 信息读取开始");
        String str = null;
        while ((str = in.readLine()) != null) {
            if (str == null) {
                break;
            }
            String[] strs = str.split("[|]");
            String name = null;
            float data = 0f;
            switch (strs[3]) {
                case "16":
                    name = "温度";
                    data = Integer.valueOf(strs[6].substring(0, 4), 16);
                    data = (data * (0.00268127F)) - 46.85F;
                    addCollection(collection, strs, name, data);

                    name = "湿度";
                    data = Integer.valueOf(strs[6].substring(4, 8), 16);
                    data = (data * 0.00190735F) - 6;
                    addCollection(collection, strs, name, data);
                    break;
                case "256":
                    name = "光照强度";
                    data = Integer.valueOf(strs[6].substring(0, 4), 16);
                    addCollection(collection, strs, name, data);
                    break;
                case "1280":
                    name = "二氧化碳";
                    data = Integer.valueOf(strs[6].substring(0, 4), 16);
                    addCollection(collection, strs, name, data);
                    break;
                default:
                    System.out.println("无");
                    break;
            }
        }

        log.info(name + " 信息读取结束，共读取" + collection.size() + "条数据");
        log.info(name + " 备份开始");
        backup.store(fileName, length, Backup.STORE_APPEND);
        log.info(name + " 备份结束");

        return collection;
    }

    private void addCollection(Collection<Environment> collection, String[] strs, String name, float data) throws Exception{
        collection.add(new Environment(name, strs[0], strs[1], strs[2], strs[3], Integer.parseInt(strs[4]),
                strs[5], Integer.parseInt(strs[7]), data, new Timestamp(Long.parseLong(strs[8]))));
    }

    @Override
    public void init(Properties properties) throws Exception {
        dataFilePath = properties.getProperty("dataFilePath");
    }

    @Override
    public void setConfiguration(Configuration configuration) throws Exception {
        this.configuration = configuration;
    }
}