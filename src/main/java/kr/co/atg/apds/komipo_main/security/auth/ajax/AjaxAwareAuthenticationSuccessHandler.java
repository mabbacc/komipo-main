package kr.co.atg.apds.komipo_main.security.auth.ajax;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.atg.apds.komipo_main.config.WebSecurityConfig;
import kr.co.atg.apds.komipo_main.security.auth.AppAuthenticationToken;
import kr.co.atg.apds.komipo_main.security.jwt.JwtToken;
import kr.co.atg.apds.komipo_main.security.jwt.JwtTokenFactory;
import kr.co.atg.apds.komipo_main.security.model.T_user;
import kr.co.atg.apds.komipo_main.security.model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @project : Lego
 * @author : hk
 * @date : 2017. 1. 21.
 * @description : Ajax login 성공후 처리
 */
@Component
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper mapper;
    private final JwtTokenFactory tokenFactory;

    // @Autowired
    // private AuthService authService;

    // @Autowired
    // private WebSecurityConfig webSecurityConfig;

    private final String USER_STATUS_CANDIDATE = "2001";
    private final String USER_STATUS_READYSIGN = "2002";
    private final String USER_STATUS_NORMALUSR = "2003";
    private final String USER_STATUS_USRISLOCK = "2004";
    private final String USER_STATUS_TERMINATE = "2005";

    @Autowired
    public AjaxAwareAuthenticationSuccessHandler(final ObjectMapper mapper, final JwtTokenFactory tokenFactory) {
        this.mapper = mapper;
        this.tokenFactory = tokenFactory;
    }

    /*
     * @Value(value = "${security.firebase.auth.accountKey}")
     * private String fbAccountKeyPath;
     * 
     * @Value(value = "${security.firebase.db.name}")
     * private String fbDbName;
     */

    /**
     * @author : hk
     * @date : 2017. 1. 21.
     * @description : 로그인 성공 후 처리. JWT 토큰 발행
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        // A02 - 가입인증 ( 일반, 소셜 - 신규, 소셜 - 기가입자 )
        // A06 - 일반 로그인 인증
        // A08 - 소셜 로그인 인증

        // 인증 성공시 JWT 토큰 발행
        AppAuthenticationToken token = (AppAuthenticationToken) authentication;

        UserContext userContext = (UserContext) authentication.getPrincipal();

        // createAccessJwt 에 사용자 정보 전달
        LoginRequest loginRequest = token.getLoginRequest();
        T_user user = new T_user();
        user.setId(loginRequest.getIdx());
        user.setUser_id(loginRequest.getUser_id());
        user.setUser_nm(loginRequest.getUser_name());
        user.setPhone_number(loginRequest.getPhone_number());
        user.setUser_status(loginRequest.getUser_status());
        user.setAuthorities(loginRequest.getAuthorities());
        user.setScope(loginRequest.getScope());
        user.setEmail(loginRequest.getEmail());
        user.setDescription(loginRequest.getDescription());

        JwtToken accessToken = tokenFactory.createAccessJwtToken(userContext, user);
        JwtToken refreshToken = tokenFactory.createRefreshToken(userContext, user);

        Map<String, Object> tokenMap = null;

        if (USER_STATUS_NORMALUSR.equals(loginRequest.getUser_status())
                || "3100".equals(loginRequest.getUser_status())) {

            tokenMap = new HashMap<>();
            tokenMap.put("token", accessToken.getToken());
            tokenMap.put("refreshToken", refreshToken.getToken());
            // tokenMap.put("userInfo", loginRequest);
            tokenMap.put("userContext", userContext);
        }

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "인증에 성공했습니다.");
        responseMap.put("data", tokenMap);

        // mapper.writeValue(response.getWriter(), tokenMap);
        mapper.writeValue(response.getWriter(), responseMap);

        clearAuthenticationAttributes(request);
    }

    /**
     * Removes temporary authentication-related data which may have been stored
     * in the session during the authentication process..
     * 
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null)
            return;

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
