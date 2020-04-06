package TP;

import TP.Controlls.window;
import TP.Noyau.*;
import TP.Noyau.myExceptions.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import jdk.nashorn.internal.ir.SwitchNode;

import java.util.InputMismatchException;

public class Main extends Application {
    static systeme sys = new systeme();
    TP.Controlls.window w = new TP.Controlls.window(sys);
    @Override
    public void start(Stage primaryStage) throws Exception{
        //TP.Controlls.window w = new TP.Controlls.window(sys);
        //w.affichLoginMenu(sys);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        //w.aff_firstwindow(sys);
    }


    public static void main(String[] args) {
        try {
        //launch(args);
        Terrain t = new Terrain("d");
        /************ La creation des proprietaires ************/
        Proprietaire prop1 = new Proprietaire("Aroua", "Abderaouf", "M42 Bouraoui", "arar@gmail.com", 658908795, "0000");
        Proprietaire prop2 = new Proprietaire("Aroua", "Haithem", "N60 Bouraoui", "arar2@gmail.com", 553860821, "0000");
        Proprietaire prop3 = new Proprietaire("Aroua", "Abdelhalim", "L40 Bouraoui", "arar3@gmail.com", 664794294, "0000");
        Proprietaire prop4 = new Proprietaire("Aroua", "Fateh", "A100 Bouraoui", "arar4@gmail.com", 661243569, "0000");

        /************ La creation des biens ************/
        /*** appartements***/
        prop2.ajouter_app("Bien1", Transaction.vente, 4000000, "non", 1, 5, 120, 4);
        prop2.ajouter_app("Bien4", Transaction.location, 40000, "non", 1, 40, 100, 3);
        prop2.ajouter_app("Bien6", Transaction.location, 60000, "non", 6, 5, 50, 1);

        /*** maisons***/
        prop1.ajouter_maison("Bien2", Transaction.vente, 10000000, 40, "jardin", 200);
        prop3.ajouter_maison("Bien5", Transaction.location, 150000, 5, "piscine", 160);
        prop2.ajouter_maison("Bien8", Transaction.echange, 14000000, 5, "garage", 200, 5);
        /*** terrains***/
        prop1.ajouter_terrain("Bien3", Transaction.vente, 20000000, 4, 500, 3, 0);
        prop1.ajouter_terrain("Bien7", Transaction.echange, 18000000, 4, 650, 1, 40);

        sys.init_wilaya();
        sys.t_prop.put(prop1.nom + prop1.prenom, prop1);
        sys.it_prop++;
        sys.t_prop.put(prop2.nom + prop2.prenom, prop2);
        sys.it_prop++;
        sys.t_prop.put(prop3.nom + prop3.prenom, prop3);
        sys.it_prop++;
        sys.t_prop.put(prop4.nom + prop4.prenom, prop4);
        sys.it_prop++;
        Bien.init_wilaya();

        Admin a = new Admin();
        a.valider();
        sys.setmsg("     Message de test  tp poo");
            launch(args);
        //sys.menu_prin();
    }
        catch(InputMismatchException d ){
        System.out.println(" NE PAS ENTREZ DES CARACTERES A LA PLACE D UN CHIFFRE SVP.... VOUS DEVER RECOMMENCER LE TRAITEMENT");
    }
    }
}
