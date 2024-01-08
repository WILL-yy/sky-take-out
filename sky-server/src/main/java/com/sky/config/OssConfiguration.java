package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类：用于创建ALiOSSUtil对象
 * 配置ALiOSSUtil工具类的属性
 */
@Configuration
@Slf4j
public class OssConfiguration {
    /**
     * @Bean 注解
     * 假设不加Bean注解,只写了一个方法，该方法不会生效，方法不会被调用到
     * 加入Bean注解后，项目启动后，方法就会被调用，这时ALiOSSUtil工具类对象就会被创建出来
     * 工具类对象被创建出来后就交给Spring容器去管理
     *
     * @ConditionalOnMissingBean 保证整个Spring容器里只有一个ALiOSSUtil对象
     *
     */
    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){
        log.info("开始创建阿里云OSS上传文件工具类对象... ...");
        return  new AliOssUtil(aliOssProperties.getEndpoint(), aliOssProperties.getAccessKeyId(), aliOssProperties.getAccessKeySecret(), aliOssProperties.getBucketName());
    }
}
