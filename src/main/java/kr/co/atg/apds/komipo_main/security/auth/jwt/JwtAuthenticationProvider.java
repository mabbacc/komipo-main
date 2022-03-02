package kr.co.atg.apds.komipo_main.security.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.atg.apds.komipo_main.entity.tobject.T_Function;
import kr.co.atg.apds.komipo_main.security.auth.JwtAuthenticationToken;
import kr.co.atg.apds.komipo_main.security.config.JwtSettings;
import kr.co.atg.apds.komipo_main.security.jwt.RawAccessJwtToken;
import kr.co.atg.apds.komipo_main.security.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    // private static final Logger logger =
    // LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    private final JwtSettings jwtSettings;

    @Autowired
    public JwtAuthenticationProvider(JwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    /*
     * @Autowired
     * FBUtil fBUtil;
     */

    /**
     * @author : hk
     * @date : 2017. 3. 22.
     * @description : JWT 인증, Firebase로 변경
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        String subject = jwsClaims.getBody().getSubject();
        // List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
        String scope = jwsClaims.getBody().get("scope", String.class);
        List<String> _authorities = jwsClaims.getBody().get("authorities", List.class);
        List<GrantedAuthority> authorities = _authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());

        Long user_idx = jwsClaims.getBody().get("user_idx", Long.class);
        String user_id = jwsClaims.getBody().get("user_id", String.class);
        String phone_number = jwsClaims.getBody().get("phone_number", String.class);
        String user_status = jwsClaims.getBody().get("user_status", String.class);
        var functionList = jwsClaims.getBody().get("functions", List.class); // Make UserContext Function List

        UserContext context = UserContext.create(subject, authorities, scope, user_idx, user_id, phone_number,
                user_status);

        if (functionList != null) {
            List<T_Function> userFunctionList = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            for (var functionMap : functionList) {
                if (functionMap instanceof Map) {
                    // Jackson Object DataBinding
                    T_Function eachFunction = mapper.convertValue(functionMap, T_Function.class);
                    userFunctionList.add(eachFunction);
                }
            }
            context.setFunctions(userFunctionList);
        }

        return new JwtAuthenticationToken(context, context.getAuthorities());
    }

    /* For Interceptor User Authority check */
    public UserContext makeUserContext(RawAccessJwtToken rawAccessToken) {
        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        String subject = jwsClaims.getBody().getSubject();
        String scope = jwsClaims.getBody().get("scope", String.class);
        List<String> _authorities = jwsClaims.getBody().get("authorities", List.class);
        List<GrantedAuthority> authorities = _authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());

        Long user_idx = jwsClaims.getBody().get("user_idx", Long.class);
        String user_id = jwsClaims.getBody().get("user_id", String.class);
        String phone_number = jwsClaims.getBody().get("phone_number", String.class);
        String user_status = jwsClaims.getBody().get("user_status", String.class);
        return UserContext.create(subject, authorities, scope, user_idx, user_id, phone_number, user_status);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
