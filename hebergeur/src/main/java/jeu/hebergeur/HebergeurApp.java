package jeu.hebergeur;

import jeu.hebergeur.model.Hebergeur;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.InetAddress;

@SpringBootApplication
public class HebergeurApp {
    /*
    @Value("${server.port}")
    private String port;
     */

    public static void main(String[] args) {
        SpringApplication.run(HebergeurApp.class, args);
    }
    /*
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
     */

    @Bean
    public CommandLineRunner initialisation(Hebergeur hebergeur, WebClient.Builder builder){
        return args -> {
            hebergeur.setNbJoueurMax(3);
            hebergeur.setNbPartie(0);
            String url = "http://localhost:";
            String port =args[0].split("=")[1];
            url += port + "/hebergeur";
            System.out.println(url);
            WebClient webClient = builder.baseUrl(url).build();
            webClient.post().uri("http://localhost:8080/appariement/hebergeurs")
                    .bodyValue(url).retrieve()
                    .bodyToMono(Void.class)
                    .block();
        };
    }
}
