package com.eveningminusdot.bankalgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class BankalgoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankalgoApplication.class, args);
        // SpringApplication application = new SpringApplication(BankalgoApplication.class);
        // application.setWebApplicationType(WebApplicationType.SERVLET); // tomcat 서버 구동
        // application.run(args);
    }
}