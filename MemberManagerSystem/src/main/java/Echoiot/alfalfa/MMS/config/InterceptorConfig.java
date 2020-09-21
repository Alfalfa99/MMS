package Echoiot.alfalfa.MMS.config;

import Echoiot.alfalfa.MMS.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 苜蓿
 * @date 2020/9/13
 * 拦截器配置类
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    private final TokenInterceptor tokenInterceptor;

    public InterceptorConfig(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //拦截所有目录，除了通向login和register的接口
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login/**", "/**/register/**", "/**/verifyCode/**")
                .excludePathPatterns("/**/*.html", "/**/*.js", "/**/*.css");
    }

}
