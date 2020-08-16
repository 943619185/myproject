package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@Configuration
public class App {

    @RequestMapping("hello")

    public String hello(){
        return "！";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
