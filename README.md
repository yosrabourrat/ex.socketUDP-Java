# 📡 Projet Java – UDP Echo Client/Server

Ce projet implémente une communication client-serveur en Java via le protocole UDP.

## 🧠 Fonctionnement
- Le **serveur** écoute les messages UDP sur le port `1234`, les affiche et envoie une réponse saisie via le clavier.
- Le **client** envoie des messages au serveur et affiche les réponses reçues.
- L'échange se poursuit jusqu’à ce que le client envoie `"CLOSE"`.

## 🗂️ Fichiers
- UDPEchoServer.java : Code du serveur
- UDPEchoClient.java : Code du client

## 🛠️ Compilation
javac UDPEchoServer.java
javac UDPEchoClient.java
