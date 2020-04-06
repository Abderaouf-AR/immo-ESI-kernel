package TP.Noyau;

import TP.Noyau.myExceptions.*;
import java.util.*;

public class systeme
{
    /**************************** LES ATTRIBUTS ****************************/
    /** Les biens à valider **/
    static Set<Appartement> t_app_admin = new TreeSet<Appartement>(); static int it_app_admin=0;
    static Set<Maison> t_maisons_admin = new TreeSet<Maison>();static int it_m_admin=0;
    static Set<Terrain> t_terrains_admin = new TreeSet<Terrain>();static int it_t_admin=0;

    /** Les biens courents ( prets à consulter) **/
    static Set<Appartement> t_appartements = new TreeSet<Appartement>(); static int it_app=0;
    static Set<Maison> t_maisons = new TreeSet<Maison>();static int it_m=0;
    static Set<Terrain> t_terrains = new TreeSet<Terrain>();static int it_t=0;

    /** Les biens archivés **/
    static Set<Appartement> t_appartements_arch = new TreeSet<Appartement>();static int it_app_arch=0;
    static Set<Maison> t_maisons_arch = new TreeSet<Maison>(); static int it_m_arch=0;
    static Set<Terrain> t_terrains_arch = new TreeSet<Terrain>();static int it_t_arch=0;

    /** Les biens supprimés **/
    static Set<Appartement> t_appartements_sup = new TreeSet<Appartement>();static int it_app_sup=0;
    static Set<Maison> t_maisons_sup = new TreeSet<Maison>();static int it_m_sup=0;
    static Set<Terrain> t_terrains_sup = new TreeSet<Terrain>(); static int it_t_sup=0;

    /** Les biens loués/vendus/échangés **/
    static Set<Appartement> t_appartements_lve = new TreeSet<Appartement>() ;static int it_app_lve=0;
    static Set<Maison> t_maisons_lve = new TreeSet<Maison>() ; static int it_m_lve=0;
    static Set<Terrain> t_terrains_lve = new TreeSet<Terrain>() ; static int it_t_lve=0;

    /** La listes des proprietaires **/
    public static LinkedHashMap<String,Proprietaire> t_prop = new LinkedHashMap<>(); public static int it_prop=0;

    /** Les message du visiteurs **/
    static ArrayList<String> msg_visiteurs = new ArrayList<String>();

    /** Les autres attributs**/
    public  static TreeMap Wilaya = new TreeMap<Integer, String>();
    public  static Integer[] wilaya_prix = new Integer[48];
    private int int_temp;
    private String string_temp;
    private String string_temp1;
    private String string_temp2;
    private boolean bool_temp=false;
    private Scanner sc = new Scanner(System.in);
    private Admin a;
    private int choix=0;
    /**************************** LES METHODES ****************************/
    public void init_wilaya(){
        Wilaya.put(04, "Oum_el_bouaghi");
        Wilaya.put(05, "Batna");
        Wilaya.put(40, "Khenchla");
        for(int_temp = 0; int_temp != 48; int_temp++){
            wilaya_prix[int_temp]=0;
        }
        wilaya_prix[3]=100000;
        wilaya_prix[4]=30000;
        wilaya_prix[39]=60000;
    }

    public boolean check_prop(String name, String surname) throws PropNotFound {
        if(!(t_prop.containsKey(name+surname))) throw new PropNotFound("     CE NOM ET PRENOM N'EXISTE PAS DANS LE SYSTEME!!!!");
        return true;
    }

    public void supp_prop(String NomProp) throws  PropNotFound{
        if (t_prop.containsKey(NomProp)) {t_prop.remove(NomProp); it_prop--;}
        else throw new PropNotFound(" / LE PROPRIETAIRE N'EXISTE PAS / ");

    }
    public String ajouter_prop(){
        string_temp = "prop"+ Integer.toString(t_prop.size());
        Proprietaire string_temp = null;
        try {
            string_temp = new Proprietaire();
        } catch (InfoPropErronee infoPropErronee) {
            System.out.println(infoPropErronee.getMessage());
            menu_admin();
        } catch (DuplicateProp duplicateProp) {
            System.out.println(duplicateProp.getMessage());
            menu_admin();
        }
        t_prop.put(string_temp.nom+string_temp.prenom, string_temp);
        System.out.println("   LE PROPRIETAIRE EST AJOUTE AVEC SUCCES");
        return string_temp.nom+string_temp.prenom;
    }

    /******* Les menus *******/
    public void menu_prin() {    // la menu principale
        System.out.println("                       *** ImmoESI ***");
        System.out.println(" *                                                              *");
        System.out.println("     Bonjour monsieur/madame sur notre platforme ImmoESI");
        System.out.println("     Veuillez choisir votre mode de connexion: ");
        System.out.println("     1- Visiteur.");
        System.out.println("     2- Proprietaire.");
        System.out.println("     3- Administrateur.");
        System.out.print  ("     Votre choix: ");
        choix = sc.nextInt();
        while (choix<0 || choix>3){
            choix = sc.nextInt();
            System.out.print  ("     !!! Choisissez 1, 2 ou 3 seulement !!! ");
        }
        switch(choix){
            case 1: menu_visiteur();
                break;
            case 2: bool_temp=false;while(!(bool_temp)){
                {
                    System.out.println("    Entrez le nom:");
                    string_temp1 = sc.next();
                    System.out.println("    Entrez le prenom:");
                    string_temp2 = sc.next();
                    System.out.println("    Entrez le mot de passe");
                    string_temp=sc.next();
                    try {
                        if (check_prop(string_temp1 , string_temp2)){
                            if (t_prop.get(string_temp1+string_temp2).getMP().equals(string_temp)){System.out.println("   Connextion avec succée "); bool_temp = true;}
                            else {System.out.println("   Le mot de passe est errone !!"); bool_temp=false;}
                        } else bool_temp = false;
                    } catch (PropNotFound propNotFound) {
                        System.out.println("   Connextion echouée! Verifier le nom ou prenom ");
                        System.out.println(propNotFound.getMessage());
                        bool_temp = false;
                        System.out.println("    Voulez vous reesayer (tapez 1) ou bien aller au menu principale(tapez 0)?");
                        choix = sc.nextInt();
                        while (choix<-1 && choix>2){
                            choix = sc.nextInt();
                            System.out.print  ("     !!! Choisissez 0 ou 1 seulement !!! ");
                        }
                        if (choix == 0) menu_prin();
                    }
                    menu_prop(string_temp1+string_temp2);
                }}
                break;
            case 3: while(!(connect_admin())){
                System.out.println("    Voulez vous reesayer (tapez 1) ou bien aller au menu principale(tapez 0)?");
                choix = sc.nextInt();
                while (choix<-1 && choix>2){
                    choix = sc.nextInt();
                    System.out.print  ("     !!! Choisissez 0 ou 1 seulement !!! ");
                }
                if (choix == 0) menu_prin();
            };
                menu_admin();
                break;
        }
    }
    public void menu_admin() {
        Admin a = new Admin();
        System.out.println("                       *** ImmoESI ***");
        System.out.println(" *                                                              *");
        System.out.println("     Bonjour monsieur/madame l'admin:");
        System.out.println("     à choisir: ");
        System.out.println("     1- Consulter les notification.");
        System.out.println("     2- Afficher la liste des biens.");
        System.out.println("     3- Afficher les prix finaux des biens.");
        System.out.println("     4- Afficher les bien d'un proprietaire.");
        System.out.println("     5- Effectuer une recherche par un critère.");
        System.out.println("     6- Ajouter un bien.");
        System.out.println("     7- Valider les bien.");
        System.out.println("     8- Afficher la liste des proprietaires.");
        System.out.println("     9- Ajouter un proprietaire au systeme.");
        System.out.println("     10- Supprimer un proprietaire du systeme.");
        System.out.println("     11- Afficher la listes des biens archivees.");
        System.out.println("     12- Afficher la listes des biens supprimee.");
        System.out.println("     13- Afficher la listes des biens loués/vendus/échangés");
        System.out.println("     14- Deconnection.");
        System.out.print  ("     Votre choix: ");
        choix = sc.nextInt();
        while (choix<1 && choix>14){
            choix = sc.nextInt();
            System.out.print  ("     !!! Choisissez de 1 jusqu'a 14 seulement !!! ");
        }
        switch(choix) {
            case 1: {
                a.afficher_notif();
                menu_admin();
            }
            break;

            case 2: {
                a.visualiser();
                menu_admin();
            }
            break;

            case 3:
                a.afficher_prix_finaux();
                menu_admin();
                break;

            case 4: {
                System.out.println("        Donner svp le nom du proprietaire");
                string_temp1 = sc.next();
                System.out.println("        Donner svp le prenom du proprietaire");
                string_temp2 = sc.next();
                a.visualiser_liste_bien_prop(string_temp1, string_temp2);
                menu_admin();
            }
            break;

            case 5: {
                System.out.println("    * Choisir un critere pour lancer la recherche");
                System.out.println("    1- Par wilaya.");
                System.out.println("    2- Par Type de bien (appartement, maison ou terrain ).");
                System.out.println("    3- Par type de transaction .");
                System.out.println("    4- Donner un prix min.");
                System.out.println("    5- Donner un prix max.");
                System.out.println("    6- Donner la superficie min.");
                System.out.println("    7- Donner la superficie max.");
                System.out.println("    8- Donner le nombre minimale de pieces.");
                System.out.print("     Votre choix: ");
                choix = sc.nextInt();
                while (choix < 1 && choix > 8) {
                    choix = sc.nextInt();
                    System.out.print("     !!! Choisissez de 1 jusqu'a 8 seulement !!! ");
                }
                switch (choix) {
                    case 1: {
                        System.out.println("    Donner le code de la wilaya:");
                        int_temp = sc.nextInt();
                        recherche(int_temp);
                        menu_admin();
                    }
                    break;
                    case 2: {
                        System.out.println("    Quelle type de bien vous voulez rechercher? (1-appartement, 2-maison, 3-terrain)");
                        choix = sc.nextInt();
                        while (choix<1 && choix>3) {
                            System.out.println("    CHOISISSEZ 1 pour appartement, 2 pour maison, 3 pour terrain SEULEMENT");
                            System.out.println("    Re-entrez ");
                            choix = sc.nextInt();
                        }
                        recherche_b(choix);
                        menu_admin();
                    }
                    break;
                    case 3: {
                        System.out.println("    Quelle type de transaction vous voulez rechercher? (1-vente, 2-echange, 3-location) \n      voter choix:");
                        choix = sc.nextInt();
                        while (choix < 1 || choix > 3) {
                            System.out.println("   !!! Choisissez de 1, 2 ou 3 seulement !!!");
                            choix = sc.nextInt();
                        }
                        switch (choix) {
                            case 1:
                                recherche(Transaction.vente);
                                break;
                            case 2:
                                recherche(Transaction.echange);
                                break;
                            case 3:
                                recherche(Transaction.location);
                                break;
                        }
                        menu_admin();
                    }
                    break;
                    case 4: {
                        System.out.println("    Donner un prix minimale : ");
                        int_temp = sc.nextInt();
                        recherche_prix_min(int_temp);
                        menu_admin();
                    }
                    break;
                    case 5: {
                        System.out.println("    Donner un prix maximale : ");
                        int_temp = sc.nextInt();
                        recherche_prix_max(int_temp);
                        menu_admin();
                    }
                    break;
                    case 6: {
                        System.out.println("    Donner la superficie minimale : ");
                        int_temp = sc.nextInt();
                        recherche_superficie_min(int_temp);
                        menu_admin();
                    }
                    break;
                    case 7: {
                        System.out.println("    Donner la superficie maximale : ");
                        int_temp = sc.nextInt();
                        recherche_superficie_max(int_temp);
                        menu_admin();
                    }
                    break;
                    case 8: {
                        System.out.println("    Donner le nombre max de pieces : ");
                        int_temp = sc.nextInt();
                        recherche_nb_pieces_min(int_temp);
                        menu_admin();
                    }
                    break;
                }
            }
            menu_admin();
            break;

            case 6: {
                System.out.println("    /*/ Pour creer un appartement tapez 1, 2 poue une maison et 3 pour un terrain (0 pour annuler)");
                System.out.print("     Votre choix: ");
                choix = sc.nextInt();
                while (choix < 0 && choix > 3) {
                    choix = sc.nextInt();
                    System.out.print("     !!! Choisissez de 0 jusqu'a 3 seulement !!! ");
                }
                switch (choix) {
                    case 0:
                        menu_admin();
                        break;
                    case 1:
                        a.creer_bien_admin("appartement");
                        break;
                    case 2:
                        a.creer_bien_admin("maison");
                        break;
                    case 3:
                        a.creer_bien_admin("terrain");
                        break;
                }
            }
            menu_admin();
            break;

            case 7: {
                System.out.println("        Pour valider tous les tempons tapez 1, 2 pour juste les appartements, 3 pour les maisons et 4 pour les terrains (0 pour annuler)");
                System.out.print("     Votre choix: ");
                choix = sc.nextInt();
                while (choix < 0 && choix > 4) {
                    choix = sc.nextInt();
                    System.out.print("     !!! Choisissez de 0 jusqu'a 4 seulement !!! ");
                }
                switch (choix) {
                    case 0:
                        menu_admin();
                        break;
                    case 1:
                        a.valider();
                        menu_admin();
                        break;
                    case 3:
                        a.valider_m();
                        menu_admin();
                        break;
                    case 4:
                        a.valider_t();
                        menu_admin();
                        break;
                    case 2:
                        a.valider_app();
                        menu_admin();
                        break;
                }
            }
            break;

            case 8: {
                Set<Map.Entry<String, Proprietaire>> element_t_prop = t_prop.entrySet();
                for (Map.Entry<String, Proprietaire> elem : element_t_prop) {
                    System.out.println("    le proprietaire:");
                    System.out.println("            nom: " + elem.getValue().nom);
                    System.out.println("            prenom: " + elem.getValue().prenom);
                    System.out.println("            l'adress: " + elem.getValue().getAdr());
                    System.out.println("            le num de telephone et l'email: " + elem.getValue().getTel() + " " + elem.getValue().getEmail());
                    System.out.println("\n            1/ supprimer ce proprietaire du systeme.");
                    System.out.println("            2/ Envoyer un msg à ce proprietaire.");
                    System.out.println("            3/ Passer au suivant.");
                    System.out.println("            0/ Retour au menu principale.");
                    System.out.print("    Votre chois:");
                    choix = sc.nextInt();
                    while (choix < 0 && choix > 3) {
                        choix = sc.nextInt();
                        System.out.print("     !!! Choisissez de 0 jusqu'a 3 seulement !!! ");
                    }
                    switch (choix) {
                        case 1:{
                            try {
                                supp_prop(elem.getValue().nom+elem.getValue().prenom);
                            } catch (PropNotFound propNotFound) {
                                propNotFound.getMessage();
                            }
                        }
                        menu_admin();
                        break;
                        case 0:
                            menu_admin();
                            break;
                        case 2: {
                            System.out.println("     Ecrire le message: ");
                            string_temp=sc.next();
                            a.contact_prop( elem.getKey(), string_temp);
                        }
                        menu_admin();
                        break;
                    }
                }
            }
            menu_admin();
            break;

            case 9:{
                System.out.println("        L'ajout d'un proprietaire (inserez les infos correctement svp)");
                string_temp=ajouter_prop();
                menu_admin();
            }
            break;

            case 10:{
                System.out.println("        Donner le nom du proprietaire que vous voulez supprimer:");
                string_temp1=sc.next();
                System.out.println("        et le prenom svp:");
                string_temp2=sc.next();
                try {
                    supp_prop(string_temp1+string_temp2);
                }
                catch (PropNotFound e){
                    System.out.println(e.getMessage());
                }
            }
            menu_admin();
            break;

            case 11: {
                System.out.println("     **//** Tapez 1 pour voir toute les listes d'archive, 2 pour juste les appartement, 3 pour juste les maison et 4 pour juste les  terrains ");
                System.out.print("     Votre choix: ");
                choix = sc.nextInt();
                while (choix < 1 && choix > 4) {
                    choix = sc.nextInt();
                    System.out.print("     !!! Choisissez de 1 jusqu'a 4 seulement !!! ");
                }
                switch (choix) {
                    case 1:
                        a.visualiser_archive();
                        menu_admin();
                        break;
                    case 2:
                        a.visualiser_archive("appartement");
                        menu_admin();
                        break;
                    case 3:
                        a.visualiser_archive("maison");
                        menu_admin();
                        break;
                    case 4:
                        a.visualiser_archive("terrain");
                        menu_admin();
                        break;
                }
            }
            break;

            case 12: {
                System.out.println("     **//** Tapez 1 pour voir toute le liste des bien supprimes, 2 pour juste les appartement, 3 pour juste les maison et 4 pour juste les  terrains ");
                System.out.print("     Votre choix: ");
                choix = sc.nextInt();
                while (choix < 1 && choix > 4) {
                    choix = sc.nextInt();
                    System.out.print("     !!! Choisissez de 1 jusqu'a 4 seulement !!! ");
                }
                switch (choix) {
                    case 1:
                        a.visualiser_biens_sup();
                        menu_admin();
                        break;
                    case 2:
                        a.visualiser_biens_sup("appartement");
                        menu_admin();
                        break;
                    case 3:
                        a.visualiser_biens_sup("maison");
                        menu_admin();
                        break;
                    case 4:
                        a.visualiser_biens_sup("terrain");
                        menu_admin();
                        break;
                }
            }
            break;

            case 13:
                break;

            case 14:menu_prin();
                break;
        }

    }
    public void menu_visiteur(){
        System.out.println("                       *** ImmoESI ***");
        System.out.println(" *                                                              *");
        System.out.println("     Bonjour monsieur/madame :");
        System.out.println("     à choisir: ");
        System.out.println("     1- Afficher la liste des biens.");
        System.out.println("     2- Afficher les prix finaux des biens.");
        System.out.println("     3- Effectuer une recherche par un critère.");
        System.out.println("     4- Ajouter un bien.");
        System.out.println("     5- Devenir un proprietaire.");
        System.out.println("     6- Deconnection.");
        System.out.print  ("     Votre choix: ");
        choix = sc.nextInt();
        while (choix<1 && choix>6){
            choix = sc.nextInt();
            System.out.print  ("     !!! Choisissez de 1 jusqu'a 6 seulement !!! ");
        }
        switch (choix){
            case 6: menu_prin();break;
            case 1: consulter();
                menu_visiteur();
                break;
            case 2: afficher_prix_finaux();
                menu_visiteur();
                break;
            case 3:{
                System.out.println("    * Choisir un critere pour lancer la recherche");
                System.out.println("    1- Par wilaya.");
                System.out.println("    2- Par Type de bien (appartement, maison ou terrain ).");
                System.out.println("    3- Par type de transaction .");
                System.out.println("    4- Donner un prix min.");
                System.out.println("    5- Donner un prix max.");
                System.out.println("    6- Donner la superficie min.");
                System.out.println("    7- Donner la superficie max.");
                System.out.println("    8- Donner le nombre minimale de pieces.");
                System.out.print("     Votre choix: ");
                choix = sc.nextInt();
                while (choix < 1 && choix > 8) {
                    choix = sc.nextInt();
                    System.out.print("     !!! Choisissez de 1 jusqu'a 8 seulement !!! ");
                }
                switch (choix) {
                    case 1: {
                        System.out.println("    Donner le code de la wilaya:");
                        int_temp = sc.nextInt();
                        recherche(int_temp);
                    }
                    break;
                    case 2: {
                        System.out.println("    Quelle type de bien vous voulez rechercher? (1-appartement, 2-maison, 3-terrain)");
                        choix = sc.nextInt();
                        while (choix<1 && choix>3) {
                            System.out.println("    CHOISISSEZ 1 pour appartement, 2 pour maison, 3 pour terrain SEULEMENT");
                            System.out.println("    Re-entrez ");
                            choix = sc.nextInt();
                        }
                        recherche_b(choix);
                    }
                    break;
                    case 3: {
                        System.out.println("    Quelle type de transaction vous voulez rechercher? (1-vente, 2-echange, 3-location) \n      voter choix:");
                        choix = sc.nextInt();
                        while (choix < 1 || choix > 3) {
                            System.out.println("   !!! Choisissez de 1, 2 ou 3 seulement !!!");
                            choix = sc.nextInt();
                        }
                        switch (choix) {
                            case 1:
                                recherche(Transaction.vente);
                                break;
                            case 2:
                                recherche(Transaction.echange);
                                break;
                            case 3:
                                recherche(Transaction.location);
                                break;
                        }
                    }
                    break;
                    case 4: {
                        System.out.println("    Donner un prix minimale : ");
                        int_temp = sc.nextInt();
                        recherche_prix_min(int_temp);
                    }
                    break;
                    case 5: {
                        System.out.println("    Donner un prix maximale : ");
                        int_temp = sc.nextInt();
                        recherche_prix_max(int_temp);
                    }
                    break;
                    case 6: {
                        System.out.println("    Donner la superficie minimale : ");
                        int_temp = sc.nextInt();
                        recherche_superficie_min(int_temp);
                    }
                    break;
                    case 7: {
                        System.out.println("    Donner la superficie maximale : ");
                        int_temp = sc.nextInt();
                        recherche_superficie_max(int_temp);
                    }
                    break;
                    case 8: {
                        System.out.println("    Donner le nombre max de pieces : ");
                        int_temp = sc.nextInt();
                        recherche_nb_pieces_min(int_temp);
                    }
                    break;
                }
            }
            menu_visiteur();
            break;
            case 5: string_temp=a.ajouter_prop();
                menu_prop(string_temp);
                break;
            case 4: {
                bool_temp=false;while(!(bool_temp)){
                    {
                        System.out.println("    Entrez le nom:");
                        string_temp1 = sc.next();
                        System.out.println("    Entrez le prenom:");
                        string_temp2 = sc.next();
                        try {
                            if (check_prop(string_temp1 , string_temp2)){
                                if (t_prop.get(string_temp1+string_temp2).getMP().equals(string_temp)){System.out.println("   Connextion avec succée "); bool_temp = true;}
                                else {System.out.println("   Le mot de passe est errone !!"); bool_temp=false;}
                            } else bool_temp = false;
                        } catch (PropNotFound propNotFound) {
                            System.out.println("   Connextion echouée! Verifier le nom ou prenom ");
                            System.out.println(propNotFound.getMessage());
                            bool_temp = false;
                            System.out.println("    Voulez vous reesayer (tapez 1) ou bien aller au menu principale(tapez 0)? si vous n'etes pas encore inscrit dans le systeme te vous voulez nous rejoindre comme propietaire\n    tapez 0 puis 5");
                            choix = sc.nextInt();
                            while (choix<0 || choix>1){
                                choix = sc.nextInt();
                                System.out.print  ("     !!! Choisissez 0 ou 1 seulement !!! ");
                            }
                            if (choix == 0) menu_visiteur();
                        }
                        menu_prop(string_temp1+string_temp2);
                    }}
            }
            break;
        }
    }
    public void menu_prop(String NomPrenom){
        System.out.println("                       *** ImmoESI ***");
        System.out.println(" *                                                              *");
        System.out.println("     Bonjour monsieur/madame :");
        System.out.println("     à choisir: ");
        System.out.println("     1- Afficher mes notification.");
        System.out.println("     2- Afficher la liste de mes biens");
        System.out.println("     3- Afficher la liste des biens.");
        System.out.println("     4- Afficher les prix finaux des biens.");
        System.out.println("     5- Effectuer une recherche par un critère.");
        System.out.println("     6- Ajouter un bien.");
        System.out.println("     7- Deinscrire");
        System.out.println("     0- Deconnection");
        System.out.print  ("     Votre choix: ");
        choix = sc.nextInt();
        while (choix<0 && choix>7){
            choix = sc.nextInt();
            System.out.print  ("     !!! Choisissez de 0 jusqu'a 7 seulement !!! ");
        }
        switch (choix){
            case 0: menu_prin();
                break;
            case 1:{
                t_prop.get(NomPrenom).afficher_notif();
                menu_prop(NomPrenom);
            }
            break;
            case 2:{
                t_prop.get(NomPrenom).afficher_mes_biens();
                menu_prop(NomPrenom);
            }
            break;
            case 3: consulter();
                menu_prop(NomPrenom);
                break;
            case 5:{
                System.out.println("    * Choisir un critere pour lancer la recherche");
                System.out.println("    1- Par wilaya.");
                System.out.println("    2- Par Type de bien (appartement, maison ou terrain ).");
                System.out.println("    3- Par type de transaction .");
                System.out.println("    4- Donner un prix min.");
                System.out.println("    5- Donner un prix max.");
                System.out.println("    6- Donner la superficie min.");
                System.out.println("    7- Donner la superficie max.");
                System.out.println("    8- Donner le nombre minimale de pieces.");
                System.out.print("     Votre choix: ");
                choix = sc.nextInt();
                while (choix < 1 && choix > 8) {
                    choix = sc.nextInt();
                    System.out.print("     !!! Choisissez de 1 jusqu'a 8 seulement !!! ");
                }
                switch (choix) {
                    case 1: {
                        System.out.println("    Donner le code de la wilaya:");
                        int_temp = sc.nextInt();
                        recherche(int_temp);
                    }
                    break;
                    case 2: {
                        System.out.println("    Quelle type de bien vous voulez rechercher? (1-appartement, 2-maison, 3-terrain)");
                        choix = sc.nextInt();
                        while (choix<1 && choix>3) {
                            System.out.println("    CHOISISSEZ 1 pour appartement, 2 pour maison, 3 pour terrain SEULEMENT");
                            System.out.println("    Re-entrez ");
                            choix = sc.nextInt();
                        }
                        recherche_b(choix);
                    }
                    break;
                    case 3: {
                        System.out.println("    Quelle type de transaction vous voulez rechercher? (1-vente, 2-echange, 3-location) \n      voter choix:");
                        choix = sc.nextInt();
                        while (choix < 1 || choix > 3) {
                            System.out.println("   !!! Choisissez de 1, 2 ou 3 seulement !!!");
                            choix = sc.nextInt();
                        }
                        switch (choix) {
                            case 1:
                                recherche(Transaction.vente);
                                break;
                            case 2:
                                recherche(Transaction.echange);
                                break;
                            case 3:
                                recherche(Transaction.location);
                                break;
                        }
                    }
                    break;
                    case 4: {
                        System.out.println("    Donner un prix minimale : ");
                        int_temp = sc.nextInt();
                        recherche_prix_min(int_temp);
                    }
                    break;
                    case 5: {
                        System.out.println("    Donner un prix maximale : ");
                        int_temp = sc.nextInt();
                        recherche_prix_max(int_temp);
                    }
                    break;
                    case 6: {
                        System.out.println("    Donner la superficie minimale : ");
                        int_temp = sc.nextInt();
                        recherche_superficie_min(int_temp);
                    }
                    break;
                    case 7: {
                        System.out.println("    Donner la superficie maximale : ");
                        int_temp = sc.nextInt();
                        recherche_superficie_max(int_temp);
                    }
                    break;
                    case 8: {
                        System.out.println("    Donner le nombre max de pieces : ");
                        int_temp = sc.nextInt();
                        recherche_nb_pieces_min(int_temp);
                    }
                    break;
                }
            }
            menu_prop(NomPrenom);
            break;
            case 6:{
                System.out.println("    /*/ Pour creer un appartement tapez 1, 2 poue une maison et 3 pour un terrain (0 pour annuler)");
                System.out.print("     Votre choix: ");
                choix = sc.nextInt();
                while (choix < 0 && choix > 3) {
                    choix = sc.nextInt();
                    System.out.print("     !!! Choisissez de 0 jusqu'a 3 seulement !!! ");
                }
                switch (choix) {
                    case 0:
                        menu_prop(NomPrenom);
                        break;
                    case 1:
                        t_prop.get(NomPrenom).ajouter_app("bien"+Integer.toString(int_temp));
                        System.out.println("    Votre bien est enregistré, il reste que la validation de l'agence.   MERCI");
                        menu_prop(NomPrenom);
                        break;
                    case 2:
                        t_prop.get(NomPrenom).ajouter_maison("bien"+Integer.toString(int_temp));
                        System.out.println("    Votre bien est enregistré, il reste que la validation de l'agence.   MERCI");
                        menu_prop(NomPrenom);
                        break;
                    case 3:
                        t_prop.get(NomPrenom).ajouter_terrain("bien"+Integer.toString(int_temp));
                        System.out.println("    Votre bien est enregistré, il reste que la validation de l'agence.   MERCI");
                        menu_prop(NomPrenom);
                        break;
                }
            }
            menu_prop(NomPrenom);
            break;
            case 4: afficher_prix_finaux();
                menu_prop(NomPrenom);
                break;
            case 7:{
                System.out.println("   Est ce que vous etes sur? (appuiez sur 1 pour confirmer)");
                int_temp=sc.nextInt();
                if (int_temp==1){
                    System.out.println("   C'est bon :(");
                    t_prop.remove(NomPrenom);
                    menu_prin();
                }else menu_prop(NomPrenom);
            }
            break;
        }
    }

    public boolean connect_prop(){
        System.out.println("    Entrez le nom:");
        string_temp1 = sc.next();
        System.out.println("    Entrez le prenom:");
        string_temp2 = sc.next();
        try {
            if (check_prop(string_temp1 , string_temp2)){
                System.out.println("   Connextion avec succée ");
                return true;
            } else return false;
        } catch (PropNotFound propNotFound) {
            System.out.println("   Connextion echouée! Verifier le nom ou prenom ");
            System.out.println(propNotFound.getMessage());
            return false;
        }
    }
    public boolean connect_admin(){
        Admin a = new Admin();
        System.out.println("    Entrez le nom d'utilisateur:");
        string_temp1 = sc.next();
        System.out.println("    Entrez le mot de passe:");
        string_temp2 = sc.next();
        if (string_temp1.equals(a.NomUtilisateur) && string_temp2.equals(a.MP)){
            System.out.println("   Connextion avec succée ");
            return true;
        }
        else{
            System.out.println("   Connextion echouée! Verifier le nom ou prenom ");
            return false;
        }
    }

    /******* Les methodes de la recherche *******/
    public void consulter(){
        consulter("appartement");
        consulter("maison");
        consulter("terrain");
    }
    public void consulter(String s){
        switch (s){
            case "appartement":{
                System.out.println("     Les appartements:");
                for(Appartement a : t_appartements) {
                    a.afficher();
                    System.out.println("     à choisir:");
                    System.out.println("     1- Voir plus.");
                    System.out.println("     2- Contacter l'agence pour ce bien num "+ a.num_bien_actu);
                    System.out.println("     3-Passer au suivant.");
                    System.out.println("     0-Retour au menu principale.");
                    int_temp=sc.nextInt();
                    while (int_temp<0 && int_temp>3){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 0, 1, 2 ou 3 seulement !!! ");
                    }
                    switch(int_temp){
                        case 1: a.afficher_plus();
                            break;
                        case 2: a.contacter_agc();
                            break;
                        case 0: menu_visiteur();
                            break;
                    }
                }
            }
            break;
            case "maison":{
                System.out.println("     Les maisons:");
                for(Maison a : t_maisons) {
                    a.afficher();
                    System.out.println("     à choisir:");
                    System.out.println("     1- Voir plus.");
                    System.out.println("     2- Contacter l'agence pour ce bien num "+ a.num_bien_actu);
                    System.out.println("     3-Passer au suivant.");
                    System.out.println("     0-Retour au menu principale.");
                    int_temp=sc.nextInt();
                    while (int_temp<0 && int_temp>3){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 0, 1, 2 ou 3 seulement !!! ");
                    }
                    switch(int_temp){
                        case 1: a.afficher_plus();
                            break;
                        case 2: a.contacter_agc();
                            break;
                        case 0: menu_visiteur();
                            break;
                    }
                }
            }
            break;
            case "terrain":{
                System.out.println("     Les terrains:");
                for(Terrain a : t_terrains) {
                    a.afficher();
                    System.out.println("     à choisir:");
                    System.out.println("     1- Voir plus.");
                    System.out.println("     2- Contacter l'agence pour ce bien num "+ a.num_bien_actu);
                    System.out.println("     3-Passer au suivant.");
                    System.out.println("     0-Retour au menu principale.");
                    int_temp=sc.nextInt();
                    while (int_temp<0 && int_temp>3){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 0, 1, 2 ou 3 seulement !!! ");
                    }
                    switch(int_temp){
                        case 1: a.afficher_plus();
                            break;
                        case 2: a.contacter_agc();
                            break;
                        case 0: menu_visiteur();
                            break;
                    }
                }
            }
            break;
        }
    }
    public void afficher_prix_finaux(){
        afficher_prix_finaux("appartement");
        afficher_prix_finaux("maison");
        afficher_prix_finaux("terrain");
    }
    public void afficher_prix_finaux(String s){
        switch(s){
            case "appartement":
                System.out.println("     Les appartements:");
                for(Appartement v : t_appartements) {
                    System.out.println("        */* L'appartement "+v.num_bien_actu);
                    System.out.println("            Le prix : "+ v.prix_final);
                    System.out.println("\n     1- Passer au suivant.");
                    System.out.println("     2- Retoiur au menu precedante.");
                    System.out.print("     Votre choix: ");
                    int_temp=sc.nextInt();
                    while (int_temp<1 && int_temp>2){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 1 ou 2 seulement !!! ");
                    }
                    if ( int_temp==2) menu_admin();
                }
                break;
            case "maison":
                System.out.println("     Les maisons:");
                for(Maison v : t_maisons) {
                    System.out.println("        */* La maison "+v.num_bien_actu);
                    System.out.println("            Le prix : "+ v.prix_final);
                    System.out.println("\n     1- Passer au suivant.");
                    System.out.println("     2- Retoiur au menu precedante.");
                    System.out.print("     Votre choix: ");
                    int_temp=sc.nextInt();
                    while (int_temp<1 && int_temp>2){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 1 ou 2 seulement !!! ");
                    }
                    if ( int_temp==2) menu_admin();
                }
                break;
            case "terrain":
                System.out.println("     Les terrains:");
                for(Terrain v : t_terrains) {
                    System.out.println("        */* Le terrain "+v.num_bien_actu);
                    System.out.println("            Le prix : "+ v.prix_final);
                    System.out.println("\n     1- Passer au suivant.");
                    System.out.println("     2- Retoiur au menu precedante.");
                    System.out.print("     Votre choix: ");
                    int_temp=sc.nextInt();
                    while (int_temp<1 && int_temp>2){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 1 ou 2 seulement !!! ");
                    }
                    if ( int_temp==2) menu_admin();
                }
                break;
        }
    }
    /** simple avec un seul critère**/
    public void recherche(int wilaya_code){
        while(!(Wilaya.containsKey(wilaya_code))){
            System.out.println("    IL N'Y A PAS UNE WULAYA AVEC LE CODE " +wilaya_code +" !!");
            System.out.println("    Re-entrez le code de la wilaya svp:");
            wilaya_code = sc.nextInt();
        }
        System.out.println("     Les appartements siuées dans " + Wilaya.get(wilaya_code) + ":");
        for(Appartement a : t_appartements) if (a.code_wilaya == wilaya_code){a.afficher();System.out.println("*/*/*/*/*/*");}
        System.out.println("     Les maisons siuées dans " + Wilaya.get(wilaya_code) + ":");
        for(Maison a : t_maisons) if (a.code_wilaya == wilaya_code){a.afficher();System.out.println("*/*/*/*/*/*");}
        System.out.println("     Les terrains siuées dans " + Wilaya.get(wilaya_code) + ":");
        for(Terrain a : t_terrains) if (a.code_wilaya == wilaya_code){a.afficher();System.out.println("*/*/*/*/*/*");}
    }
    public void recherche_b( int i){
        switch(i){
            case 1: System.out.println("     Les appartements:");
                for(Appartement a : t_appartements) a.afficher();
                break;
            case 2:      System.out.println("     Les maisons:");
                for(Maison a : t_maisons) a.afficher();
                break;
            case 3:     System.out.println("     Les terrains:");
                for(Terrain a : t_terrains) a.afficher();
                break;
        }
    }
    public void recherche(Transaction transaction){
        System.out.println("     Les appartements:");
        for(Appartement a : t_appartements) if (a.trans == transaction){a.afficher();}
        System.out.println("     Les maisons:");
        for(Maison a : t_maisons) if (a.trans == transaction){a.afficher();}
        System.out.println("     Les terrains:");
        for(Terrain a : t_terrains) if (a.trans == transaction){a.afficher();}
    }
    public void recherche_prix_min(int prix_min){
        System.out.println("     Les appartements:");
        for(Appartement a : t_appartements) if (a.prix_final > prix_min){a.afficher();}
        System.out.println("     Les maisons:");
        for(Maison a : t_maisons) if (a.prix_final > prix_min){a.afficher();}
        System.out.println("     Les terrains:");
        for(Terrain a : t_terrains) if (a.prix_final > prix_min){a.afficher();}
    }
    public void recherche_prix_max(int prix_max){
        System.out.println("     Les appartements:");
        for(Appartement a : t_appartements) if (a.prix_final < prix_max){a.afficher();}
        System.out.println("     Les maisons:");
        for(Maison a : t_maisons) if (a.prix_final < prix_max){a.afficher();}
        System.out.println("     Les terrains:");
        for(Terrain a : t_terrains) if (a.prix_final < prix_max){a.afficher();}
    }
    public void recherche_superficie_min(int superficie_min){
        System.out.println("     Les appartements:");
        for(Appartement a : t_appartements) if (a.superficie > superficie_min){a.afficher();}
        System.out.println("     Les maisons:");
        for(Maison a : t_maisons) if (a.superficie > superficie_min){a.afficher();}
        System.out.println("     Les terrains:");
        for(Terrain a : t_terrains) if (a.superficie > superficie_min){a.afficher();}
    }
    public void recherche_superficie_max(int superficie_max){
        System.out.println("     Les appartements:");
        for(Appartement a : t_appartements) if (a.superficie < superficie_max){a.afficher();}
        System.out.println("     Les maisons:");
        for(Maison a : t_maisons) if (a.superficie < superficie_max){a.afficher();}
        System.out.println("     Les terrains:");
        for(Terrain a : t_terrains) if (a.superficie < superficie_max){a.afficher();}
    }
    public void recherche_nb_pieces_min(int nb_pieces_min){
        System.out.println("     Les appartements:");
        for(Appartement a : t_appartements) if (a.nb_pieces > nb_pieces_min){a.afficher();}
        System.out.println("     Les maisons:");
        for(Maison a : t_maisons) if (a.nb_pieces > nb_pieces_min){a.afficher();}
    }
/** simple avec un deux critère**/

    public int getNb_bien(){
        return t_appartements.size()+t_maisons.size()+t_terrains.size();
    }
    public int getNb_prop(){ return t_prop.size();}
    public int getNb_notif(){return msg_visiteurs.size();}
    public String getNotif(int n){return msg_visiteurs.get(n);}
    public Appartement getApp(int n){
        for (Appartement appa : t_appartements){
        return appa;
        }
        return null;
    }
    public Proprietaire getProp(){
        return t_prop.get(0);
    }
    public void setmsg(String s){ msg_visiteurs.add(s);}
}
