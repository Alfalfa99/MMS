package Echoiot.alfalfa.MMS.config;


import Echoiot.alfalfa.MMS.interceptor.AdminInterceptor;
import Echoiot.alfalfa.MMS.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 苜蓿
 * @date 2020/9/13
 * 拦截器配置类
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    private final TokenInterceptor tokenInterceptor;
    private final AdminInterceptor adminInterceptor;

    public InterceptorConfig(AdminInterceptor adminInterceptor, TokenInterceptor tokenInterceptor) {
        this.adminInterceptor = adminInterceptor;
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //拦截所有目录，除了通向login和register的接口
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login/**", "/**/register/**", "/**/verifyCode/**")
                .excludePathPatterns("/**/swagger-ui.html/**","/**/*.html", "/**/*.js", "/**/*.css");

        //拦截前往Admin接口的所有请求，对请求进行处理
        registry.addInterceptor(adminInterceptor).addPathPatterns("/**/admin/**").excludePathPatterns("/**/findAllUser/**");
        //拦截前往TaskController接口的所有请求，对请求进行处理
        registry.addInterceptor(adminInterceptor).addPathPatterns("/**/task/**");
    }

    /**
     * 打开前往swagger的接口
     * @param registry 注册
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
