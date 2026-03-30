package controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jframework.annotation.API;
import jframework.annotation.Authorized;
import jframework.annotation.Controller;
import jframework.annotation.FormatApi;
import jframework.annotation.GetUrl;
import jframework.annotation.PostUrl;
import jframework.annotation.Url;
import jframework.session.Session;
import jframework.tools.ModelView;
import model.table.Vehicule;
import pja.databases.MyConnection;
import pja.databases.generalisation.DB;
import jframework.annotation.RequestParam;
import jframework.annotation.Role;

@Controller
public class VehiculeController {


    @GetUrl("/vehicule-form")
    public ModelView vehiculeForm(String id) throws Exception{
        ModelView modelView = new ModelView();
        Connection connection = MyConnection.connect();
        Vehicule vehicule = null;
        if (id != null) {
            vehicule = (Vehicule) DB.getById(new Vehicule(), id, connection); 
        }
        modelView.setView("pages/vehicule/vehicule-form.jsp");
        modelView.addData("vehicule", vehicule);
        connection.close();
        return modelView ;
    }

    @GetUrl("akory")
    public ModelView akory() throws Exception{
        ModelView modelView = new ModelView();
        modelView.setView("pages/vehicule/akory.jsp");
        return modelView ;
    }

    @PostUrl("/vehicule")
    public ModelView saveVehicule(Vehicule vehicule) throws Exception{
        ModelView modelView = new ModelView();
        Connection connection = null;
        try {
            connection = MyConnection.connect();
            connection.setAutoCommit(false);
            String message = "";
            if (vehicule.getId() == null) {
                message = "Creation effectue";
            }else{
                message = "Modification effectue";
            }
            DB.save(vehicule, connection);
            connection.commit();
            modelView.addData("success", message);
        } catch (Exception e) {
            e.printStackTrace();
            modelView.addData("error", e.getMessage());
        }
        modelView.setView("pages/vehicule/vehicule-form.jsp");
        return modelView ;
    }


    @GetUrl("/vehicule-list")
    public ModelView vehiculeList() throws Exception{
        Connection connection = null;
        ModelView modelView = new ModelView();
        connection = MyConnection.connect();
        List<Vehicule> vehicules = (List<Vehicule>) DB.getAllWhere(new Vehicule(), " date_delete is null ", connection);
        String vehiculeTab = DB.getTableau(vehicules, new Vehicule(), "Liste de tout les vehicules", "");
        modelView.addData("vehiculeTab", vehiculeTab);
        modelView.setView("pages/vehicule/vehicule-list.jsp");
        connection.close();
        return modelView;
    }

    @GetUrl("/vehicule-delete")
    public ModelView deleteVehicule(String id) throws Exception{
        ModelView modelView = new ModelView();
        Connection connection = null;
        try {
            connection = MyConnection.connect();
            connection.setAutoCommit(false);
            String message = "";
            Vehicule vehicule = (Vehicule) DB.getById(new Vehicule(), id, connection);
            if (vehicule == null) {
                throw new Exception("Le vehicule id : '"+id+"' n'existe pas");
            }
            vehicule.setDateDelete(new java.util.Date());

            DB.save(vehicule, connection);
            message = "Suppression de vehicule id '"+id+"' effectue";
            connection.commit();
            modelView.addData("success", message);
        } catch (Exception e) {
            e.printStackTrace();
            modelView.addData("error", e.getMessage());
        }
        List<Vehicule> vehicules = (List<Vehicule>) DB.getAllWhere(new Vehicule(), " date_delete is null ", connection);
        String vehiculeTab = DB.getTableau(vehicules, new Vehicule(), "Liste de tout les vehicules", "");
        connection.close();
        modelView.addData("vehiculeTab", vehiculeTab);
        modelView.setView("pages/vehicule/vehicule-list.jsp");
        return modelView ;
    }

}
