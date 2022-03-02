package kr.co.atg.apds.komipo_main.config;

import kr.co.atg.apds.komipo_main.fend.interceptor.RequestRoleAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private RequestRoleAuthenticationInterceptor requestRoleAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // Register Interceptor ( For Checking URL is "/api/v1/admin/**" )
        registry.addInterceptor(requestRoleAuthenticationInterceptor)
                .addPathPatterns("/api/v1/admin/**")
                .excludePathPatterns("/api/v1/admin/authority/t_user");
    }
}
