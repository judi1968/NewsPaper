-- Création de la base de données (si nécessaire)
-- CREATE DATABASE newspaper;
-- \c newspaper;

-- Table news
CREATE TABLE IF NOT EXISTS news (
    id SERIAL PRIMARY KEY,
    contenu TEXT NOT NULL,
    date_publication DATE NOT NULL,
    images_couverture TEXT,
    alt_images_couverture TEXT,
    href TEXT,
    title TEXT
);

-- Table images_news
CREATE TABLE IF NOT EXISTS images_news (
    id SERIAL PRIMARY KEY,
    url_image TEXT NOT NULL,
    alt_image TEXT NOT NULL,
    id_news INTEGER NOT NULL,
    FOREIGN KEY (id_news) REFERENCES news(id) ON DELETE CASCADE
);
