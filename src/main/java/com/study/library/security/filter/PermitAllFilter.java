package com.study.library.security.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class PermitAllFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        List<String> antMatchers = List.of("/login", "/error", "/server", "/auth", "/oauth2", "/mail/authenticate");//필터에서 거름

        String uri = request.getRequestURI();
        request.setAttribute("isPermitAll", false); //요청객체는 응답이 될때까지 따라다닌다

        for(String antMatcher : antMatchers) {
            if(uri.startsWith(antMatcher)) {
                request.setAttribute("isPermitAll", true);
            }
        }

        filterChain.doFilter(request, response);


    }
}
