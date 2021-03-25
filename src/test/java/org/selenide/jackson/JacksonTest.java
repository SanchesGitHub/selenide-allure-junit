package org.selenide.jackson;

import org.junit.Before;
import org.junit.Test;
import org.pojo.User;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.util.Converter.toJSON;
import static org.util.Converter.toJavaObject;

public class JacksonTest {

    User user = new User(1, "Alex", "+38000000001", "medium");

    @Before
    public void setJSON() {
        try {
            toJSON(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void toJavaObjectTest() {
        User userToJavaObject = null;
        try {
            userToJavaObject = toJavaObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotEquals(userToJavaObject, user);
        assertEquals(user.toString(), userToJavaObject.toString());
    }

}
