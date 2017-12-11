package com.jwt.funny;

import com.jwt.entity.User;
import com.jwt.repository.TokenRepository;

public class Test
{
    public static void main(String[] args)
    {
        User user = new User();
        user.setName("1");
        user.setPassword("pas");
        user.setRole("role");

        String token = new TokenRepository().generateTokenForUser(user);
        System.out.println(token);
        System.out.println(new TokenRepository().JwtCsrfValidatorFilter(token));
    }
}
