package com.fan.smsrt.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

/**
 * @author AlgerFan
 * @date Created in 2018/12/14 08
 * @Description
 */
@Configuration
public class UploadFileConfig {

    /**
     * 文件上传配置
     */
    @Bean
    MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("10240KB");
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
}
