## Itération 3

### Tâches finalisées :

#### Web Service

- Finalisation du web service d’implémentation de probabilité pour aider les joueurs à prendre des décisions éclairées pendant le jeu
- Gestion des parties
- Simulation d’un tour complet sur une partie
- Attribution de tous les coups possible à un joueur : Le joueur doit pouvoir lancer les dès, choisir le dé, récupérer les dès choisis
- Choix des figures et calcul des points
- Création de la grille de score
- Tester la communication entre les Web services sur docker et corriger les bugs

#### Déploiement
- Finalisation de la mise en place la configuration du Docker : Création des images de chaque web service  et du docker compose

## Itération 4
### Tâches à venir :
#### Web Service
- Creation de l'IA intelligente
- Optimiser le webservice probabilité afin que la classe IA random et IA intelligente puissent appeler chacune des fonctions de la classe joueur

#### Déploiement
- Amélioration de l’intégration continue
- Implémentation du docker sur le webservice proba

#### Tests unitaires
- Écriture des tests unitaires indépendants de Spring pour chaque composant
- Écriture des tests unitaires dépendants de Spring pour les chemins exposés et les appels simulés vers l'extérieur
- Exécution des tests unitaires pour vérifier la qualité du code