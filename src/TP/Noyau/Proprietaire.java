package TP.Noyau;

import TP.Noyau.myExceptions.*;


import java.util.*;

public class Proprietaire extends systeme implements Comparable<Proprietaire>
{
    /**************************** LES ATTRIBUTS ****************************/
    public String nom="";                                    // le nom du proprietaire
    public String prenom="";                                 // le prenom du proprietaire
    public String adr="";                                   // l'adresse du proprietaire
    public String email="";                                 // l'@ mail du proprietaire
    public long tel=658908795;                              // le numero de telephone du proprietaire
    private String MP="";
    public ArrayList<String> notifications = new ArrayList<>();
    TreeSet<Appartement> liste_bien_app = new TreeSet<Appartement>();          // la liste des bien qu'il possède dans le système
    TreeSet<Maison> liste_bien_m = new TreeSet<Maison>();
    TreeSet<Terrain> liste_bien_t = new TreeSet<Terrain>();
    private Scanner sc = new Scanner(System.in);
    private String string_temp;
    private String string_temp3;
    private int int_temp=0;
    private int i;

    /**************************** LES METHODES ****************************/
    public Proprietaire(String nom, String prenom, String adr, String email, long phone, String mp){
        this.nom=nom;
        this.prenom=prenom;
        this.adr=adr;
        this.tel=phone;
        this.email=email;
        this.MP=mp;
    }

    public Proprietaire() throws InfoPropErronee, DuplicateProp {
        creer_prop();
    }
    public void creer_prop() throws InfoPropErronee, DuplicateProp{
        System.out.println("    Veuillez inserer les information suivantes");
        System.out.println("    Le nom:");
        nom = sc.next();

        System.out.println("    Le prenom:");
        prenom = sc.next();

        System.out.println("    L'adresse mail:");
        email = sc.next();

        System.out.println("    Le numero du telephone:");
        tel = sc.nextInt();

        System.out.println("    L'adresse exact:");
        adr = sc.next();

        int_temp=0;
        System.out.println("    Choisissez un mot de passe SECURISE pour votre compte:");
        MP = sc.next();
        while( MP.length() < 8){
            System.out.println("    Choisissez un mot de passe SECURISE avec au moins 8 caractères:");
            MP = sc.next();
        }

        if (tel < 550000000 || tel>799999999) throw new InfoPropErronee("   VOUS DEVEZ ENTRER UN NUM DE TEL ACEPTABLE !!!");
        if (t_prop.containsKey(nom+prenom) ) {
            if (t_prop.get(nom+prenom).tel == tel) throw new DuplicateProp("    CE PROPIETAIRE EXISTE DEJA DANS LE SYSTEME !!!!");
        }
    }

    public void ajouter_app(String s){
        string_temp=s;
        Appartement string_temp = null;
        try {
            string_temp = new Appartement();
        } catch (InfoBienErronees infoBienErronees) {
            System.out.println(infoBienErronees.getMessage());
            menu_prop(nom+prenom);
        }
        t_app_admin.add(string_temp);
        liste_bien_app.add(string_temp);
        it_app_admin++;
    }
    public void ajouter_app(String s, Transaction trans, double prix_prop, String Ascenseur, int etage, int code_wilaya, int superficie, int nb_pieces){
        string_temp=s;
        Appartement string_temp = new Appartement(trans, prix_prop, Ascenseur, etage, code_wilaya, superficie, nb_pieces);
        t_app_admin.add(string_temp);
        liste_bien_app.add(string_temp);
        it_app_admin++;
    }
    public void ajouter_app(String s, Transaction trans, double prix_prop, int etage, int code_wilaya, int superficie, int nb_pieces){
        string_temp=s;
        Appartement string_temp = new Appartement(trans,prix_prop,etage,code_wilaya,superficie,nb_pieces);
        t_app_admin.add(string_temp);
        liste_bien_app.add(string_temp);
        it_app_admin++;
    }


    public void ajouter_maison(String s){
        string_temp=s;
        Maison string_temp = null;
        try {
            string_temp = new Maison();
        } catch (InfoBienErronees infoBienErronees) {
            System.out.println(infoBienErronees.getMessage());
            menu_prop(nom+prenom);
        } catch (SuperficieHabitableImpossible superficieHabitableImpossible) {
            System.out.println(superficieHabitableImpossible.getMessage());
            menu_prop(nom+prenom);
        }
        t_maisons_admin.add(string_temp);
        liste_bien_m.add(string_temp);
        it_m_admin++;
    }
    public void ajouter_maison(String s,Transaction trans, double prix_prop, int code_wilaya, int superficie){
        string_temp=s;
        Maison string_temp = new Maison(trans,prix_prop,code_wilaya,superficie);
        t_maisons_admin.add(string_temp);
        liste_bien_m.add(string_temp);
        it_m_admin++;
    }
    public void ajouter_maison(String s1,Transaction trans, double prix_prop, int code_wilaya, String s, int superficie){
        string_temp=s1;
        Maison string_temp = new Maison(trans,prix_prop,code_wilaya,s,superficie);
        t_maisons_admin.add(string_temp);
        liste_bien_m.add(string_temp);
        it_m_admin++;
    }
    public void ajouter_maison(String s,Transaction trans, double prix_prop, int code_wilaya, String s1, int superficie, int code_wilaya_ech){
        string_temp3=s;
        Maison string_temp3 = new Maison(trans,prix_prop,code_wilaya,s1,superficie,code_wilaya_ech);
        t_maisons_admin.add(string_temp3);
        liste_bien_m.add(string_temp3);
        it_m_admin++;
    }

    public void ajouter_terrain(String s){
        string_temp=s;
        Terrain string_temp = null;
        try {
            string_temp = new Terrain();
        } catch (InfoBienErronees infoBienErronees) {
            System.out.println(infoBienErronees.getMessage());
            menu_prop(nom+prenom);
        }
        t_terrains_admin.add(string_temp);
        liste_bien_t.add(string_temp);
        it_t_admin++;
    }
    public void ajouter_terrain(String s, Transaction trans, double prix_prop, int code_wilaya, int superficie, int nb_facades, int code_wilaya_ech){
        string_temp=s;
        Terrain string_temp = new Terrain(trans,prix_prop,code_wilaya,superficie,nb_facades,code_wilaya_ech);
        liste_bien_t.add(string_temp);
        t_terrains_admin.add(string_temp);
        it_t_admin++;
    }

    public String getAdr() {
        return adr;
    }
    public String getEmail() {
        return email;
    }
    public long getTel() {
        return tel;
    }
    public String getMP() {
        return MP;
    }

    public void afficher_notif(){
        for (i=0; i< notifications.size(); i++){
            System.out.println("   notificaton "+i+" "+ notifications.get(i));
            System.out.println("   tapez une touche puor passer au suivant");
            string_temp=sc.next();
        }
    }
    public void afficher_mes_biens(){
        for (Appartement b : liste_bien_app){
            b.afficher();
            System.out.println("    1- Voir plus");
            System.out.println("    2- Supprimer mon bien");
            System.out.println("    3- Passer au suivant");
            System.out.println("    0- Retour au menu principale");
            System.out.print("    Votre choix:");
            int_temp=sc.nextInt();
            Admin ad = new Admin();
            while (int_temp<0 || int_temp>3)
                switch(int_temp){
                    case 0: menu_prop(nom+prenom);
                        break;
                    case 1: b.afficher_plus();
                        break;
                    case 2: {
                        System.out.println("   Vous voulez vraiment supprimer ce bien? 1 pour oui 0 pour annuler");
                        int_temp=sc.nextInt();
                        if (int_temp==1){ ad.supprimer_app((Appartement)b); menu_prop(nom+prenom);System.out.println(" C'est bon");
                        }else menu_prop(nom+prenom);
                    }
                }
        }
        for (Maison b : liste_bien_m){
            b.afficher();
            System.out.println("    1- Voir plus");
            System.out.println("    2- Supprimer mon bien");
            System.out.println("    3- Passer au suivant");
            System.out.println("    0- Retour au menu principale");
            System.out.print("    Votre choix:");
            int_temp=sc.nextInt();
            Admin ad = new Admin();
            while (int_temp<0 || int_temp>3)
                switch(int_temp){
                    case 0: menu_prop(nom+prenom);
                        break;
                    case 1: b.afficher_plus();
                        break;
                    case 2: {
                        System.out.println("   Vous voulez vraiment supprimer ce bien? 1 pour oui 0 pour annuler");
                        int_temp=sc.nextInt();
                        if (int_temp==1){ ad.supprimer_m((Maison)b);menu_prop(nom+prenom);System.out.println(" C'est bon");
                        }else menu_prop(nom+prenom);
                    }
                }
        }
        for (Terrain b : liste_bien_t){
            b.afficher();
            System.out.println("    1- Voir plus");
            System.out.println("    2- Supprimer mon bien");
            System.out.println("    3- Passer au suivant");
            System.out.println("    0- Retour au menu principale");
            System.out.print("    Votre choix:");
            int_temp=sc.nextInt();
            Admin ad = new Admin();
            while (int_temp<0 || int_temp>3)
                switch(int_temp){
                    case 0: menu_prop(nom+prenom);
                        break;
                    case 1: b.afficher_plus();
                        break;
                    case 2: {
                        System.out.println("   Vous voulez vraiment supprimer ce bien? 1 pour oui 0 pour annuler");
                        int_temp=sc.nextInt();
                        if (int_temp==1){ ad.supprimer_t((Terrain)b);menu_prop(nom+prenom);System.out.println(" C'est bon");
                        }else menu_prop(nom+prenom);
                    }
                }
        }
    }

    @Override
    public int compareTo(Proprietaire o) {
        if ( nom.equals(o.nom) && prenom.equals(o.prenom) && adr.equals(o.adr)) return 1;
        else return -1;
    }

    public String gztFullName(){return nom+" "+prenom;}
    public String getAdress(){return adr;}
    public int get_nb_bien(){return liste_bien_m.size()+liste_bien_app.size()+liste_bien_t.size();}
}