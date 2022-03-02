package kr.co.atg.apds.komipo_main.security.auth.ajax.service;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.atg.apds.komipo_main.security.model.T_admin;
import kr.co.atg.apds.komipo_main.security.model.T_user;
import kr.co.atg.apds.komipo_main.security.model.UserContext;
import kr.co.atg.apds.komipo_main.security.service.AuthService;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {
    // private static final Logger logger =
    // LoggerFactory.getLogger(LoginService.class);

    // @Autowired private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthService authService;

    public UserContext verifyAdmin(String user_id, String user_pw) throws AuthenticationException {

        // mabbacc start - 2020-03-04 [ - 회원 가입은 t_user_temp 를 이용하고, 회원 인증은 t_user 를
        // 이용함.
        T_admin t_admin = null;
        t_admin = authService.getAdminByAdminId(user_id);

        if (!passwordEncoder.matches(user_pw, t_admin.getAdmin_pw())) {
            throw new BadCredentialsException("Authentication Failed, Username or password not valid");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        String[] userAuthorities = t_admin.getAuthorities().split(",");
        for (String auth : userAuthorities) {
            authorities.add(new SimpleGrantedAuthority(auth));
        }

        UserContext uc = UserContext.create("" + t_admin.getAdmin_nm(), authorities, t_admin.getScope(),
                t_admin.getIdx(),
                t_admin.getAdmin_id(),
                t_admin.getPhone_number(),
                null);

        /*
         * RawAccessJwtToken RAJToken = new RawAccessJwtToken(token);
         * JwtAuthenticationToken at = (JwtAuthenticationToken)
         * jwtAuthenticationProvider.authenticate(new JwtAuthenticationToken(RAJToken));
         * UserContext uc = (UserContext) at.getPrincipal();
         */

        return uc;
    }

    public UserContext verifyUser(String user_id, String user_pw) throws AuthenticationException {

        // mabbacc start - 2020-03-04 [ - 회원 가입은 t_user_temp 를 이용하고, 회원 인증은 t_user 를
        // 이용함.
        T_user t_user = null;
        t_user = authService.getUserByUserId(user_id);

        // if ( !"2003".equals(t_user.getUser_status()) ) {
        // if("3100".equals(t_user.getUser_status()))
        // throw new PasswordResetException("User Password must be reset");
        // throw new BadCredentialsException("Authentication Failed, Status is not
        // valid");
        // }
        if (!passwordEncoder.matches(user_pw, t_user.getUser_pw())) {
            throw new BadCredentialsException("Authentication Failed, Username or password not valid");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        String[] userAuthorities = t_user.getAuthorities().split(",");
        for (String auth : userAuthorities) {
            authorities.add(new SimpleGrantedAuthority(auth));
        }

        UserContext uc = UserContext.create("" + t_user.getUser_nm(), authorities, t_user.getScope(),
                t_user.getId(),
                t_user.getUser_id(),
                t_user.getPhone_number(),
                t_user.getUser_status());

        /*
         * RawAccessJwtToken RAJToken = new RawAccessJwtToken(token);
         * JwtAuthenticationToken at = (JwtAuthenticationToken)
         * jwtAuthenticationProvider.authenticate(new JwtAuthenticationToken(RAJToken));
         * UserContext uc = (UserContext) at.getPrincipal();
         */

        return uc;
    }
}
