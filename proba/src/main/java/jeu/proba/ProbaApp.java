package jeu.proba;

import jeu.proba.model.Proba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ProbaApp {

    public static void main(String[] args) {
        SpringApplication.run(ProbaApp.class, args);
    }



    @Bean
    public CommandLineRunner initialisation(Proba proba, WebClient.Builder builder){
        return args -> {
            String localhost = "http://localhost:";
            String port = "8060";
            String url = localhost + port + "/proba";

            WebClient webClient = builder.baseUrl(url).build();
            webClient.post().uri("http://localhost:8080/appariement/probas")
                    .bodyValue(url).retrieve()
                    .bodyToMono(Void.class)
                    .block();
        };
    }

    @RestController
    class UnAutreProbaController {

        @Autowired
        private Proba proba;

        @PostMapping("/des")
        public ResponseEntity<Void> recevoirDes(@RequestBody int[] des) {
            proba.calculerProbabilites(des);
            return ResponseEntity.ok().build();
        }

        @GetMapping("/proba2")
        public Proba obtenirProba() {
            return proba;
        }
    }
}
