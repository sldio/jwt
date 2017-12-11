package com.jwt.entity;


import java.io.Serializable;

public class User implements Serializable
{
    private String id;

    private String name;
    private String role;
    private String password;
    private String token;

    public User()
    {
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getStringUser(){
        StringBuilder builder = new StringBuilder();
        builder.append(" ")
                .append(name)
                .append(" ")
                .append(role)
                .append(" ")
                .append(password)
                .append(" ")
                .append(token);
        return builder.toString();
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
