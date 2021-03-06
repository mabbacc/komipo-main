package kr.co.atg.apds.komipo_main.security.auth.ajax;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.atg.apds.komipo_main.common.ErrorCode;
import kr.co.atg.apds.komipo_main.common.ErrorResponse;
import kr.co.atg.apds.komipo_main.security.exceptions.AuthMethodNotSupportedException;
import kr.co.atg.apds.komipo_main.security.exceptions.JwtExpiredTokenException;
import kr.co.atg.apds.komipo_main.security.exceptions.URLNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private final ObjectMapper mapper;

	@Autowired
	public AjaxAwareAuthenticationFailureHandler(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException {

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		if (e instanceof BadCredentialsException) {
			mapper.writeValue(response.getWriter(),
					ErrorResponse.of(e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		} else if (e instanceof JwtExpiredTokenException) {
			mapper.writeValue(response.getWriter(),
					ErrorResponse.of(e.getMessage(), ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
		} else if (e instanceof AuthMethodNotSupportedException) {
			mapper.writeValue(response.getWriter(),
					ErrorResponse.of(e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		} else if (e instanceof AuthenticationServiceException) {
			mapper.writeValue(response.getWriter(),
					ErrorResponse.of(e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		} else if (e instanceof URLNotFoundException) { // URL Not Found (Authentication)
			mapper.writeValue(response.getWriter(),
					ErrorResponse.of(e.getMessage(), ErrorCode.URL_NOT_FOUND, HttpStatus.NOT_FOUND));
		}
		// } else if (e instanceof PasswordResetException){ // Password Reset Need
		// mapper.writeValue(response.getWriter(), ErrorResponse.of(e.getMessage(),
		// ErrorCode.PASSWORD_RESET, HttpStatus.UNAUTHORIZED));
		// }

		// mapper.writeValue(response.getWriter(), ErrorResponse.of("Authentication
		// failed", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
	}
}
