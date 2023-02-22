package org.nisum.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class NisumApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(NisumApplication.class, "--spring.output.ansi.enabled=always");
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NisumApplication.class);
    }
}
