package kr.co.atg.apds.komipo_main.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.atg.apds.komipo_main.security.RestAuthenticationEntryPoint;
import kr.co.atg.apds.komipo_main.security.auth.ajax.AjaxAuthenticationProvider;
import kr.co.atg.apds.komipo_main.security.auth.ajax.AjaxLoginProcessingFilter;
import kr.co.atg.apds.komipo_main.security.auth.ajax.LoginPathRequestMatcher;
import kr.co.atg.apds.komipo_main.security.auth.jwt.JwtAuthenticationProvider;
import kr.co.atg.apds.komipo_main.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import kr.co.atg.apds.komipo_main.security.auth.jwt.SkipPathRequestMatcher;
import kr.co.atg.apds.komipo_main.security.auth.jwt.extractor.TokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/api/auth/login";
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/**";
    public static final String TOKEN_REFRESH_ENTRY_POINT = "/api/auth/token";
    // public static final String WEB_FRONT = "/**/*.html";

    public static final String APDS_A01_SIGNIN = "/api/v1/auth/A01-SignInAuth";
    public static final String APDS_A02_REFRESH = "/api/v1/auth/A02-RefreshToken";

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // Need {bcrypt}, {noop}, etc...
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // @Override
    // public void configure(WebSecurity web){
    // web.ignoring().antMatchers("/", "/**/*.ico", "/**/*.atg", "/**/*.html",
    // "/css/**", "/js/**", "/media/**", "/img/**", "/webfonts/**");
    // }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)

                .and()
                .authorizeRequests()

                // CORS
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                // .antMatchers("/**/*.html").permitAll()
                // .antMatchers("/img/**/*").permitAll()
                // .antMatchers("/js/**/*").permitAll()
                // .antMatchers("/css/**/*").permitAll()
                .antMatchers("/json/**/*.json").permitAll()
                .antMatchers("/3d/**/*").permitAll()

                .antMatchers("/authenticate").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                // .antMatchers("/api/v1/faqs").permitAll()
                // .antMatchers("/api/v1/goods/categories").permitAll()
                // .antMatchers("/api/v1/user").permitAll()
                // .antMatchers("/api/v1/qnas").permitAll()
                // .antMatchers("/api/v1/user-all").permitAll()
                // .antMatchers(HttpMethod.GET, "/api/v1/goods/image").permitAll()
                // .antMatchers("/goods/categories").permitAll()
                .antMatchers("/api/v1/front/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                ////// For Swagger UI ///////
                /////////////////////////////////////
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/configuration/security").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/csrf").permitAll()
                ////// For Swagger UI End ////////
                /////////////////////////////////////
                .and()
                .authorizeRequests()
                .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated()
                .and()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }

    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {
        List<String> pathsToSignin = Arrays.asList(
                FORM_BASED_LOGIN_ENTRY_POINT,
                APDS_A01_SIGNIN);

        LoginPathRequestMatcher matcher = new LoginPathRequestMatcher(pathsToSignin);

        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(
                successHandler,
                failureHandler,
                matcher,
                objectMapper);

        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = Arrays.asList(
                FORM_BASED_LOGIN_ENTRY_POINT, TOKEN_REFRESH_ENTRY_POINT, APDS_A02_REFRESH
                // , WEB_FRONT
                // , "/api/v1/faqs"
                // , "/api/v1/goods/categories"
                // , "/api/v1/user"
                // , "/api/v1/qnas"
                // , "/api/v1/user-all"
                // , "/api/v1/goods/image"
                // , "/goods/categories"
                , "/api/v1/front/**", "/json/**/*.json", "/3d/**/*"

                /* For Swagger API Documentation */
                , "/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security",
                "/swagger-ui.html", "/webjars/**", "/csrf");

        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);

        JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(
                failureHandler,
                tokenExtractor,
                matcher);

        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // - (3)
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ajaxAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }
}
