package kr.co.atg.apds.komipo_main.security.auth.jwt.verifier;

public interface TokenVerifier {
    public boolean verify(String jti);
}
