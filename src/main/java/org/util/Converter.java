package org.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pojo.User;

import java.io.File;
import java.io.IOException;

public class Converter {

    private final static String baseFile = "src/test/resources/__files/userJackson.json";

    public static void toJSON(User user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(baseFile), user);
        System.out.println("json created!");
    }

    public static User toJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(baseFile), User.class);
    }

}