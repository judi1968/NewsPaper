package model.table;

import pja.databases.generalisation.annotation.AttributDb;
import pja.databases.generalisation.annotation.IdDb;
import pja.databases.generalisation.annotation.TableDb;

@TableDb(name = "images_news")
public class ImagesNews {
    
    @IdDb
    @AttributDb(name = "id")
    private Integer id;
    
    @AttributDb(name = "url_image")
    private String urlImage;
    
    @AttributDb(name = "alt_image")
    private String altImage;
    
    @AttributDb(name = "id_news")
    private Integer idNews;
    
    // Relation avec la news (non mappée directement)
    private News news;
    
    // Constructeurs
    public ImagesNews() {
    }
    
    public ImagesNews(Integer id, String urlImage, String altImage, Integer idNews) {
        this.id = id;
        this.urlImage = urlImage;
        this.altImage = altImage;
        this.idNews = idNews;
    }
    
    // Getters et Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUrlImage() {
        return urlImage;
    }
    
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
    
    public String getAltImage() {
        return altImage;
    }
    
    public void setAltImage(String altImage) {
        this.altImage = altImage;
    }
    
    public Integer getIdNews() {
        return idNews;
    }
    
    public void setIdNews(Integer idNews) {
        this.idNews = idNews;
    }
    
    public News getNews() {
        return news;
    }
    
    public void setNews(News news) {
        this.news = news;
    }
    
    @Override
    public String toString() {
        return "ImagesNews{" +
                "id=" + id +
                ", urlImage='" + urlImage + '\'' +
                ", altImage='" + altImage + '\'' +
                ", idNews=" + idNews +
                '}';
    }
}