package br.com.alura.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

//Habilitando o Spring pegar campos, informações de ordenação e paginação da requisição dos parâmetros da url;
//@EnableSpringDataWebSupport
@SpringBootApplication
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

}

