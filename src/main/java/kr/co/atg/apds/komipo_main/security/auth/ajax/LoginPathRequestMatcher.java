package kr.co.atg.apds.komipo_main.security.auth.ajax;

import io.jsonwebtoken.lang.Assert;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class LoginPathRequestMatcher implements RequestMatcher {
    private OrRequestMatcher matchers;

    public LoginPathRequestMatcher(List<String> pathsToLogin) {
        Assert.notNull(pathsToLogin);
        List<RequestMatcher> m = pathsToLogin.stream().map(path -> new AntPathRequestMatcher(path)).collect(Collectors.toList());
        matchers = new OrRequestMatcher(m);
    }

    @Override
    public boolean matches(HttpServletRequest request) {

        System.out.println("#### matches");
        System.out.println(request.getMethod());

        if("OPTIONS".equals(request.getMethod())) return false;

        if (matchers.matches(request)) {
            return true;
        } else {
            return false;
        }
    }
}
