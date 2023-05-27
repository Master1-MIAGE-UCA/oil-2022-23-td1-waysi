package jeu.hebergeur;

import jeu.hebergeur.model.Hebergeur;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class HebergeurApp {
    public static void main(String[] args) {
        SpringApplication.run(HebergeurApp.class, args);
    }


    @Bean
    public CommandLineRunner initialisation(Hebergeur hebergeur, WebClient.Builder builder){
        return args -> {
            hebergeur.setNbJoueurMax(3);
            hebergeur.setNbPartie(0);
            if (args.length > 0 ){
                String url = "http://localhost:";
                String[] parts = args[0].split("=");
                if (args.length > 1){
                    url += parts[1] + "/hebergeur";
                    System.out.println(url);
                    WebClient webClient = builder.baseUrl(url).build();
                    webClient.post().uri("http://localhost:8080/appariement/hebergeurs")
                            .bodyValue(url).retrieve()
                            .bodyToMono(Void.class)
                            .block();
                }
            }
        };
    }
}
