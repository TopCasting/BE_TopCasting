package com.ll.topcastingbe.global.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.topcastingbe.global.exception.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthorizationExceptionFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (InvalidCredentialsException e) {
            handleException((HttpServletResponse) servletResponse, e);
        }
    }

    private void handleException(HttpServletResponse response, Exception e) throws IOException{
        ErrorResponse errorResponse = ErrorResponse.builder()
                                              .result(false)
                                              .code("401")
                                              .message(e.getMessage())
                                              .build();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }
}
