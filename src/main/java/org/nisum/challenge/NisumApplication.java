package org.nisum.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:mensajes.properties")
public class NisumApplication {
        public static void main(String[] args) {
            SpringApplication.run(NisumApplication.class, args);
        }
}
