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
`docker-compose exec -T db psql -U postgres -d newspaper < database/create.sql`


- Insérer les données
`docker-compose exec -T db psql -U postgres -d newspaper < database/insert.sql`


- Supprimer les données
`docker-compose exec -T db psql -U postgres -d newspaper < database/delete.sql`
