package kr.co.atg.apds.komipo_main.security.jwt;

public class AccessJwtToken implements JwtToken {
    private final String rawToken;

    protected AccessJwtToken(final String token) {
        this.rawToken = token;
    }

    public String getToken() {
        return this.rawToken;
    }
}
