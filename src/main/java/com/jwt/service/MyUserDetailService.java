package com.jwt.service;

import com.jwt.entity.User;
import com.jwt.repository.UserRepository;
import com.jwt.security.JWTAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailService")
public class MyUserDetailService implements UserDetailsService
{
    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = repository.findByName(username);

        UserDetails loadedUser;
        Set<GrantedAuthority> role = new HashSet<>();

        try{
            user.getId();
            role.add(new SimpleGrantedAuthority(user.getRole()));
            logger.info("--> user role {}", role.stream().toArray());

            loadedUser = new org.springframework.security.core.userdetails.User(
                    user.getName(),
                    user.getPassword(),
                    true, true, true, true, role);
        }
        catch (NullPointerException e){
            throw new UsernameNotFoundException("can`t found " + username);
        }

        return loadedUser;
    }
}
