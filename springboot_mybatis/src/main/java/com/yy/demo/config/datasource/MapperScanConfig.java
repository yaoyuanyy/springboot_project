package com.yy.demo.config.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yy.demo.mapper")
public class MapperScanConfig {

//  @Bean
//  public MapperScannerConfigurer mapperScannerConfigurer() {
//      MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//      mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//      mapperScannerConfigurer.setBasePackage("com.yy.demo.mapper");
//      System.out.println("mapperScannerConfigurer");
//      return mapperScannerConfigurer;
//  }

}
