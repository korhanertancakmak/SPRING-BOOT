package com.luv2code.springboot.thymeleafdemo.security;

import com.luv2code.springboot.thymeleafdemo.entity.User;
import com.luv2code.springboot.thymeleafdemo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    public CustomAuthenticationSuccessHandler(UserService theUserService) {
        this.userService = theUserService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        System.out.println("In customAuthenticationSuccessHandler");

        // Get the logged-in username
        String userName = authentication.getName();
        System.out.println("userName=" + userName);

        // Retrieve the user details using the username
        User theUser = userService.findByUserName(userName);

        // Store user details in the session
        HttpSession session = request.getSession();
        session.setAttribute("user", theUser);

        // Redirect to the homepage
        response.sendRedirect(request.getContextPath() + "/");
    }
}
