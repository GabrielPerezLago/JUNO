package com.gabriel.juno;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JunoApplicationInitializer {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
        System.out.println("""
                               _ _   _ _  _  ____ \s
                              | | | | | \\| |/ __ \\\s
                           _  | | | | |  ` | |  | |
                          | |_| | |_| | |\\  | |__| |
                           \\___/ \\___/|_| \\_|\\____/
                        """);
        SpringApplication.run(JunoApplicationInitializer.class, args);
    }

}
