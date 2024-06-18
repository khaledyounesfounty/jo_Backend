
# Guide de Configuration de l'Application Spring Boot

Ce README fournit des instructions sur comment configurer et exécuter l'application Spring Boot localement.

## Prérequis

Assurez-vous d'avoir installé :
- Java JDK 17 ou supérieur
- Maven 3.6 ou supérieur

## Configuration de l'Environnement

Pour augmenter la sécurité de la connexion à la base de données, les variables d'environnement sont gérées via un fichier `.env` situé dans le répertoire `env`. Ce fichier doit être ajouté au répertoire `resources` de l'application.

1. Copiez le fichier `.env` disponible aupres du responsable du project, le placer dans le répertoire `src/main/resources` 


2. Configurez la method `corsFilter()` de la class `WebConfig.java` pour l'exécution en local. Ouvrez le fichier `.env` à la racine du projet et commentez la ligne suivante :
    ```java
    /* @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://finalexamyounesfront.s3-website.eu-west-3.amazonaws.com");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }*/
    ```
   et décommentez le block suivante :
    ```java
    @Bean
     public CorsFilter corsFilter() {
         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
         CorsConfiguration config = new CorsConfiguration();
         config.setAllowCredentials(true);
         config.addAllowedOrigin("http://localhost:3000");
         config.addAllowedHeader("*");
         config.addAllowedMethod("*");
         source.registerCorsConfiguration("/**", config);
         return new CorsFilter(source);
     }
    ```

3. Demandez les données de connexion à un compte admin au responsable du projet.
   
4. Lancer l'application en utilisant la commande suivante :
    ```bash
    mvn spring-boot:run
    ```
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

