package kr.co.atg.apds.komipo_main.security.jwt;

import kr.co.atg.apds.komipo_main.fend.memorybean.UserAuthorityObjectComponent;
import kr.co.atg.apds.komipo_main.security.config.JwtSettings;
import kr.co.atg.apds.komipo_main.security.model.T_user;
import kr.co.atg.apds.komipo_main.security.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

@Component
public class JwtTokenFactory {
    private final JwtSettings settings;
    private final UserAuthorityObjectComponent userAuthorityObjectComponent;

    @Autowired
    public JwtTokenFactory(
            JwtSettings settings,
            UserAuthorityObjectComponent userAuthorityObjectComponent) {
        this.settings = settings;
        this.userAuthorityObjectComponent = userAuthorityObjectComponent;
    }

    /**
     * @author : hk
     * @date : 2017. 3. 22.
     * @description : 사용자 JWT 토큰 생성 - Firebase SDK 사용
     * @param userContext
     * @return
     */
    public AccessJwtToken createAccessJwtToken(UserContext userContext, T_user user) {
        if (ObjectUtils.isEmpty(userContext.getUser_name()))
            throw new IllegalArgumentException("Cannot create JWT Token without username");

        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty())
            throw new IllegalArgumentException("User doesn't have any privileges");

        // 토큰 생성부분 firebase로 변경
        Claims claims = Jwts.claims().setSubject(userContext.getIdx().toString());
        // claims.put("scopes", userContext.getAuthorities().stream().map(s ->
        // s.toString()).collect(Collectors.toList()));

        claims.put("id", user.getId());
        claims.put("user_id", user.getUser_id());
        claims.put("user_nm", user.getUser_nm());
        claims.put("email", user.getEmail());
        claims.put("phone_number", user.getPhone_number());
        claims.put("user_status", user.getUser_status());
        claims.put("authorities", Arrays.asList(user.getAuthorities()));
        claims.put("scope", user.getScope());
        claims.put("description", user.getDescription());

        claims.put("functions", userAuthorityObjectComponent.getUserFunctionList(user.getId().intValue()));

        // Set User Context
        userContext.setFunctions(userAuthorityObjectComponent.getUserFunctionList(user.getId().intValue()));

        LocalDateTime now = LocalDateTime.now();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(
                        now.plusMinutes(settings.getTokenExpirationTime()).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .setSubject(user.getId().toString())
                .setAudience(settings.getTokenAud())
                .compact();

        return new AccessJwtToken(token);
    }

    /**
     * @author : hk
     * @date : 2017. 3. 22.
     * @description : 리프레시 토큰 생성. firebase 사용하지 않음
     * @param userContext
     * @return
     */
    public JwtToken createRefreshToken(UserContext userContext, T_user user) {
        if (ObjectUtils.isEmpty(userContext.getUser_name())) {
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        }

        Claims claims = Jwts.claims().setSubject(userContext.getIdx().toString());
        // claims.put("scopes", Arrays.asList(Scopes.REFRESH_TOKEN.authority()));
        claims.put("id", user.getId());
        claims.put("user_id", user.getUser_id());
        claims.put("user_nm", user.getUser_nm());
        claims.put("email", user.getEmail());
        claims.put("phone_number", user.getPhone_number());
        claims.put("user_status", user.getUser_status());
        claims.put("authorities", Arrays.asList(user.getAuthorities()));
        claims.put("scope", user.getScope());
        claims.put("description", user.getDescription());

        claims.put("functions", userAuthorityObjectComponent.getUserFunctionList(user.getId().intValue()));

        // Set User Context
        userContext.setFunctions(userAuthorityObjectComponent.getUserFunctionList(user.getId().intValue()));

        LocalDateTime now = LocalDateTime.now();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(
                        now.plusMinutes(settings.getRefreshTokenExpTime()).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .setSubject(userContext.getIdx().toString())
                .setAudience(settings.getTokenAud())
                .compact();
        /*
         * String token = Jwts.builder()
         * .setClaims(claims)
         * .setIssuer(settings.getTokenIssuer())
         * .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
         * .setExpiration(Date.from(
         * now.plusMinutes(settings.getRefreshTokenExpTime()).atZone(ZoneId.
         * systemDefault()).toInstant()))
         * .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
         * .setId(UUID.randomUUID().toString())
         * .compact();
         */

        return new AccessJwtToken(token);
    }
}
