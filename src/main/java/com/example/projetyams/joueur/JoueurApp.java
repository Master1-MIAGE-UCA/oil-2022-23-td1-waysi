package com.example.projetyams.joueur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JoueurApp {
    public static void main(String[] args) {
        int port = 8081;
        String url = "http://localhost:" + port+"/joueur";
        System.setProperty("joueur.url", url);
        SpringApplication.run(JoueurApp.class, args);
    }
}
