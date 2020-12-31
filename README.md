# Application covid

Théophile STAUDER et Maéla LACONI

## Mise en place de MySQL (sous Linux)
### Création de la database
CREATE DATABASE Jee_database DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
### Création d'un utilisateur
CREATE USER 'projetTest'@'localhost' IDENTIFIED BY 'projetTest' ;
### Ajout de la databe créée à notre utilisateur
GRANT ALL ON Jee_database.* TO 'projetTest'@'localhost' IDENTIFIED BY 'projetTest' ;
### Connexion a notre database sur le terminal
 mysql -h localhost -u projetTest -p
 Puis entrer le mot de passe qui est 'projetTest'.
 
 ## Mise en place d'Eclipse
 
 ### Modification de l'encodage par défaut 
 Rendez-vous alors dans la barre de menus supérieure, et cliquez sur 'Window', puis 'Preferences'. Dans la fenêtre qui s'affiche alors, il y a un champ vous permettant de taper du texte en haut à gauche. Saisissez-y le mot "encoding", et dans chaque section qui apparaît alors dans le volet de gauche, changez l'encodage par défaut (il est généralement réglé à Cp1252 ou ISO-8859-1) par la valeur UTF-8.
 
 ### Désactivation de la vérification de l'orthographe
Rendez-vous à nouveau dans le menu 'Window' > 'Preferences', puis dans le volet de gauche rendez-vous dans 'General' > 'Editors' > 'Text Editors', et dans le volet de droite cochez la case "Show line numbers". Dans le volet de gauche, cliquez alors sur le sous-menu 'Spelling', et dans le nouveau volet de droite qui apparaît, décochez la case "Enable spell checking".

 ### Modification du build path
 Faire un clic droit sur le dossier du projet, sélectionnez "Build Path" puis "Configure Build Path...".
 Séléctionner l'ongler 'Source' puis regarder en bas le champ 'Default output folder' et préciser le chemin vers WEB-INF/classes afin que nos classes, lors de leur compilation, soient automatiquement déposées dans le dossier pris en compte par notre serveur d'applications. Donc saisir le chemin nomProjet/WebContent/WEB-INF/classes.
 
 ## Gestion du l'arborescence
 Par la suite, on modifie le web.xml qui se trouve sous WebContent/WEB-INF.
 Les pages jsp se trouve elles aussi sous WebContent/WEB-INF.
 En ce qui concerne les servlets, on les trouve dans src/ServletPackage.
  
