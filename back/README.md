# MDD API - Backend Spring Boot

Projet backend d'une application développée avec Spring Boot et Maven.

---

## Stack technique

- Java 17
- Spring Boot 3.1.4
    - Spring Web
    - Spring Security
    - Spring Data JPA
- MySQL
- JWT (jjwt)
- Lombok
- MapStruct
- SpringDoc / Swagger
- Maven

---

## Structure du projet

- `Entity/` : les entités JPA (Article, Comment, User, Theme, Subscription)
- `repository/` : les interfaces Spring Data JPA
- `services/` : la couche métier
- `controller/` : les endpoints REST
- `dto/` : les objets de transfert de données
- `config/` : la configuration sécurité + Swagger
- `src/main/resources/script.sql` : script SQL de création de la base de données

---

## Pré-requis

- Java 17
- Maven 3.x
- MySQL 8.x

---

## Création de la base de données

1. Exécute la commande suivante dans ton terminal :

```bash
mysql -u root -p < src/main/resources/script.sql
```

Ce script crée la base de données `mdd_db` et toutes les tables avec leurs relations.

---

## Configuration de la base de données

Dans `src/main/resources/application.properties`, configure les accès MySQL :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mdd_db
spring.datasource.username=root
spring.datasource.password=ton_mot_de_passe

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Swagger (Springdoc)
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
```

---

## Lancer le projet

### Installation des dépendances et compilation :

```bash
mvn clean install
```

### Lancement de l'application :

```bash
mvn spring-boot:run
```

L'API sera disponible par défaut à [http://localhost:8080](http://localhost:8080)

---

## Documentation Swagger

Une fois le backend lancé, la documentation interactive est dispo ici :

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
