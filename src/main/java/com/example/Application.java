package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class Application
{
    public static void main( String[] args )
    {
        SpringApplication app = new SpringApplication(Application.class);
        app.run();

    }
}
