package com.example.akka;

import com.example.akka.starter.AkkaStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AkkaApplication {

    public static void main(String[] args) {
        AkkaStarter.init();


        SpringApplication.run(AkkaApplication.class, args);
    }

}
