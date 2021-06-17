package com.briup.smart.env.util;

import com.briup.smart.env.support.PropertiesAware;

import java.io.*;
import java.util.Properties;

/**
 * 备份模块
 */
public class BackupImpl implements Backup, PropertiesAware {

    private String filePath;

    @Override
    public Object load(String fileName, boolean del) throws Exception {
        Object obj = null;
        ObjectInputStream in = null;

        try {
            File file = new File(filePath, fileName);
            if (!file.exists()) {
                return null;
            }

            in = new ObjectInputStream(new FileInputStream(file));
            obj = in.readObject();

            if (del) {
                file.deleteOnExit();
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return obj;
    }

    @Override
    public void store(String fileName, Object obj, boolean append) throws Exception {

        ObjectOutputStream out = null;
        try {
            File file = new File(filePath, fileName);

            File mkdir = new File(filePath);
            if (!mkdir.exists()) {
                file.mkdirs();
            }

            out = new ObjectOutputStream(new FileOutputStream(file, append));
            out.writeObject(obj);
            out.flush();
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    @Override
    public void init(Properties properties) throws Exception {
        filePath = properties.getProperty("filePath");
    }
}
