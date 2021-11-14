package com.gxstnu.search.config;

import com.gxstnu.search.utils.UploadUtils;
import com.gxstnu.search.utils.Utils;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * 拦截配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 跨域解决
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8081")//允许跨域的域名，可以用*表示允许任何域名使用
                        .allowedMethods("*")//允许任何方法（post、get等）
                        .allowedHeaders("*") //允许任何请求头
                        .allowCredentials(true) //带上cookie信息
                        .exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600L); //maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果
            }
        };
    }

    /**
     * 静态图片映射url
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ApplicationHome applicationHome = new ApplicationHome(getClass());
        File source = applicationHome.getSource();
        String targetPath = source.getParentFile().toString();
        registry.addResourceHandler("/uploadImg/**")
                .addResourceLocations("classpath:/static/uploadImg/");
    }
}
