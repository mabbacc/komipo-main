package kr.co.atg.apds.komipo_main.security.config;

import kr.co.atg.apds.komipo_main.security.jwt.JwtToken;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class JwtSettings {
    /**
     * {@link JwtToken} will expire after this time.
     */
    @Value(value = "${security.jwt.jwttokenExpirationTime}")
    private Integer tokenExpirationTime;

    /**
     * Token issuer.
     */
    @Value(value = "${security.jwt.jtokenSubject}")
    private String tokenSubject;

    /**
     * Token Subject
     */
    @Value(value = "${security.jwt.jtokenIssuer}")
    private String tokenIssuer;

    /**
     * Token aud
     */
    @Value(value = "${security.jwt.jtokenAud}")
    private String tokenAud;

    /**
     * Key is used to sign {@link JwtToken}.
     */
    @Value(value = "${security.jwt.jtokenSigningKey}")
    private String tokenSigningKey;

    /**
     * {@link JwtToken} can be refreshed during this timeframe.
     */
    @Value(value = "${security.jwt.jrefreshTokenExpTime}")
    private Integer refreshTokenExpTime;
}
