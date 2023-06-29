## Réponses aux questions

### Diagramme général de l'architecture des services

![img.png](Diagramme_architecture_YAMS.png)

### Explication du rôle de chaque service / les échanges entre les services

Le système est composé de quatre entités principales : l'application d'appariement, l'application hébergeur, l'application joueur et l'application Probabilité. Chacune de ces entités comprend deux composants clés : le Controller et le Web service.

1. Application Hébergeur :
- Composants : Controller, Web service et Service.
- Le Service du Hébergeur exécute toutes les actions de la partie.
- Le Service comprend deux sous-composants : "Méthodes de calcul des points" et "Toutes les fonctions relatives à une partie".

2. Application Appariement :
- Représentée par son Controller.
- Établit la communication avec le Controller de l'Hébergeur.
- Initie le démarrage de la partie et transmet les informations du joueur ajouté.
- Le Controller de l'Hébergeur informe l'Appariement lorsque l'Hébergeur est complet et prêt à lancer une partie.
- Le Web service de l'Hébergeur communique avec le Controller de l'Appariement pour enregistrer ses informations.

3. Application Joueur :
- Composants : Controller et Web service.
- Le Web service comprend deux sous-composants : "Joueur aléatoire" et "Joueur intelligent".
- Différents types de joueurs avec des stratégies différentes peuvent être représentés.
- Le joueur aléatoire est fonctionnel et intégré dans l'application Yams.
- Le développement du joueur intelligent est en cours et sera intégré à l'application.

4. Interactions entre les composants :
- Le Controller de l'Hébergeur exécute toutes les actions de la partie.
- Le Controller de l'Appariement communique avec le Controller du Joueur pour informer qu'un hébergeur est libre.
- Le Controller du Joueur peut demander à être ajouté à un hébergeur libre en communiquant avec le Controller de l'Appariement.
- Le Web service du Joueur enregistre ses informations sur l'Appariement en communiquant avec le Controller de l'Appariement.
- Le Joueur intelligent peut demander des probabilités spécifiques au Controller de Probabilité (en cours de développement).

5. Fonctionnement du jeu :
- L'Appariement est lancé en premier, suivi de l'Hébergeur et du Joueur.
- Les joueurs et l'Hébergeur envoient leurs informations à l'Appariement sous forme d'URL.
- Lorsqu'un hébergeur est libre et qu'un joueur souhaite rejoindre une partie, l'Appariement transmet les informations du joueur à l'Hébergeur et vice versa.
- Lorsque l'Hébergeur est plein et prêt à lancer une partie, il informe l'Appariement, qui à son tour informe tous les joueurs connectés à cet Hébergeur.
- L'Appariement demande ensuite à l'Hébergeur de lancer la partie.

### Services stateless / non stateless

L’application Joueur est stateless, elle ne conserve aucune donnée (à l’exception de l’url de son hébergeur), ce qui veut dire que son score et sa liste des dés doivent être envoyés par un autre web service.
L’application Hébergeur n’est en revanche pas Stateless, il conserve l’URL de tous ses joueurs mais également le score de chaque joueur au cours de la partie. On aurait pu créer un web service à part qui servirait à conserver le score de chaque joueur pour que l’hébergeur n’ait plus qu’à communiquer avec celui-ci afin d’obtenir tous les scores à n’importe quel moment, mais nous n’avons pas jugé primordiale dans ce cas de rendre l’hébergeur complètement stateless.

Le web service Appariement conserve lui aussi uniquement les URL des joueurs et des hébergeurs afin de les mettre en relation, ce qui ne constitue pas une application entièrement stateless, mais cela était nécessaire pour que ce service joue son rôle dans ce projet.

Enfin, le web service probabilité est entièrement stateless, il ne conserve aucune donnée à chaque requête, il se contente de renvoyer la probabilité qui lui a été demandée.


### Tests unitaires

Les tests du fichier HebergeurControllerTest sont des tests unitaires qui vérifient le comportement du contrôleur HebergeurController en isolant les dépendances avec l'utilisation de mocks et en vérifiant les résultats attendus pour différentes méthodes du contrôleur.

Dans le fichier HebergeurControllerTest, les tests vérifient les fonctionnalités suivantes :

- Le test afficherDetailsHebergeur vérifie si les détails d'un hébergeur sont correctement retournés, en vérifiant que la liste de joueurs est vide et que la capacité maximale n'est pas atteinte.
- Le test isFull vérifie si l'état de l'hébergeur est correctement retourné, en vérifiant si la capacité maximale est atteinte.
- Le test lancerPartie vérifie si la méthode jouerTour du service hebergeurService est appelée 13 fois lorsqu'une partie est lancée.

Dans le fichier JoueurControllerTest, les tests vérifient les fonctionnalités suivantes :

- Le test testGetNom vérifie si le nom du joueur est correctement retourné lors de l'appel à l'endpoint /joueur/nom.
- Le test testAjouterHebergeur vérifie si le joueur est correctement ajouté à un hébergeur lors de l'appel à l'endpoint /joueur/hebergeur.
- Le test testLancerDes vérifie si les dés sont correctement lancés lors de l'appel à l'endpoint /joueur/lancerDes.
- Le test testScoreRelance vérifie si les dés sont correctement relancés lors de l'appel à l'endpoint /joueur/scoreRelance.
- Le test testRelancerDes vérifie si les dés sont correctement relancés lors de l'appel à l'endpoint /joueur/relancerDes.
- Le test testChoisirCombinaison vérifie si la combinaison choisie par le joueur est correctement retournée lors de l'appel à l'endpoint /joueur/choisirCombinaison.
- Le test testScore vérifie si le score du joueur est correctement retourné lors de l'appel à l'endpoint /joueur/score.

En résumé, les tests du fichier HebergeurControllerTest vérifient les différentes fonctionnalités du contrôleur HebergeurController, tandis que les tests du fichier JoueurControllerTest vérifient les fonctionnalités du contrôleur JoueurController en simulant des requêtes HTTP et en vérifiant les réponses obtenues.

## Bilan sur les aspects technologiques et fonctionnels

### Tâches finalisées :

#### Web Service
- Création de l'IA intelligente( pas terminée et pas deployée)
- Séparation du joueur en deux entités (Joueur Aléatoire/joueur intelligent),
- Ajout d'une fonction à l'initialisation du web service joueur pour attribuer le joueur intelligent ou aléatoire à la classe abstraite de joueur


#### Déploiement
- Amélioration de l’intégration continue
- Implémentation du docker sur le web service proba


#### Tests unitaires
- Écriture des tests unitaires indépendants de Spring pour chaque composant
- Écriture des tests unitaires dépendants de Spring pour les chemins exposés et les appels simulés vers l'extérieur
- Exécution des tests unitaires pour vérifier la qualité du code 






