package jeu.hebergeur;

import jeu.hebergeur.model.Hebergeur;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.InetAddress;

@SpringBootApplication
public class HebergeurApp {

    @Value("${server.port}")
    private String port;
    public static void main(String[] args) {
        SpringApplication.run(HebergeurApp.class, args);
    }

    @Bean
    public CommandLineRunner initialisation(Hebergeur hebergeur, WebClient.Builder builder, @Value("${SERVER_IP}") String serverIp, @Value("${SERVER_PORT}") String serverPort){
        return args -> {

            hebergeur.setNbJoueurMax(2);
            hebergeur.setNbPartie(1);
            String myIp = InetAddress.getLocalHost().getHostAddress();
            String myUrl = "http://"+myIp+":"+serverPort+"/hebergeur";

            WebClient webClient = builder.baseUrl(serverIp).build();
            System.out.println("Url de l'hébergeur: " + myUrl);
            System.out.println("Url de l'appariement: " + serverIp);

            webClient.post().uri("appariement/hebergeurs")
                    .bodyValue(myUrl)
                    .retrieve().bodyToMono(String.class)
                    .block();
        };
    }

}
