package edu.miu.simpleshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class SimpleshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleshopApplication.class, args);
    }

}
