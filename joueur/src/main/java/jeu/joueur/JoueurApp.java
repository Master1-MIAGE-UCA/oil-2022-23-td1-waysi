package jeu.joueur;
import jeu.joueur.model.Joueur;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.URL;

@SpringBootApplication
public class JoueurApp {

    @Value("${server.port}")
    private String port;
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


            /**
             * Docker
             */
            /*
            System.out.println("args.length =  " + args[0]);
            joueur.setNom("Joueur 1");
                if (args.length > 0) {
                    port =args[0];
                    String url = args[0];
                    URL parseUrl = new URL(url);
                    int portTest = parseUrl.getPort();
                    String myIp = InetAddress.getLocalHost().getHostAddress();
                    System.out.println("myIp" + myIp);
                    String myUrl = "http://"+myIp+":"+String.valueOf(portTest);
                    WebClient webClient = builder.baseUrl(port).build();
                    System.out.println("myUrl" + myUrl);

                    webClient.post().uri("/appariement/joueurs")
                            .body(Mono.just(myUrl), String.class)
                            .retrieve().bodyToMono(String.class)
                            .block();
                }

             */


            /**
             * Docker
             */

        };
    }
}
