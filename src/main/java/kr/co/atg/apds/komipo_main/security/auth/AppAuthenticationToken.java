package kr.co.atg.apds.komipo_main.security.auth;

import kr.co.atg.apds.komipo_main.security.auth.ajax.LoginRequest;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

public class AppAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    // ~ Instance Fields
    // ======================================================================================================
    private final Object principal;
    private Object credentials;
    private LoginRequest loginRequest;

    // ~ Constructors
    // ======================================================================================================

    public AppAuthenticationToken(Object principal, Object credentials, LoginRequest loginRequest) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.loginRequest = loginRequest;
        setAuthenticated(false);
    }

    public AppAuthenticationToken(Object principal, Object credentials,
                                  Collection<? extends GrantedAuthority> authorities, LoginRequest loginRequest) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.loginRequest = loginRequest;
        super.setAuthenticated(true); // must use super, as we override
    }

    public AppAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public AppAuthenticationToken(Object principal, Object credentials,
                                  Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }

    // ~ Methods
    // ======================================================================================================

    public Object getCredentials() {
        return this.credentials;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }



    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }
}
