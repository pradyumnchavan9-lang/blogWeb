package com.prady.blogWeb.jwt;

import com.prady.blogWeb.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {


    private final JwtUtility jwtUtility;
    private final MyUserDetailsService myUserDetailsService;

    public JwtAuthFilter(JwtUtility jwtUtility, MyUserDetailsService myUserDetailsService) {
        this.jwtUtility = jwtUtility;
        this.myUserDetailsService = myUserDetailsService;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        if (request.getServletPath().startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = authHeader.substring(7);

        if (!jwtUtility.validateToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtUtility.extractUsername(token);
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request,response);

    }
}
