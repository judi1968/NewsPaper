# guide

## 1 - instalation des environement 
executer : 
  `docker compose up -d`

## 2 - Si tu code et tu veux recompiler 
executer les un a un: 
  `docker-compose up builder`
  `docker-compose restart app`

## 3 - Pour les bases de donne 
- Créer les tables
`docker-compose exec -T db psql -U postgres -d newspaper -p 5433 < database/create.sql`


- Insérer les données
`docker-compose exec -T db psql -U postgres -d newspaper -p 5433 < database/insert.sql`


- Supprimer les données
`docker-compose exec -T db psql -U postgres -d newspaper -p 5433 < database/delete.sql`

- Supprimer les tables
`docker-compose exec -T db psql -U postgres -d newspaper -p 5433 < database/drop.sql`

### S'il y a un probleme sur le build pour la raison du dernier ligne : 
executer ceci : 
  `sed -i 's/\r$//' build.sh`
