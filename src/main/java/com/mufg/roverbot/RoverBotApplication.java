package com.mufg.roverbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This App provision REST service endpoints for Rover Bot.
 * This information is delivered in JSON Format.
 * @author Kishore Diyyana
 */
@SpringBootApplication
public class RoverBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoverBotApplication.class, args);
    }

}
