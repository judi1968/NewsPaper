package controller;

import java.sql.Connection;
import java.util.List;

import jframework.annotation.Controller;
import jframework.annotation.GetUrl;
import jframework.tools.ModelView;
import model.table.News;
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

}
