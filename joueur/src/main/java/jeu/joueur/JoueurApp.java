package jeu.joueur;
import jeu.joueur.model.Joueur;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

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
            System.out.println(args.length);
            joueur.setNom("Joueur 1");
            if (args.length > 0) {

                String localhost = "http://localhost:";
                String[] parts = args[0].split("=");
                if (parts.length > 1) {
                    port =/*args[0].split("=")*/parts[1];
                    String url = localhost + port + "/joueur";

                    WebClient webClient = builder.baseUrl(url).build();
                    webClient.post().uri("http://localhost:8080/appariement/joueurs")
                            .bodyValue(url).retrieve()
                            .bodyToMono(Void.class)
                            .block();
                }

            }
        };
    }
}
