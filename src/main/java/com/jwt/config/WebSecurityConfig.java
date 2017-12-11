package com.jwt.config;

import com.jwt.repository.TokenRepository;
import com.jwt.security.JWTAuthenticationFilter;
import com.jwt.service.MyUserDetailService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    @Qualifier("userDetailService")
    MyUserDetailService userDetailsService;

    @Autowired
    JWTAuthenticationFilter filter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/secure/**").access("hasRole('ROLE_USER')")
                .antMatchers("/for-all/**").access("permitAll()")
                .anyRequest().authenticated()

                /*.and()
                .formLogin().permitAll()*/

                /*.and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))*/

                .and().csrf().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       /* auth
                .inMemoryAuthentication()
                .withUser("user").password("1").roles("USER");

        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN");*/

       /*System.out.println(userDetailsService);
       auth.userDetailsService(userDetailsService);*/

    }

   /* @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }*/
}
