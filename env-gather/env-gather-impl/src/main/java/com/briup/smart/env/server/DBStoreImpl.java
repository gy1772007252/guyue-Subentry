package com.briup.smart.env.server;

import com.briup.smart.env.Configuration;
import com.briup.smart.env.client.GatherImpl;
import com.briup.smart.env.entity.Environment;
import com.briup.smart.env.support.ConfigurationAware;
import com.briup.smart.env.support.PropertiesAware;
import com.briup.smart.env.util.Log;

import java.sql.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.Properties;

/**
 * 入库模块
 */
public class DBStoreImpl implements DBStore, PropertiesAware, ConfigurationAware {

    private String driver;
    private String url;
    private String username;
    private String password;
    private Configuration configuration;

    private String name = GatherImpl.class.getSimpleName();

    @Override
    public void saveDB(Collection<Environment> c) throws Exception {

        Log log = configuration.getLogger();

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        log.info(name + " " + connection + " 数据库已连接");

        int i = 0;
        int day = 0;
        int temp = 0;
        PreparedStatement preparedStatement = null;
        Calendar calendar = Calendar.getInstance();

        log.info(name + " 数据开始入库");

        for (Environment environment : c) {
            calendar.setTimeInMillis(environment.getGather_date().getTime());
            day = calendar.get(Calendar.DAY_OF_MONTH);
            if (day != temp) {
                if (i != 0) {
                    preparedStatement.executeBatch();
                }
                String sql = "insert into e_detail_" + day +
                        " values(?,?,?,?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
            }
            preparedStatement.setString(1, environment.getName());
            preparedStatement.setString(2, environment.getSrcId());
            preparedStatement.setString(3, environment.getDesId());
            preparedStatement.setString(4, environment.getDevId());
            preparedStatement.setString(5, environment.getSersorAddress());
            preparedStatement.setInt(6, environment.getCount());
            preparedStatement.setString(7, environment.getCmd());
            preparedStatement.setInt(8, environment.getStatus());
            preparedStatement.setFloat(9, environment.getData());
            preparedStatement.setTimestamp(10, environment.getGather_date());
            preparedStatement.addBatch();
            temp = day;
            i++;
            if (i % 100 == 0) {
                preparedStatement.executeBatch();
            }
        }
        preparedStatement.executeBatch();

        log.info(name + " 数据入库完成，共入库" + i + "条数据");

    }

    @Override
    public void init(Properties properties) throws Exception {
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    @Override
    public void setConfiguration(Configuration configuration) throws Exception {
        this.configuration = configuration;
    }
}
