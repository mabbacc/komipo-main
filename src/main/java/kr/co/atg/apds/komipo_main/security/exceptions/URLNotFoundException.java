package kr.co.atg.apds.komipo_main.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class URLNotFoundException extends AuthenticationException {
    private static final long serialVersionUID = -3966231876505802752L;

    public URLNotFoundException(String msg){
        super(msg);
    }
}
