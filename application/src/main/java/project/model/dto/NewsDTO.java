package model.dto;

import java.sql.Date;

import jframework.web.JFile;

public class NewsDTO {
    String contenu;
    Date datePublication;
    JFile imageCouverture;
    String alternativeCouverture;
    public Date getDatePublication() {
        return datePublication;
    }
    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }
    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
   
    public JFile getImageCouverture() {
        return imageCouverture;
    }
    public void setImageCouverture(JFile imageCouverture) {
        this.imageCouverture = imageCouverture;
    }
    public String getAlternativeCouverture() {
        return alternativeCouverture;
    }
    public void setAlternativeCouverture(String alternativeCouverture) {
        this.alternativeCouverture = alternativeCouverture;
    }

    
}
