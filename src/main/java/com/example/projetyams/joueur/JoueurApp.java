package com.example.projetyams.joueur;

import com.example.projetyams.joueur.model.Joueur;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class JoueurApp {
    public static void main(String[] args) {
        SpringApplication.run(JoueurApp.class, args);
    }

    @Bean
    public CommandLineRunner initialisation(Joueur joueur, WebClient.Builder builder){
        return args -> {
            joueur.setNom("Joueur 1");
            String localhost = "http://localhost:";
            String port =args[0].split("=")[1];
            String url = localhost + port + "/joueur";
            WebClient webClient = builder.baseUrl(url).build();
            webClient.post().uri("http://localhost:8080/appariement/joueurs")
                    .bodyValue(url).retrieve()
                    .bodyToMono(Void.class)
                    .block();
        };
    }
}
