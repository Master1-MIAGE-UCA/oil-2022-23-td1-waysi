package jeu.appariement.controller;
import jeu.appariement.model.Appariement;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/appariement")
public class AppariementController {
    private RestTemplate restTemplate = new RestTemplate();

    Appariement appariement = new Appariement();
    @PostMapping("/joueurs")
    public void ajouterJoueurs(@RequestBody String url) throws InterruptedException {
        String hebergeurValide = "";
        appariement.getJoueurs().add(url);
        System.out.println(appariement.getJoueurs());
        while(appariement.getHebergeurs().isEmpty()){
            System.out.println("En attente d'un hébergeur...");
            Thread.sleep(2000);
        }
        System.out.println("Un hébergeur est disponible !");
        boolean isJoueurAttribue = false;
        for(String hebergeurUri : appariement.getHebergeurs()){
            String urlHebergeur = hebergeurUri + "/joueurs";
            try{
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
        if(!isJoueurAttribue){
            System.out.println("Aucun hébergeur n'est disponible pour accepter le joueur");
        }
        else {
            restTemplate.postForObject(url+"/hebergeur", hebergeurValide, Void.class);
        }
    }
    @PostMapping(" ")
    public void ajouterHebergeur(@RequestBody String url){
        appariement.getHebergeurs().add(url);
        System.out.println(appariement.getHebergeurs());
    }

//    @PostMapping("/{idHebergeur}/ajouterJoueur")
//    public void attribuerJoueur(@PathVariable String hebergeurUri, @RequestBody String joueurUrl){
//        String urlHebergeur = hebergeurUri + "/joueurs";
//        try{
//            restTemplate.postForObject(urlHebergeur, joueurUrl, Void.class);
//            System.out.println("Le joueur a bien été ajouté à l'hébergeur");
//        }
//        catch (Exception e){
//            System.out.println("Erreur lors de l'ajout du joueur à l'hébergeur : " + e.getMessage());
//        }
//
//    }
    
}
