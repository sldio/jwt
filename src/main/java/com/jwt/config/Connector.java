package com.jwt.config;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;


@ConditionalOnMissingBean
public class Connector
{
    private static MongoClient mongoClient;

    private static DB db;
    private static DBCollection table;


    public static DBCollection getTable(String tableName){

        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDB("user");
        try
        {
            table = db.getCollection("user");
        }
        catch (NullPointerException e)
        {
            table = db.createCollection(tableName, null);
        }

        return table;
    }
}
