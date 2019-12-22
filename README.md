<!-- LOGO DU PROJET -->
<br />
<p align="center">
  <a href="https://github.com/axelallain/Projet-6">
    <img src="src/main/resources/static/img/logo.png" alt="Logo" width="80" height="32">
  </a>

  <h3 align="center">Projet 6</h3>

  <p align="center">
    Climbing webapp with Spring
    <br />
    <br />
  </p>
</p>



<!-- SOMMAIRE -->
## Sommaire

1. [Prérequis](#prérequis)
2. [Configuration](#configuration)
    * [Connexion à votre base de données](#connexion-à-votre-base-de-données)
    * [Génération du fichier .war avec votre configuration](#génération-du-fichier-war-avec-votre-configuration) 
    
3. [Déploiement](#déploiement)
    * [Base de données](#base-de-données)
    * [Apache Tomcat](#apache-tomcat)
    
4. [Plus d'informations](#plus-dinformations)

<!-- PRÉREQUIS -->
## Prérequis

Dans ce tutoriel nous allons voir comment configurer et déployer l'application via les outils suivants.

* JDK 8
* PostgreSQL 11
* Apache Maven 3.6.3
* Apache Tomcat 9

Pour commencer, clonez le projet sur votre ordinateur dans un repertoire facilement accessible.

Dans cet exemple nous allons utiliser le bureau. Ouvrez un terminal et tapez les commandes suivantes :

```sh
cd Desktop
```
```sh
git clone https://github.com/axelallain/Projet-6.git
```

<!-- CONFIGURATION -->
## Configuration

### Connexion à votre base de données

Pour connecter l'application à votre base de données, nous devons modifier le fichier de configuration "jdbc.properties".

Ouvrez le dossier du projet cloné et suivez ce chemin d'accès pour accéder à ce fichier de configuration :
```sh
src/main/resources/jdbc.properties
```
Munissez-vous de votre éditeur de texte préféré pour éditer "jdbc.properties".

Il ne vous reste plus qu'à modifier les propriétés suivantes pour connecter votre base de données PostgreSQL.
```properties
jdbc.url=jdbc:postgresql://localhost:5432/amisescalade
jdbc.username=postgres
jdbc.password=password
```

### Génération du fichier .war avec votre configuration

Une fois la configuration terminée, générons le fichier .war qui sera déployé sur notre serveur d'application.

Pour cela, ouvrez un terminal et rendez-vous à la racine du projet. Si le dossier du projet est sur le bureau :
```sh
cd Desktop/Projet-6
```

Pour plus de configuration concernant le fichier .war, vous pouvez éditer le fichier pom.xml situé à la racine du projet.

Vous pouvez désormais utiliser dans l'ordre les commandes Maven suivantes pour générer proprement le fichier .war :
```sh
mvn clean
```
```sh
mvn package
```

Si le build se passe correctement, votre fichier .war sera accessible dans le dossier target situé à la racine du projet :
```sh
Projet-6/target/amisescalade-1.0.0.war
```

<!-- DÉPLOIEMENT -->
## Déploiement

Votre fichier .war est prêt ? Alors passons à l'étape finale de ce tutoriel : le déploiement !

### Base de données

En premier lieu, nous allons mettre en place notre base de données. Plusieurs choix s'offrent à vous :

* Les tables ET les données de démo
* Uniquement les tables SANS données de démo

Une troisième option consiste à prendre la deuxième option puis d'ajouter les données de démo via la sauvegarde fournie.

Les script SQL sont disponibles à la racine du projet, voici à quelle option ils correspondent :

Les tables ET les données de démo :
```sh
amisescalade.backup.sql
```

Uniquement les tables SANS données de démo :
```sh
create.sql
```

Uniquement les données de démo :
```sh
insert.sql
```

Dans cet exemple d'exécution des scripts, nous allons utiliser l'utilisateur par défault de PostgreSQL "postgres".

Accédons d'abord aux scripts présents à la racine du projet via un terminal :
```sh
cd Desktop/Projet-6
```

Pour exécuter le script "amisescalade.backup.sql", entrez la commande suivante dans votre terminal :

```sh
psql -U postgres -f amisescalade.backup.sql
```

La base de données est prête !

Pour exécuter le script "create.sql", nous devons d'abord créer la base de données via les commandes suivantes :

Se connecter en tant qu'utilisateur "postgres" :
```sh
psql -U postgres
```

Créer la base de données en lui attribuant un nom. Dans notre exemple, son nom est "amisescalade" :
```sh
CREATE DATABASE amisescalade;
```

Pour exécuter le script fermez votre terminal, ouvrez-en un nouveau et retournez à la racine du projet via cd..

Une fois de nouveau à la racine du projet, entrez cette commande :
```sh
psql -U postgres -d amisescalade -f create.sql
```

La base de données vide est prête !

Si vous souhaitez ajouter les données de démo, voici la commande :
```sh
psql -U postgres -d amisescalade -f insert.sql
```

### Apache Tomcat

L'étape finale de l'étape finale ! Nous allons déployer le fichier .war sur un serveur d'application Tomcat 9.

Si vous ne l'avez pas encore fait, vous pouvez télécharger Tomcat depuis ce lien :
```sh
https://tomcat.apache.org/download-90.cgi
```

Vous pouvez extraire Tomcat sur votre bureau. Ouvrez le dossier dézippé et rendez-vous dans le dossier "webapps" :
```sh
tomcat/webapps
```

Il suffit de glisser le fichier .war dans ce dossier "webapps" et.. c'est tout !

Procédons au démarrage de Tomcat. Pour cela rendez-vous dans le dossier "bin" depuis un terminal :
```sh
cd Desktop/tomcat/bin
```

Pour lancer le serveur sous un environnement UNIX, entrez les commandes suivantes :

Rendre le script exécutable :
```sh
chmod +x startup.sh
```

Démarrage du serveur :
```sh
sh startup.sh
```

Pour lancer le serveur sous un environnement MS-DOS, entrez la commande suivante :
```sh
startup.bat
```

L'application est déployée ! L'URL pour y accéder varie selon le nom de votre fichier .war :

Si votre fichier .war s'appelle "amisescalade.war", l'URL sera le suivant :
```sh
localhost:8080/amisescalade
```

<!-- PLUS D'INFORMATIONS -->
## Plus d'informations

Pour plus d'informations un PDF est disponible à la racine du projet.

Ce tutoriel est terminé, merci et bonne journée :-)
