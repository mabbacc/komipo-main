package kr.co.atg.apds.komipo_main.fend.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.co.atg.apds.komipo_main.common.constant.Constants;
import kr.co.atg.apds.komipo_main.config.WebSecurityConfig;
import kr.co.atg.apds.komipo_main.entity.tobject.T_Function_Api;
import kr.co.atg.apds.komipo_main.fend.memorybean.UserAuthorityObjectComponent;
import kr.co.atg.apds.komipo_main.security.auth.jwt.JwtAuthenticationProvider;
import kr.co.atg.apds.komipo_main.security.auth.jwt.extractor.TokenExtractor;
import kr.co.atg.apds.komipo_main.security.jwt.RawAccessJwtToken;
import kr.co.atg.apds.komipo_main.security.model.UserContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/***
 * @author sqcomp
 * @description Check authority who a user wanted to use APIs
 */
@Slf4j
@Component
public class RequestRoleAuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private UserAuthorityObjectComponent userAuthorityObjectComponent;

    /* Check User Authority for Requests */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("==================================== Request Intercepted ===================================");
        log.info("== URL: " + request.getRequestURI());


        String token = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));
        UserContext uc = jwtAuthenticationProvider.makeUserContext(new RawAccessJwtToken(token));


        /* 1. Get Authority Map and JWT Token User Authority */
        Map<String, List<T_Function_Api>> authorityApiMap = this.userAuthorityObjectComponent.getAuthorityApiMappingMap();
        var authorities = uc.getAuthorities();

        String reqUri    = request.getRequestURI();
        String reqMethod = request.getMethod();

        /* 2. Specific case (ADMIN Pass authority path) */
        for(var auth : authorities)
        {
            if(auth.getAuthority().equalsIgnoreCase(Constants.AUTH_ROLE_STRING))
            {
                return true;
            }
        }

        /* 3. Check Authority : APIs */
        for(var auth : authorities)
        {
            List<T_Function_Api> grantedApis = authorityApiMap.get(auth.getAuthority());

            for(T_Function_Api grantApi : grantedApis)
            {
                if(grantApi.getMethod().equalsIgnoreCase(reqMethod)
                    && _compareUrl(reqUri, grantApi.getUri())) {
                    return true;
                }
            }
        }

        //response.sendRedirect("/error");
        response.sendError(HttpServletResponse.SC_FORBIDDEN, Constants.ACCESS_FORBIDDEN);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /* Private Method */
    private boolean _compareUrl(String requestUrl, String databaseUrl)
    {
        StringTokenizer requestUrlSplitToken  = new StringTokenizer(requestUrl, "/");
        StringTokenizer databaseUrlSplitToken = new StringTokenizer(databaseUrl, "/");

        if (requestUrlSplitToken.countTokens() != databaseUrlSplitToken.countTokens())
            return false;

        while (databaseUrlSplitToken.hasMoreTokens()) {
            String dbUrlToken = databaseUrlSplitToken.nextToken();
            String reqUrlToken = requestUrlSplitToken.nextToken();
            if(!dbUrlToken.startsWith("{")
                && !dbUrlToken.equals(reqUrlToken)){
                return false;
            }
        }
        return true;
    }
}
