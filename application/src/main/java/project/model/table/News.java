package model.table;

import java.sql.Date;
import java.util.List;

import pja.databases.generalisation.annotation.ShowTable;
import pja.databases.generalisation.annotation.AttributDb;
import pja.databases.generalisation.annotation.IdDb;
import pja.databases.generalisation.annotation.TableDb;
import model.utils.HtmlUtils;

@pja.databases.generalisation.annotation.TableDb(name = "news")
public class News {
    
    @IdDb
    @AttributDb(name = "id")
    private Integer id;
    
    @AttributDb(name = "contenu")
    private String contenu;
    
    @AttributDb(name = "date_publication")
    private Date datePublication;
    
    @AttributDb(name = "images_couverture")
    private String imagesCouverture;
    
    @AttributDb(name = "alt_images_couverture")
    private String altImagesCouverture;
    
    // Relation avec les images (non mappée directement dans la base)
    private List<ImagesNews> images;
    
    // Constructeurs
    public News() {
    }
    
    public News(Integer id, String contenu, Date datePublication, 
                String imagesCouverture, String altImagesCouverture) {
        this.id = id;
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.imagesCouverture = imagesCouverture;
        this.altImagesCouverture = altImagesCouverture;
    }
    
    // Getters et Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getContenu() {
        return contenu;
    }
    
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    public Date getDatePublication() {
        return datePublication;
    }
    
    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }
    
    public String getImagesCouverture() {
        return imagesCouverture;
    }
    
    public void setImagesCouverture(String imagesCouverture) {
        this.imagesCouverture = imagesCouverture;
    }
    
    public String getAltImagesCouverture() {
        return altImagesCouverture;
    }
    
    public void setAltImagesCouverture(String altImagesCouverture) {
        this.altImagesCouverture = altImagesCouverture;
    }
    
    public List<ImagesNews> getImages() {
        return images;
    }
    
    public void setImages(List<ImagesNews> images) {
        this.images = images;
    }
    
    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", contenu='" + contenu + '\'' +
                ", datePublication=" + datePublication +
                ", imagesCouverture='" + imagesCouverture + '\'' +
                ", altImagesCouverture='" + altImagesCouverture + '\'' +
                '}';
    }

    @ShowTable(name = "Titre", numero = 1)
    public String getTitleInfo() {
        return HtmlUtils.extractH1Content(contenu);
    }

    @ShowTable(name = "Date Sortie", numero = 2)
    public String getDateInfo(){ 
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("d MMM yyyy");
        return formatter.format(datePublication);
    }

    @ShowTable(name = "Action", numero = 3)
    public String getActionTab(){
        return "<td>" + //
                        "<div class=\"dropdown\">" + //
                        "<button type=\"button\" class=\"btn btn-primary light sharp\" data-bs-toggle=\"dropdown\">" + //
                        "<svg width=\"20px\" height=\"20px\" viewBox=\"0 0 24 24\" version=\"1.1\"><g stroke=\"none\" stroke-width=\"1\" fill=\"none\" fill-rule=\"evenodd\"><rect x=\"0\" y=\"0\" width=\"24\" height=\"24\"/><circle fill=\"#000000\" cx=\"5\" cy=\"12\" r=\"2\"/><circle fill=\"#000000\" cx=\"12\" cy=\"12\" r=\"2\"/><circle fill=\"#000000\" cx=\"19\" cy=\"12\" r=\"2\"/></g></svg>" + //
                        "</button>" + //
                        "<div class=\"dropdown-menu\">" + //
                        "<a class=\"dropdown-item\" href=\"vehicule-form?id="+this.getId()+"\">Modifier</a>" + //
                        "<a class=\"dropdown-item\" href=\"vehicule-delete?id="+this.getId()+"\">Supprimer</a>" + //
                        "</div>" + //
                        "</div>" + //
                        "</td>";
    }
}