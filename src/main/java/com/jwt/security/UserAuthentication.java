package com.jwt.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public class UserAuthentication implements Authentication
{
    private final UserDetails userDetails;
    private boolean authenticated = true;

    public UserAuthentication(@NotNull UserDetails userDetails ){
        this.userDetails = userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials()
    {
        return userDetails.getPassword();
    }

    @Override
    public Object getDetails()
    {
        return userDetails;
    }

    @Override
    public Object getPrincipal()
    {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated()
    {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException
    {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName()
    {
        return userDetails.getUsername();
    }
}
