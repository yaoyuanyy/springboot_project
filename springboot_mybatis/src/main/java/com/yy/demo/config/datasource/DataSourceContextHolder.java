package com.yy.demo.config.datasource;

/**
 * Description:
 * <p></p>
 * <pre>
 *
 *   NB.
 * </pre>
 * <p>
 * Created by skyler on 2019-03-16 at 22:45
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> dataSourceContext = new ThreadLocal<>();

    /**
     * 默认数据源
     */
    public static final String DEFAULT_DS = "master";


    public static String getDB(){
        return dataSourceContext.get();
    }

    public static void set(String value){
        dataSourceContext.set(value);
    }

    public static void clear(){
        dataSourceContext.remove();
    }

}
