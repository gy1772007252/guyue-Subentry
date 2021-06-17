package com.briup.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.briup")
@Import({BeanConfig.class, MyBatisConfig.class, ServiceConfig.class})
public class AllConfig {
}
