package com.google.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author iris
 */
@Configuration
@MapperScan("com.google.modules.**.mapper")
public class MybatisPlusConfig {

}
