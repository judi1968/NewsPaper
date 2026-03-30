package controller;

import jframework.annotation.Controller;
import jframework.annotation.GetUrl;
import jframework.tools.ModelView;

@Controller
public class RooterController {


    @GetUrl("/")
    public ModelView home() throws Exception{
        ModelView modelView = new ModelView();        
        modelView.setView("pages/home/home.jsp");
        return modelView ;
    }

}
