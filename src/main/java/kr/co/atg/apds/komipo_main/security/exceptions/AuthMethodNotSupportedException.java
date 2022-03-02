package kr.co.atg.apds.komipo_main.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthMethodNotSupportedException extends AuthenticationException {
    private static final long serialVersionUID = -493149327506243L;

    public AuthMethodNotSupportedException(String msg) {
        super(msg);
    }
}
