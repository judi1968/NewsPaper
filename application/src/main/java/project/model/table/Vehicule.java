package model.table;


import java.util.Date;

import pja.databases.generalisation.annotation.AttributDb;
import pja.databases.generalisation.annotation.IdDb;
import pja.databases.generalisation.annotation.ShowTable;
import pja.databases.generalisation.annotation.TableDb;

@TableDb(name = "vehicule")
public class Vehicule {
    @IdDb
    @AttributDb(name = "id")
    private String id;

    @AttributDb(name = "reference")
    private String reference;

    @AttributDb(name = "nb_place")
    private Integer nbPlace;
    
    @AttributDb(name = "type_carburant")
    private String typeCarburant;

    @AttributDb(name = "date_delete")
    private Date dateDelete;

    @AttributDb(name = "heure_disponnibilite")
    private Date heureDisponnibilite;

    public Vehicule(String id, String reference, Integer nbPlace, String typeCarburant, Date dateDelete) {
        this.id = id;
        this.reference = reference;
        this.nbPlace = nbPlace;
        this.typeCarburant = typeCarburant;
        this.dateDelete = dateDelete;
    }



    public Vehicule(){}

    
    public Date getHeureDisponnibilite() {
        return heureDisponnibilite;
    }

    public void setHeureDisponnibilite(Date heureDisponnibilite) {
        this.heureDisponnibilite = heureDisponnibilite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(Integer nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getTypeCarburant() {
        return typeCarburant;
    }

    public void setTypeCarburant(String typeCarburant) {
        this.typeCarburant = typeCarburant;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public void setDateDelete(Date dateDelete) {
        this.dateDelete = dateDelete;
    }

    @ShowTable(name = "Id", numero = 1)
    public String getIdString(){
        return this.getId();
    }

    @ShowTable(name = "Nb place", numero = 2)
    public String getNbPlaceString(){
        return this.getNbPlace().toString();
    }

    @ShowTable(name = "Reference", numero = 3)
    public String getReferenceString(){
        return this.getReference();
    }

    @ShowTable(name = "Type carburant", numero = 4)
    public String getTypeCarburantString(){
        return this.getTypeCarburant();
    }

    @ShowTable(name = "Action", numero = 5)
    public String getActionTab(){
        return "<td>" + //
                        "<div class=\"dropdown\">" + //
                        "<button type=\"button\" class=\"btn btn-primary light sharp\" data-bs-toggle=\"dropdown\">" + //
                        "<svg width=\"20px\" height=\"20px\" viewBox=\"0 0 24 24\" version=\"1.1\"><g stroke=\"none\" stroke-width=\"1\" fill=\"none\" fill-rule=\"evenodd\"><rect x=\"0\" y=\"0\" width=\"24\" height=\"24\"/><circle fill=\"#000000\" cx=\"5\" cy=\"12\" r=\"2\"/><circle fill=\"#000000\" cx=\"12\" cy=\"12\" r=\"2\"/><circle fill=\"#000000\" cx=\"19\" cy=\"12\" r=\"2\"/></g></svg>" + //
                        "</button>" + //
                        "<div class=\"dropdown-menu\">" + //
                        "<a class=\"dropdown-item\" href=\"news-form?id="+this.getId()+"\">Modifier</a>" + //
                        "<a class=\"dropdown-item\" href=\"news-delete?id="+this.getId()+"\">Supprimer</a>" + //
                        "</div>" + //
                        "</div>" + //
                        "</td>";
    }

}
