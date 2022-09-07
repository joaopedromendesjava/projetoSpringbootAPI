package br.com.springboot.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication //inicia todo o projeto atraves dela (le todo o projeto)
public class Application {
	
    public static void main(String[] args) {
    	
        SpringApplication.run(Application.class, args); //linha principal que roda o projeto da um run na classe
        
    }
}
