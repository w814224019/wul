package com.select.wuliu.shiro;

import javax.servlet.MultipartConfigElement;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 配置图片存放路径
 * @author Admin
 *
 */

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Value("${cbs.imagesPath}")
    private String mImagesPath;
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        /// 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(10, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(mImagesPath.equals("") || mImagesPath.equals("${cbs.imagesPath}")){
               String imagesPath = WebAppConfig.class.getClassLoader().getResource("").getPath();
               if(imagesPath.indexOf(".jar")>0){
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
               }else if(imagesPath.indexOf("classes")>0){
                imagesPath = "file:"+imagesPath.substring(0, imagesPath.indexOf("classes"));
               }
               imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/"))+"/img/";
               mImagesPath = imagesPath;
              }
              LoggerFactory.getLogger(WebAppConfig.class).info("imagesPath="+mImagesPath);
              registry.addResourceHandler("/img/**").addResourceLocations(mImagesPath);
    }
}