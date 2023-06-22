package jeu.appariement.controller;
import jeu.appariement.model.Appariement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/appariement")
public class AppariementController {
    private RestTemplate restTemplate = new RestTemplate();
    private Object lock = new Object();
    Appariement appariement = new Appariement();
    @PostMapping("/joueurs")
    public void ajouterJoueurs(@RequestBody String url) throws InterruptedException {
        String hebergeurValide = "";
        appariement.getJoueurs().add(url);
        System.out.println("Joueur connecté à appariement : "+ appariement.getJoueurs());
        while(appariement.getHebergeurs().isEmpty()){
            System.out.println("En attente d'un hébergeur...");
            Thread.sleep(2000);
        }
        System.out.println("Un hébergeur est disponible !");
        boolean isJoueurAttribue = false;
        synchronized (lock){
            for(String hebergeurUri : appariement.getHebergeurs()){
                String urlHebergeur = hebergeurUri + "/joueurs";
                try{
                    System.out.println("Ajout du joueur à l'hébergeur " + hebergeurUri);
                    System.out.println("Envoi de la requête à l'hébergeur " + urlHebergeur);
                    restTemplate.postForObject(urlHebergeur, url, Void.class);
                    System.out.println("Le joueur a bien été ajouté à l'hébergeur");
                    isJoueurAttribue = true;
                    hebergeurValide = hebergeurUri;
                    break;
                }
                catch (Exception e){
                    System.out.println("Erreur lors de l'ajout du joueur à l'hébergeur : " + e.getMessage());
                }
            }
        }
        if(!isJoueurAttribue){
            System.out.println("Aucun hébergeur n'est disponible pour accepter le joueur");
        }
        else {
            System.out.println("L'hébergeur " + hebergeurValide + " a été choisi pour la partie");
            System.out.println("Le joueur " + url + " a été ajouté à l'hébergeur ");
            restTemplate.postForObject(url+"/hebergeur", hebergeurValide, Void.class);
        }
    }
    @PostMapping("/hebergeurs")
    public void ajouterHebergeur(@RequestBody String url){
        appariement.getHebergeurs().add(url);
        System.out.println(appariement.getHebergeurs());
    }
    @PostMapping("/probas")
    public void ajouterProba(@RequestBody String probaUrl){
        appariement.getProbas().add(probaUrl);
        String probaValue = restTemplate.getForObject(probaUrl, String.class);
        System.out.println("La probabilité " + probaValue + " a été ajoutée à l'appariement");
    }



}
