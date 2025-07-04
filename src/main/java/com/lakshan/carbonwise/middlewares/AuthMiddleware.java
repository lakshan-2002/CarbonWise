package com.lakshan.carbonwise.middlewares;

import com.lakshan.carbonwise.entity.User;
import com.lakshan.carbonwise.service.JWTService;
import com.lakshan.carbonwise.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class AuthMiddleware implements Filter {

    private final UserService userService;

    public AuthMiddleware(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String authHeader = req.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String email = JWTService.getUserFromToken(token);
            User user = userService.getUserByEmail(email);
            request.setAttribute("user", user);
            System.out.println(user.getName());
        }

        chain.doFilter(request, response);
    }
}
