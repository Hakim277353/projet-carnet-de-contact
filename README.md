
# Application de Gestion de Contacts

Une application Java Swing pour gérer des contacts avec une base de données MySQL.

## ✨ Fonctionnalités

- Ajouter de nouveaux contacts avec nom, numéro de téléphone et adresse e-mail
- Modifier les contacts existants
- Supprimer des contacts
- Rechercher des contacts en temps réel
- Persistance des données avec MySQL

## 🧰 Prérequis

- Java JDK 24 ou version ultérieure
- MySQL Server 8.0 ou version ultérieure
- MySQL Connector/J 8.0.33

## ⚙️ Configuration de la Base de Données

L'application utilise MySQL avec les paramètres par défaut définis dans [`DatabaseConfig.java`](src/DatabaseConfig.java) :

```java
URL = "jdbc:mysql://localhost:3306/"
DB_NAME = "carnet_contacts"
USER = "root"
PASSWORD = "almia123"
```

## 🛠️ Installation

1. Cloner le dépôt
2. Importer le projet dans votre IDE
3. Configurer la base de données MySQL :
   - Exécuter le script SQL [`database.sql`](database.sql)
   - Ou laisser l'application créer automatiquement la base de données

## 🏗️ Compilation du Projet

Le projet peut être compilé avec Maven ou Gradle :

**Avec Maven** ([`pom.xml`](src/build/pom.xml)) :
```sh
mvn clean install
```

**Avec Gradle** ([`build.gradle`](src/build/build.gradle)) :
```sh
gradle build
```

## ▶️ Utilisation

1. Exécuter [`App.java`](src/App.java) pour démarrer l'application
2. La fenêtre principale permet de :
   - Ajouter des contacts
   - Modifier des contacts
   - Supprimer des contacts
   - Rechercher des contacts

## 🗂️ Structure du Projet

- [`App.java`](src/App.java) - Point d’entrée de l’application
- [`MainFrame.java`](src/MainFrame.java) - Interface principale
- [`Contact.java`](src/Contact.java) - Classe modèle de contact
- [`ContactDao.java`](src/ContactDao.java) - Opérations de base de données
- [`ContactForm.java`](src/ContactForm.java) - Formulaire d'ajout/modification
- [`ContactTableModel.java`](src/ContactTableModel.java) - Modèle de données pour le tableau
- [`DatabaseConfig.java`](src/DatabaseConfig.java) - Configuration de la base de données

## 📄 Licence

Ce projet est open source et disponible sous la licence MIT.
