package TP.Controlls;

import TP.Noyau.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class window extends Stage {

    private static systeme agence ;
    private static ArrayList<Bien> ownerbiens = new ArrayList<Bien>();
    public static ArrayList<Bien> getOwnerbiens() {
        return ownerbiens;
    }

    public window(systeme a){
        this.agence = a;
    }
    public window(){

    }

    public static systeme getAgence() {
        return agence;
    }
    public static void setAgence(systeme agence) {
        window.agence = agence;
    }


    public void aff_firstwindow(systeme a){
        setAgence(a);
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader();
        //administrationController root ;
        try {
            loader.setLocation(getClass().getResource("../UI/adminLogin2.fxml"));
            Pane p = loader.load(getClass().getResource("../UI/adminLogin2.fxml"));
            window.setScene(new Scene(p));
            window.show();

        }catch (Exception e)
        {
            System.out.println("hello");
            e.printStackTrace();
            Platform.exit();
        }
    }

    public void affichLoginMenu(systeme agence)  {
        //setAgence(a);
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader();
        //administrationController root ;
        try {
            loader.setLocation(getClass().getResource("../UI/adminLogin.fxml"));
            Pane p = loader.load();
            window.setScene(new Scene(p));
            window.show();

        }catch (Exception e)
        {
            System.out.println("hello");
        }
    }
    public void affichAdministration(){
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("../UI/administrationMenu.fxml"));
            Pane p = loader.load();
            administrationController a = new administrationController();
            window.setScene(new Scene(p));
            window.show();

        }catch (Exception e)
        {
            System.out.println("hello");
        }
    }
    public void affichFilterEst(){
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("../UI/filterest.fxml"));
            Pane p = loader.load();
            //root.setBiens(biens);
            window.setScene(new Scene(p, 1000, 600));
            window.show();

        }catch (Exception e)
        {
            System.out.println("hello");
        }
    }
    public void affichAddEst(){
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("../UI/addchoix.fxml"));
            Pane p = loader.load();
            //root.setBiens(biens);
            window.setScene(new Scene(p, 400, 200));
            window.show();

        }catch (Exception e)
        {
            System.out.println("nigro");
        }
    }

    public void affichCheckMessages(){
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("../UI/checkmessages.fxml"));
            Pane p = loader.load();
            //root.setBiens(biens);
            window.setScene(new Scene(p, 1000, 600));
            window.show();

        }catch (Exception e)
        {
            System.out.println("hello");
        }
    }
    public void affichShowEst(){
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("../UI/AffichageBien.fxml"));
            Pane p = loader.load();
            //root.setBiens(biens);
            window.setScene(new Scene(p));
            window.show();

        }catch (Exception e)
        {
            System.out.println("hello");
        }
    }
    public void affichShowOwn(){
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("../UI/h.fxml"));
            Pane p = loader.load();
            //root.setBiens(biens);
            window.setScene(new Scene(p, 1000, 600));
            window.show();

        }catch (Exception e)
        {
            System.out.println("hhhhhhhhhhhhhhh");
        }
    }
    public void aff_prop(){
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("../UI/AffichageProp.fxml"));
            Pane p = loader.load();
            //root.setBiens(biens);
            window.setScene(new Scene(p, 1000, 600));
            window.show();

        }catch (Exception e)
        {
            System.out.println("hello");
        }
    }


}
