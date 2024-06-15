
# Guide de Configuration de l'Application Spring Boot

Ce README fournit des instructions sur comment configurer et exécuter l'application Spring Boot localement.

## Prérequis

Assurez-vous d'avoir installé :
- Java JDK 17 ou supérieur
- Maven 3.6 ou supérieur

## Configuration de l'Environnement

Pour augmenter la sécurité de la connexion à la base de données, les variables d'environnement sont gérées via un fichier `.env` situé dans le répertoire `env`. Ce fichier doit être ajouté au répertoire `resources` de l'application.

1. Naviguez vers le répertoire racine de votre projet.
2. Copiez le fichier `.env` dans le répertoire `src/main/resources` :

   ```bash
   cp env/.env src/main/resources/.env

## Librairies Utilisées

- Spring Boot 
- Spring Security
- Spring Data JPA
- MySQL Connector : Connexion à la base de données
- Lombok : Réduction du code boilerplate
- MapStruct : Mapping des DTOs
- Springdoc OpenAPI : Documentation de l'API
- JWT : Authentification
- ZXing : Génération de QR Code
- Java Mail
- JUnit : Tests unitaires
- Mockito : Mocking des dépendances
- Hamcrest : Matcher pour les tests
- AssertJ : Assertions pour les tests

