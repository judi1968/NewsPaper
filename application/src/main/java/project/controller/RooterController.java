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
import pja.databases.MyConnection;
import pja.databases.generalisation.DB;
import jframework.annotation.RequestParam;
import jframework.annotation.Role;

@Controller
public class RooterController {


    @GetUrl("/")
    public ModelView home() throws Exception{
        ModelView modelView = new ModelView();        
        modelView.setView("pages/home/home.jsp");
        return modelView ;
    }

}
