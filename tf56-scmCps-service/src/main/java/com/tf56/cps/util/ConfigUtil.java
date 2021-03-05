package com.tf56.cps.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 读取配置文件工具类
 * <功能详细描述>
 * 
 * @author  Dudc
 * @version  [v.0.0.1, 2017年10月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ConfigUtil {

//    private static final Log log = LogFactory.getLog(ServerConfig.class);
    private static Properties config = new Properties();
    private static ClassLoader CL = ConfigUtil.class.getClassLoader();
    
    public static void loadPropertyFile(String filePath) {
    	InputStream in = null;
        try {
            if (CL != null) {
                in = CL.getResourceAsStream(filePath);
            }else {
                in = ClassLoader.getSystemResourceAsStream(filePath);
            }
            config.load(in);
            in.close();
        } catch (FileNotFoundException e) {
        //    log.warn("服务器配置文件没有找到");
            System.out.println("服务器配置文件没有找到");
        } catch (Exception e) {
        //    log.warn("服务器配置信息读取错误");
        	e.printStackTrace();
            System.out.println("服务器配置信息读取错误");
        } finally {
			try {
				if (null != in)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    public static String getValue(String filePath, String key) {
    	loadPropertyFile(filePath);
        if (config.containsKey(key)) {
            String value = config.getProperty(key);
            return value;
        }else {
            return "";
        }
    }
    
    public static int getValueInt(String filePath, String key) {
        String value = getValue(filePath, key);
        int valueInt = 0;
        try {
            valueInt = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return valueInt;
        }
        return valueInt;
    }
    
    public static void main(String[] args) {
		System.out.println(ConfigUtil.getValue("config/omsapi.properties", "OMS_DELIVER_URL"));
	}
}