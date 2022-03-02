package kr.co.atg.apds.komipo_main.security.auth.ajax;

import kr.co.atg.apds.komipo_main.security.auth.AppAuthenticationToken;
import kr.co.atg.apds.komipo_main.security.auth.ajax.service.LoginService;
import kr.co.atg.apds.komipo_main.security.model.T_admin;
import kr.co.atg.apds.komipo_main.security.model.T_user;
import kr.co.atg.apds.komipo_main.security.model.UserContext;
import kr.co.atg.apds.komipo_main.security.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(AjaxAuthenticationProvider.class);

    @Autowired
    private AuthService authService;
    @Autowired
    private LoginService loginService;

    public static final String LT_USER = "user";
    public static final String LT_ADMIN = "admin";


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        AppAuthenticationToken paramToken = (AppAuthenticationToken) authentication;

        // String userID = (String) paramToken.getPrincipal();
        LoginRequest loginRequest = (LoginRequest) paramToken.getLoginRequest();

        UserContext userContext = null;

        if (null != loginRequest.getLogin_type()) {
            switch (loginRequest.getLogin_type()) {
                case LT_USER:
                    userContext = userLoginFlow(loginRequest.getUser_id(), loginRequest.getUser_pw());

                    T_user user = authService.getUserByUserId(loginRequest.getUser_id());
                    if (null != user) {
                        loginRequest.setIdx(user.getId());
                        loginRequest.setUser_id(user.getUser_id());
                        loginRequest.setUser_name(user.getUser_nm());
                        loginRequest.setPhone_number(user.getPhone_number());
                        loginRequest.setUser_status(user.getUser_status());
                        loginRequest.setAuthorities(user.getAuthorities());
                        loginRequest.setScope(user.getScope());
                        loginRequest.setEmail(user.getEmail());
                        loginRequest.setDescription(user.getDescription());
                    }
                    break;
                case LT_ADMIN:
                    userContext = adminLoginFlow(loginRequest.getUser_id(), loginRequest.getUser_pw());

                    T_admin admin = authService.getAdminByAdminId(loginRequest.getUser_id());
                    if (null != admin) {
                        loginRequest.setIdx(admin.getIdx());
                        loginRequest.setUser_id(admin.getAdmin_id());
                        loginRequest.setUser_name(admin.getAdmin_nm());
                        loginRequest.setPhone_number(admin.getPhone_number());
                        loginRequest.setUser_status(admin.getUser_status());
                        loginRequest.setAuthorities(admin.getAuthorities());
                        loginRequest.setScope(admin.getScope());
                        loginRequest.setEmail(admin.getEmail());
                        loginRequest.setDescription(admin.getDescription());
                    }
                    break;
            }
        } else {
            userContext = adminLoginFlow(loginRequest.getUser_id(), loginRequest.getUser_pw());
        }

        return new AppAuthenticationToken(userContext, null, userContext.getAuthorities(), loginRequest);
    }

    public UserContext adminLoginFlow(String user_id, String user_pw) {

        logger.info("#L ADMIN Login");
        logger.info("#L (" + user_id + ":" + user_pw + ")");

        UserContext uc = loginService.verifyAdmin(user_id, user_pw);

        logger.info("#L uc.getUsername() : " + uc.getUser_name());

        return uc;
        // 사용자 권한 가져 오기
        /*
         * List<GrantedAuthority> authorities = getAuthorities(user_id);
         * 
         * return UserContext.create(uc.getUser_name(), authorities, uc.getIdx(),
         * uc.getUser_id(), uc.getPhone_number(), uc.getUser_status());
         */
    }

    public UserContext userLoginFlow(String user_id, String user_pw) {

        logger.info("#L USER Login");
        logger.info("#L (" + user_id + ":" + user_pw + ")");

        UserContext uc = loginService.verifyUser(user_id, user_pw);

        logger.info("#L uc.getUsername() : " + uc.getUser_name());

        return uc;
        // 사용자 권한 가져 오기
        /*
         * List<GrantedAuthority> authorities = getAuthorities(user_id);
         * 
         * return UserContext.create(uc.getUser_name(), authorities, uc.getIdx(),
         * uc.getUser_id(), uc.getPhone_number(), uc.getUser_status());
         */
    }

    /**
     * @author : hk
     * @date : 2017. 1. 21.
     * @description : 사용자 권한 가져오기, 모든 로그인플로우 인증 후 호출
     * @param userID
     * @return
     */
    public List<GrantedAuthority> getAuthorities(String userID) {

        // TODO: 사용자 DB에서 권한가져오기로 변경

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (AppAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
