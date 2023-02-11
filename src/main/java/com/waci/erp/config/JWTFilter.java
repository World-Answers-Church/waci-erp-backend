package com.waci.erp.config;

import com.waci.erp.shared.security.HttpConstants;
import com.waci.erp.shared.security.TokenProvider;
import org.hibernate.dialect.Sybase11Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * '
 * The http request filter to authenticate and permit all incoming  http requests.
 */
@Component
public class JWTFilter extends GenericFilterBean {

    @Autowired
    TokenProvider tokenProvider;

    private static final List<String> allowedPaths = Arrays.asList(
            "/api/health",
            "/api/v1/status"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        System.out.println("Endpoint hit on >>>"+httpServletRequest.getRequestURI());
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET,PUT, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        if ("OPTIONS".equals(((HttpServletRequest) servletRequest).getMethod())) {
            httpServletResponse.setStatus(200);
            return;
        }
        if (allowedPaths.contains(httpServletRequest.getRequestURI())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            try {
               // String authorisationToken = httpServletRequest.getHeader(HttpConstants.AUTHORIZATION_HEADER);
               // String deviceId = httpServletRequest.getHeader(HttpConstants.DEVICE_ID_HEADER);
                    //validate with BO
              //  tokenProvider.validateToken(authorisationToken);

                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (Exception ex) {
                httpServletResponse.setHeader("error", ex.getMessage());
                httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value());

            }

        }
    }
}
