package uz.todo.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DbProps {
    public static String get(String key) {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("resources/db.properties"));
            return props.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
