package com.jwt.security;

import com.jwt.entity.User;
import com.jwt.repository.TokenRepository;
import com.jwt.service.MyUserDetailService;
import com.jwt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


@Component
public class JWTAuthenticationFilter extends GenericFilterBean
{

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
    @Autowired
    private MyUserDetailService service;

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException
    {
        User user;
        UserDetails userDetails;
        TokenRepository tokenRepository = new TokenRepository();

        final HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        String token = httpRequest.getHeader("Authorization");

        logger.info(" - > token - " + token);
        String userId = tokenRepository.JwtCsrfValidatorFilter(token);

        if (userId != null)
        {
            user = userService.getUserById(userId);
            logger.info("-> user {}", user);
            userDetails = service.loadUserByUsername(user.getName());

            if (user.getPassword().equals(userDetails.getPassword())){
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(new UserAuthentication(userDetails));
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
