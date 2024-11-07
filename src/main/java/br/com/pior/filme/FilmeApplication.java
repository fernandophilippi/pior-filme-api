package br.com.pior.filme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.pior.filme")
public class FilmeApplication {

    public static void main(String[] args) {

        SpringApplication.run(FilmeApplication.class, args);
    }

}
