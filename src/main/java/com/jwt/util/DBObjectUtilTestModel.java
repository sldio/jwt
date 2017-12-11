package com.jwt.util;

import com.jwt.entity.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class DBObjectUtilTestModel
{
    private static BasicDBObject document = new BasicDBObject();

    public static BasicDBObject toBasicDBObjectFromKeyAndValue(String key, String val){
        document.put(key, val);
        return document;
    }

    public static BasicDBObject usertoBasicDBObject(User user){

        BasicDBObject doc = new BasicDBObject();

        String[] data = user.getStringUser().split(" ");
        Field[] fields = user.getClass().getDeclaredFields();
        Map<String,String> map = new HashMap<>();

        Stream<Field> stream = Stream.of(fields);

        final int[] i = {0};

        stream.forEach(field -> {
            if (i[0] != 0)
            {
                map.put(field.getName(), data[i[0]]);
            }
            i[0]++;
        });

        doc.putAll(map);
        return doc;
    }

    public static DBObject toDBObjectFromMap(DBObject newObject, Map<String,String> map){
        newObject.putAll(map);
        return newObject;
    }

    public static User fromDBObject(DBObject object){
        User user = new User();

        user.setName(String.valueOf(object.get("name")));
        user.setPassword(String.valueOf(object.get("password")));
        user.setRole(String.valueOf(object.get("role")));

        return user;
    }
}
