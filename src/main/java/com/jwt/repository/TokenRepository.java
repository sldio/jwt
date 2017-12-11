package com.jwt.repository;

import com.jwt.entity.User;
import com.jwt.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class TokenRepository implements CsrfTokenRepository
{
    /*private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
    private static final byte[] secretBytes = secret.getEncoded();*/
    private static final String base64SecretBytes = "sdfsdf";//Base64.getEncoder().encodeToString(secretBytes);
    private Map<String,String> inputData;

    public Map<String, String> getInputData()
    {
        return inputData;
    }

    public void setInputData(Map<String, String> inputData)
    {
        this.inputData = inputData;
    }

    public static String getBase64SecretBytes()
    {
        return base64SecretBytes;
    }

    public String generateTokenForUser(User user)
    {
        String id = user.getId();

        String token = Jwts.builder()
                .setId(id)
                .claim("user_id", user.getId())
                .signWith(SignatureAlgorithm.HS256, base64SecretBytes)
                .compact();

        System.out.println(user);

        return token;
    }

    public String JwtCsrfValidatorFilter(String token)

    {
        Claims claims;

        try
        {
            claims = Jwts.parser()
                    .setSigningKey(base64SecretBytes)
                    .parseClaimsJws(token).getBody();
            return String.valueOf(claims.get("user_id"));
        }
        catch (Exception e){
                System.out.println("can`t pars token");
                return null;
            }
    }

    @Override
    public void saveToken(CsrfToken csrfToken,
                          HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse)
    {

    }

    @Override
    public CsrfToken loadToken(HttpServletRequest httpServletRequest)
    {
        return null;
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest httpServletRequest)
    {
        return null;
    }
}
