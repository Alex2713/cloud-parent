package com.alex.common.mysql.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MySQL 配置
 * @author Alex-2713
 */
@Slf4j
@Component
@EnableTransactionManagement
@MapperScan(basePackages = {MySqlConfig.MAPPER_PACKAGES, MySqlConfig.DAO_PACKAGES})
public class MySqlConfig {
    /**
     * BASE_MAPPER_PACKAGES
     *  Mybatis Plus 接口扫描
     */
    public static final String MAPPER_PACKAGES = "com.alex.mapper";
    /**
     * BASE_DAO_PACKAGES
     *  自定义接口扫描
     */
    public static final String DAO_PACKAGES = "com.alex.dao";
}
