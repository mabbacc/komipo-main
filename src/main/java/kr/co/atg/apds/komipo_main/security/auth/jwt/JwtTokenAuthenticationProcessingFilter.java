package kr.co.atg.apds.komipo_main.security.auth.jwt;

import kr.co.atg.apds.komipo_main.config.WebSecurityConfig;
import kr.co.atg.apds.komipo_main.security.auth.JwtAuthenticationToken;
import kr.co.atg.apds.komipo_main.security.auth.jwt.extractor.TokenExtractor;
// import kr.co.atg.apds.komipo_main.security.exceptions.URLNotFoundException;
import kr.co.atg.apds.komipo_main.security.jwt.RawAccessJwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
// import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationFailureHandler failureHandler;
    private final TokenExtractor tokenExtractor;

    @Autowired
    public JwtTokenAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler,
            TokenExtractor tokenExtractor, RequestMatcher matcher) {
        super(matcher);
        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, ServletException {
        log.info("attempAuthentication [" + request.getMethod() + ":" + request.getRequestURI() + "]");

        // if(request instanceof ServletRequestWrapper) // URL Not found not filtered
        // ANTMatchers
        // {
        // var dispatcherType = request.getDispatcherType();
        // if(request.getDispatcherType().equals(javax.servlet.DispatcherType.ERROR))
        // throw new URLNotFoundException("Do not have Proper RestController");
        // }

        String tokenPayload = request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM);
        RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));
        log.info("token [" + token.getToken() + "]");
        if (token.getToken().isEmpty())
            throw new ServletException("Token is Empty.");
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
