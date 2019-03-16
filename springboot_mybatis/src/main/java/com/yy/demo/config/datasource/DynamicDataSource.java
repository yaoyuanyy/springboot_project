package com.yy.demo.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Description:
 * <p></p>
 * <pre>
 *
 *   NB.
 * </pre>
 * <p>
 * Created by skyler on 2019-03-16 at 22:50
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        log.info("数据源为:{}", DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }
}
