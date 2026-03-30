# Guide d'installation et d'exécution

Ce guide explique comment préparer l'environnement, construire l'application et lancer les services avec Docker Compose.

Prérequis
- Docker et Docker Compose installés sur la machine.
- (Facultatif) Si vous exécutez localement le build hors conteneur, avoir `javac` et `jar` disponibles.

Étapes rapides
1. Ouvrez un terminal à la racine du projet (le dossier contenant `docker-compose.yml`).
2. Lancez la composition en arrière-plan :

```bash
docker compose up -d
```

3. Attendez que le service `builder` termine de construire le WAR (le service `app` attend automatiquement le WAR).
4. Ouvrez votre navigateur sur : http://localhost:5050

Que fait `docker compose up` ici ?
- `db` : lance Postgres 14 (base de données `newspaper`, utilisateur `postgres`, mot de passe `postgres`).
- `builder` : exécute `build.sh` à l'intérieur d'un conteneur OpenJDK 17 et place le fichier WAR construit dans un volume partagé.
- `app` : lance Tomcat 10 (JDK17), attend le WAR produit par `builder`, le copie en tant que `ROOT.war` puis démarre Tomcat.

Notes utiles
- Si vous modifiez le code et voulez reconstruire, relancer :

```bash
docker compose up --build -d
```

ou supprimez le volume d'artefacts pour forcer une reconstruction :

```bash
docker compose down -v
docker compose up -d
```

- Si vous préférez construire localement (hors conteneur) :
  1. Rendre le script `build.sh` exécutable : `chmod +x build.sh`.
  2. Exécuter `./build.sh` (ou définir `TOMCAT_WEBAPPS` pour une destination spécifique).
  3. Puis démarrer les services (sans laisser `builder` vous remplacer) :

```bash
docker compose up -d --no-deps --build app db
```

Sécurité et configuration
- Les identifiants Postgres dans ce dépôt sont pour développement. Changez `POSTGRES_PASSWORD` en production.
- Adaptez les variables d'environnement de connexion dans `docker-compose.yml` si votre application utilise d'autres noms/ports.

Dépannage
- Logs des services : `docker compose logs -f app` ou `docker compose logs -f builder`.
- Vérifiez que le WAR a bien été créé dans le volume : `docker run --rm -v \
  $(docker volume inspect --format '{{.Mountpoint}}' <your_project>_war_data):/artifacts busybox ls -la /artifacts`
