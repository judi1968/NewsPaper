package controller;

import java.sql.Connection;
import java.util.List;

import jframework.annotation.Controller;
import jframework.annotation.GetUrl;
import jframework.annotation.PostUrl;
import jframework.tools.ModelView;
import jframework.web.JFile;
import model.table.News;
import model.dto.NewsDTO;
import pja.databases.MyConnection;
import pja.databases.generalisation.DB;

@Controller
public class NewsController {


    @GetUrl("/news")
    public ModelView list() throws Exception{
        ModelView modelView = new ModelView();
        Connection connection = null;
        connection = MyConnection.connect();
        List<News> news = (List<News>) DB.getAll(new News(), connection);
        connection.close();
        String newsList = (String) DB.getTableau(news, new News(), "Liste des nouvelles", "");
        modelView.addData("newsList", newsList);
        modelView.setView("pages/back-office/news-list.jsp");
        return modelView ;
    }

    @GetUrl("/news-form")
    public ModelView showNewsForm() {
        ModelView modelView = new ModelView();
        // Vérifier et initialiser chaque attribut
        modelView.setView("pages/back-office/news-form.jsp");
        return modelView;
    }

    @PostUrl("/news")
    public ModelView saveOrUpdateNews(NewsDTO news) throws Exception {
        ModelView modelView = new ModelView();
        Connection connection = null;
        
        try {
            connection = MyConnection.connect();
            connection.setAutoCommit(false);
            News newsObject = new News();
            newsObject.setContenu(news.getContenu());
            newsObject.setDatePublication(news.getDatePublication());
            newsObject.setAltImagesCouverture(news.getAlternativeCouverture());
            newsObject.buildTitle();
            newsObject.buildHref();
            // Gérer l'image de couverture (enregistrer le fichier et stocker le chemin)
            JFile image = news.getImageCouverture();
            image.transferTo("/usr/local/tomcat/webapps/uploads/"+image.getOriginalFilename()); // Assurez-vous de définir le chemin de sauvegarde
            newsObject.setImagesCouverture("/uploads/"+image.getOriginalFilename()); // Stocker le chemin de l
            System.out.println("tonga teto de nety /usr/local/tomcat/webapps/uploads/"+newsObject.getImagesCouverture());
            String message = "";
           /*  if (news.getId() == null) {*/
                message = "Actualité créée avec succès !";
            /* } else {
                message = "Actualité modifiée avec succès !";
            }*/
            
            // Sauvegarde avec votre DB.save
            DB.save(newsObject, connection);
            connection.commit();
            
            modelView.addData("success", message);
            
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
            modelView.addData("error", "Erreur : " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        modelView.setView("pages/back-office/news-form.jsp");
        return modelView ;

    }

}
