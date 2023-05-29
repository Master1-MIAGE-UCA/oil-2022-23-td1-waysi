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
import java.net.URL;

@SpringBootApplication
public class HebergeurApp {

    @Value("${server.port}")
    private String port;


    public static void main(String[] args) {
        SpringApplication.run(HebergeurApp.class, args);
    }


    @Bean
    public CommandLineRunner initialisation(Hebergeur hebergeur, WebClient.Builder builder){
        return args -> {

            System.out.println("args" + args);
            hebergeur.setNbJoueurMax(3);
            hebergeur.setNbJoueurMax(2);
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




            /**
             * Docker
             */


            /*
            hebergeur.setNbJoueurMax(2);
            hebergeur.setNbPartie(1);

                if (args.length > 0){
                    port = args[0] ;
                    String url = args[0];
                    URL parseUrl = new URL(url);
                    int portTest = parseUrl.getPort();
                    System.out.println(port);
                    String myIp = InetAddress.getLocalHost().getHostAddress();
                    System.out.println("myIp" + myIp);
                    String myUrl = "http://"+myIp+":"+String.valueOf(portTest);
                    WebClient webClient = builder.baseUrl(port).build();
                    System.out.println("myUrl" + myUrl);

                    webClient.post().uri("/appariement/hebergeurs")
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
