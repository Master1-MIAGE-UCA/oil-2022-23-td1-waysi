package jeu.proba;

import jeu.proba.model.Proba;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
@SpringBootApplication
public class ProbaApp {
    public static void main(String[] args) {
        SpringApplication.run(ProbaApp.class, args);
    }

    @Bean
    public CommandLineRunner initialisation(Proba proba, WebClient.Builder builder){
        return args -> {
            proba.setNom("Proba 1");
            String localhost = "http://localhost:";
            String port =args[0].split("=")[1];
            String url = localhost + port + "/proba";
            WebClient webClient = builder.baseUrl(url).build();
            webClient.post().uri("http://localhost:8080/appariement/probas")
                    .bodyValue(url).retrieve()
                    .bodyToMono(Void.class)
                    .block();
        };
    }
}
