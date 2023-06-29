package jeu.appariement;


import jeu.appariement.model.Appariement;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppariementApp {
    public static void main(String[] args) {
        SpringApplication.run(AppariementApp.class, args);
    }

}
