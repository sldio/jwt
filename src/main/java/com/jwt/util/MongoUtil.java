package com.jwt.util;

import com.jwt.config.MongoConfiguration;
import com.jwt.entity.User;
import com.jwt.repository.UserRepository;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class MongoUtil
{
    private static DBCollection table;
    private DBObject document;

    @Autowired
    private UserRepository userRepository;


    static {
        MongoClient mongoClient = new MongoConfiguration().mongoClient();
        table = mongoClient.getDB("user").getCollection("user");
    }

    public void writeAllData(Map<String,String> parametrMap){
        /*DBObject object = getNewDocument();
        logger.info(" -->creates document " + object.toString());
        springTable.save(DBObjectUtilTestModel.toDBObjectFromMap(object, parametrMap));
        logger.info(" ---->updated document " + object.toString());*/
    }

    public User findUserByName(String name){
        User user = new User();
        user = userRepository.findByName(name);
        return user;
    }
}
