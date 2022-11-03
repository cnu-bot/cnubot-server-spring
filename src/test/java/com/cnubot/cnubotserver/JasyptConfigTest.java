package com.cnubot.cnubotserver;

import org.assertj.core.api.Assertions;
import org.hibernate.cfg.Environment;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class JasyptConfigTest {
    @Value("${jasypt.encryptor.password}")
    private String PASSWORD;
    @Autowired
    Environment environment;
    @Test
    void jasypt(){

        String url = "cnubot server url";
        String username = "ID";
        String password = "PASSWORD";
        System.out.println(PASSWORD);

        String encryptUrl = jasyptEncrypt(url);
        String encryptUsername = jasyptEncrypt(username);
        String encryptPassword = jasyptEncrypt(password);

        System.out.println("encryptUrl : " + encryptUrl);
        System.out.println("encryptUsername : " + encryptUsername);
        System.out.println("encryptPassword : " + encryptPassword);

        Assertions.assertThat(url).isEqualTo(jasyptDecryt(encryptUrl));
    }

    private String jasyptEncrypt(String input) {
        // Example key
        PASSWORD = System.getProperty("jasypt.encryptor.password");
        System.out.println(PASSWORD);
        String key = PASSWORD;
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.encrypt(input);
    }

    private String jasyptDecryt(String input){
        // Example key
        String key = PASSWORD;
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.decrypt(input);
    }
}