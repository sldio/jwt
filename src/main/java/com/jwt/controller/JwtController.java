package com.jwt.controller;

import com.jwt.entity.User;
import com.jwt.repository.TokenRepository;
import com.jwt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class JwtController
{
    @Autowired
    UserService service;

    private static final Logger logger = LoggerFactory.getLogger(JwtController.class);

    @GetMapping("/secure/secure")
    public @ResponseBody String testSecur(){
        return "test security ok";
    }

    @GetMapping("/for-all/hello")
    public ModelAndView testPage(ModelAndView model){
        model.addObject("message", "test page message no security");
        model.setViewName("for-all/jwt");
        return model;
    }

    @GetMapping(value = "/for-all/add")
    public ModelAndView saveNewUser(HttpServletRequest request, ModelAndView model){

        model.addAllObjects(request.getParameterMap());

        User user = service.makeNewUser(request.getParameterMap());
        user = service.saveUser(user);

        String token = new TokenRepository().generateTokenForUser(user);
        user.setToken(token);

        model.addObject("Authorization", token);
        model.setViewName("for-all/show-new-user");

        user = service.saveUser(user);
        logger.info("->save user {}", user);
        model.addObject("id", user.getId());

        return model;
    }
}
