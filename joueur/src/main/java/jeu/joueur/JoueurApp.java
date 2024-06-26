package jeu.joueur;
import jeu.joueur.model.DifficulteJoueur;
import jeu.joueur.model.Joueur;
import jeu.joueur.service.JoueurAbstrait;
import jeu.joueur.service.JoueurAleatoire;
import jeu.joueur.service.JoueurIntelligent;
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
    public static JoueurAbstrait joueurAbstrait;
    public static void main(String[] args) {
        SpringApplication.run(JoueurApp.class, args);
    }

    @Bean
    public CommandLineRunner initialisation(Joueur joueur, WebClient.Builder builder, @Value("${SERVER_IP}") String serverIp, @Value("${SERVER_PORT}") String serverPort){
        return args -> {

            joueur.setNom("Joueur 1");
            joueur.setDifficulteJoueur(jeu.joueur.model.DifficulteJoueur.ALEATOIRE);
            String myIp = InetAddress.getLocalHost().getHostAddress();
            String myUrl = "http://"+myIp+":"+serverPort +"/joueur";
            init(joueur);

            WebClient webClient = builder.baseUrl(serverIp).build();
            System.out.println("Url du joueur: " + myUrl);
            System.out.println("Url de l'appariement: " + serverIp);

            webClient.post().uri("appariement/joueurs")
                    .bodyValue(myUrl)
                    .retrieve().bodyToMono(String.class)
                    .block();
        };
    }
    public void init(Joueur joueur) {
        if (joueur.getDifficulteJoueur() == DifficulteJoueur.ALEATOIRE) {
            System.out.println("Joueur aléatoire");
            JoueurApp.joueurAbstrait = new JoueurAleatoire();
        }
        else {
            System.out.println("Joueur intelligent");
            System.out.println(joueur.getDifficulteJoueur());
            JoueurApp.joueurAbstrait = new JoueurIntelligent();
        }
    }

}
