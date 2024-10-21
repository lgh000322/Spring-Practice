package hello.external;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class JavaSystemProperties {

    public static void main(String[] args) {

        System.setProperty("helloKey", "helloValue");

        Properties properties = System.getProperties();
        for (Object key : properties.keySet()) {
            log.info("prop {} = {}", key, System.getProperty(String.valueOf(key)));
        }

        String url = System.getProperty("url");
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        String helloKey = System.getProperty("helloKey");

        log.info("url = {}", url);
        log.info("username = {}", username);
        log.info("password = {}", password);
        log.info("helloKey = {}", helloKey);

    }
}
