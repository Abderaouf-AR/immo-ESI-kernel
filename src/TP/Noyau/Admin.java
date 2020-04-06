package TP.Noyau;
import TP.Noyau.myExceptions.*;

import java.util.*;

public class Admin extends systeme
{
    /**************************** LES ATTRIBUTS ****************************/
    public static final String NomUtilisateur="ar";          // le nom d'utilisateur pour l'admin
    public static final String MP="0000";                    // le mot de passe


    private Scanner sc = new Scanner(System.in);
    private int int_temp;
    private String string_temp;
    private int i;
    private Bien b;
    /**************************** LES METHODES ****************************/

    public void visualiser(){
        Appartement v;
        System.out.println("     Les appartements:");
        Iterator<Appartement> it = t_appartements.iterator();
        while (it.hasNext()){
            v = it.next();
            v.afficher();
            System.out.println("     à choisir:");
            System.out.println("     1- Voir plus.");
            System.out.println("     2- Modifier le bien(informations principales).");
            System.out.println("     3- Modifier le bien(detail).");
            System.out.println("     4- Supprimer le bien");
            System.out.println("     5- Archiver le bien.");
            System.out.println("     6- Le bien est vendu/loué/echangé");
            System.out.println("     7- Passer au suivant.");
            System.out.println("     0- Retour au menu principale");
            int_temp=sc.nextInt();
            while (int_temp<0 || int_temp>7){
                int_temp = sc.nextInt();
                System.out.print  ("     !!! Choisissez de 0 jusqu'a 7 seulement !!! ");
            }
            switch(int_temp){
                case 0:menu_admin();
                    break;
                case 1: v.afficher_plus();
                    break;
                case 2: v.modifier_infos_prin();
                    break;
                case 3: v.modifier_detail();
                    break;
                case 4: ;
                    supprimer_app(v);
                    menu_admin();
                    System.out.println("    C'est bon");
                    break;
                case 5:
                    //v.prop.liste_bien_app.remove(v);
                    archiver_app(v);
                    System.out.println("    C'est bon");
                    break;
                case 6: t_appartements_lve.add(v);
                    t_appartements.remove(v);
                    menu_admin();
                    break;
            }
        }
        System.out.println("     Les maisons:");
        for(Maison a : t_maisons) {
            a.afficher();
            System.out.println("     à choisir:");
            System.out.println("     1- Voir plus.");
            System.out.println("     2- Modifier le bien(informations principales).");
            System.out.println("     2- Modifier le bien(detail).");
            System.out.println("     4- Supprimer le bien");
            System.out.println("     5- Archiver le bien.");
            System.out.println("     6- Le bien est vendu/loué/echangé");
            System.out.println("     7- Passer au suivant.");
            System.out.println("     0- Retour au menu principale");
            int_temp=sc.nextInt();
            while (int_temp<0 || int_temp>7){
                int_temp = sc.nextInt();
                System.out.print  ("     !!! Choisissez de 0 à 7 seulement !!! ");
            }
            switch(int_temp){
                case 0: menu_admin();
                    break;
                case 1: a.afficher_plus();
                    break;
                case 2: a.modifier_infos_prin();
                    break;
                case 3: a.modifier_detail();
                    break;
                case 4: supprimer_m(a);
                    menu_admin();
                    System.out.println("    C'est bon");
                    break;
                case 5: //a.prop.liste_bien_m.remove(a);
                    archiver_m(a);
                    System.out.println("    C'est bon");
                    break;
                case 6: t_maisons_lve.add(a);
                    t_maisons.remove(a);
                    menu_admin();
                    break;
            }
        }
        System.out.println("     Les terrains:");
        for(Terrain a : t_terrains) {
            a.afficher();
            System.out.println("     à choisir:");
            System.out.println("     1- Voir plus.");
            System.out.println("     2- Modifier le bien(informations principales).");
            System.out.println("     2- Modifier le bien(detail).");
            System.out.println("     4- Supprimer le bien");
            System.out.println("     5- Archiver le bien.");
            System.out.println("     6- Le bien est vendu/loué/echangé");
            System.out.println("     7- Passer au suivant.");
            System.out.println("     0- Retour au menu principale");
            int_temp=sc.nextInt();
            while (int_temp<0 || int_temp>7){
                int_temp = sc.nextInt();
                System.out.print  ("     !!! Choisissez de 0 à 7 seulement !!! ");
            }
            switch(int_temp){
                case 0: menu_admin();
                    break;
                case 1: a.afficher_plus();
                    break;
                case 2: a.modifier_infos_prin();
                    break;
                case 3: a.modifier_detail();
                    break;
                case 4: supprimer_t(a);
                    menu_admin();
                    System.out.println("    C'est bon");
                    break;
                case 5: //a.prop.liste_bien_t.remove(a);
                    archiver_t(a);
                    System.out.println("    C'est bon");
                    break;
                case 6: t_terrains_lve.add(a);
                    t_terrains.remove(a);
                    menu_admin();
                    break;
                case 7: break;
            }
        }
    }
    public void visualiser(String s){
        switch(s){
            case "appartement":
                System.out.println("     Les appartements:");
                for(Appartement v : t_appartements) {
                    v.afficher();
                    System.out.println("     à choisir:");
                    System.out.println("     1- Voir plus.");
                    System.out.println("     2- Modifier le bien(informations principales).");
                    System.out.println("     2- Modifier le bien(detail).");
                    System.out.println("     4- Supprimer le bien");
                    System.out.println("     5- Archiver le bien.");
                    System.out.println("     6-,Passer au suivant.");
                    int_temp=sc.nextInt();
                    while (int_temp<1 && int_temp>5){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 1, 2, 3, 4 ou 5 seulement !!! ");
                    }
                    switch(int_temp){
                        case 1: v.afficher_plus();
                            break;
                        case 2: v.modifier_infos_prin();
                            break;
                        case 3: v.modifier_detail();
                            break;
                        case 4: supprimer_app(v);
                            System.out.println("    C'est bon");
                            break;
                        case 5: archiver_app(v);
                            break;
                        case 6:
                            break;
                    }
                }
                break;
            case "maison":
                System.out.println("     Les maisons:");
                for(Maison a : t_maisons) {
                    a.afficher();
                    System.out.println("     à choisir:");
                    System.out.println("     1- Voir plus.");
                    System.out.println("     2- Modifier le bien(informations principales).");
                    System.out.println("     2- Modifier le bien(detail).");
                    System.out.println("     4- Supprimer le bien");
                    System.out.println("     5- Archiver le bien.");
                    System.out.println("     6-,Passer au suivant.");
                    int_temp=sc.nextInt();
                    while (int_temp<0 && int_temp>5){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 1, 2, 3, 4 ou 5 seulement !!! ");
                    }
                    switch(int_temp){
                        case 1: a.afficher_plus();
                            break;
                        case 2: a.modifier_infos_prin();
                            break;
                        case 3: a.modifier_detail();
                            break;
                        case 4: supprimer_m(a);
                            System.out.println("    C'est bon");
                            break;
                        case 5: archiver_m(a);
                            break;
                        case 6:
                            break;
                    }
                }
                break;
            case "terrain":
                System.out.println("     Les terrains:");
                for(Terrain a : t_terrains) {
                    a.afficher();
                    System.out.println("     à choisir:");
                    System.out.println("     1- Voir plus.");
                    System.out.println("     2- Modifier le bien(informations principales).");
                    System.out.println("     2- Modifier le bien(detail).");
                    System.out.println("     4- Supprimer le bien");
                    System.out.println("     5- Archiver le bien.");
                    System.out.println("     6-,Passer au suivant.");
                    int_temp=sc.nextInt();
                    while (int_temp<0 && int_temp>5){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 1, 2, 3, 4 ou 5 seulement !!! ");
                    }
                    switch(int_temp){
                        case 1: a.afficher_plus();
                            break;
                        case 2: a.modifier_infos_prin();
                            break;
                        case 3: a.modifier_detail();
                            break;
                        case 4: supprimer_t(a);
                            System.out.println("    C'est bon");
                            break;
                        case 5: archiver_t(a);
                            break;
                        case 6:
                            break;
                    }
                }
                break;
        }
    }
    public void visualiser_biens_sup(){
        System.out.println("     Les appartements:");
        for(Appartement v : t_appartements_sup) {
            v.afficher();
            System.out.println("     à choisir:");
            System.out.println("     1- Voir plus.");
            System.out.println("     2- Restaurer le bien.");
            System.out.println("     3- Supprimer le bien du tout");
            System.out.println("     4-,Passer au suivant.");
            int_temp=sc.nextInt();
            while (int_temp<1 && int_temp>4){
                int_temp = sc.nextInt();
                System.out.print  ("     !!! Choisissez 1, 2, 3 ou 4 seulement !!! ");
            }
            switch(int_temp){
                case 1: v.afficher_plus();
                    break;
                case 2: t_appartements.add(v);
                    t_appartements_sup.remove(v);
                    break;
                case 3: t_appartements_sup.remove(v);
                    break;
            }
        }
        System.out.println("     Les maisons:");
        for(Maison v : t_maisons_sup) {
            v.afficher();
            System.out.println("     à choisir:");
            System.out.println("     1- Voir plus.");
            System.out.println("     2- Restaurer le bien.");
            System.out.println("     3- Supprimer le bien du tout");
            System.out.println("     4-,Passer au suivant.");
            int_temp=sc.nextInt();
            while (int_temp<1 && int_temp>4){
                int_temp = sc.nextInt();
                System.out.print  ("     !!! Choisissez 1, 2, 3 ou 4 seulement !!! ");
            }
            switch(int_temp){
                case 1: v.afficher_plus();
                    break;
                case 2: t_maisons.add(v);
                    t_maisons_sup.remove(v);
                    break;
                case 3: t_maisons_sup.remove(v);
                    break;
            }
        }
        System.out.println("     Les terrains:");
        for(Terrain v : t_terrains_sup) {
            v.afficher();
            System.out.println("     à choisir:");
            System.out.println("     1- Voir plus.");
            System.out.println("     2- Restaurer le bien.");
            System.out.println("     3- Supprimer le bien du tout");
            System.out.println("     4-,Passer au suivant.");
            int_temp=sc.nextInt();
            while (int_temp<1 && int_temp>4){
                int_temp = sc.nextInt();
                System.out.print  ("     !!! Choisissez 1, 2, 3 ou 4 seulement !!! ");
            }
            switch(int_temp) {
                case 1:
                    v.afficher_plus();
                    break;
                case 2:
                    t_terrains.add(v);
                    t_terrains_sup.remove(v);
                    break;
                case 3:
                    t_terrains_sup.remove(v);
                    break;
            }    }
    }
    public void visualiser_biens_sup(String s){
        switch(s){
            case "appartement": {
                System.out.println("     Les appartements:");
                System.out.println("     Les appartements:");
                for(Appartement v : t_appartements_sup) {
                    v.afficher();
                    System.out.println("     à choisir:");
                    System.out.println("     1- Voir plus.");
                    System.out.println("     2- Restaurer le bien.");
                    System.out.println("     3- Supprimer le bien du tout");
                    System.out.println("     4-,Passer au suivant.");
                    int_temp=sc.nextInt();
                    while (int_temp<1 && int_temp>4){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 1, 2, 3 ou 4 seulement !!! ");
                    }
                    switch(int_temp){
                        case 1: v.afficher_plus();
                            break;
                        case 2: t_appartements.add(v);
                            t_appartements_sup.remove(v);
                            break;
                        case 3: t_appartements_sup.remove(v);
                            break;
                    }
                }}
            break;
            case "maison":{
                System.out.println("     Les maisons:");
                for(Maison v : t_maisons_sup) {
                    v.afficher();
                    System.out.println("     à choisir:");
                    System.out.println("     1- Voir plus.");
                    System.out.println("     2- Restaurer le bien.");
                    System.out.println("     3- Supprimer le bien du tout");
                    System.out.println("     4-,Passer au suivant.");
                    int_temp=sc.nextInt();
                    while (int_temp<1 && int_temp>4){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 1, 2, 3 ou 4 seulement !!! ");
                    }
                    switch(int_temp){
                        case 1: v.afficher_plus();
                            break;
                        case 2: t_maisons.add(v);
                            t_maisons_sup.remove(v);
                            break;
                        case 3: t_maisons_sup.remove(v);
                            break;
                    }
                }}
            break;
            case "terrain": System.out.println("     Les terrains:");
                for(Terrain v : t_terrains_sup) {
                    v.afficher();
                    System.out.println("     à choisir:");
                    System.out.println("     1- Voir plus.");
                    System.out.println("     2- Restaurer le bien.");
                    System.out.println("     3- Supprimer le bien du tout");
                    System.out.println("     4-,Passer au suivant.");
                    int_temp=sc.nextInt();
                    while (int_temp<1 && int_temp>4){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 1, 2, 3 ou 4 seulement !!! ");
                    }
                    switch(int_temp) {
                        case 1:
                            v.afficher_plus();
                            break;
                        case 2:
                            t_terrains.add(v);
                            t_terrains_sup.remove(v);
                            break;
                        case 3:
                            t_terrains_sup.remove(v);
                            break;
                    }    }
                break;
        }
    }
    public void visualiser_archive(){
        System.out.println("     Les appartements:");
        for(Appartement v : t_appartements_arch) {
            v.afficher();
            System.out.println("     à choisir:");
            System.out.println("     1- Voir plus.");
            System.out.println("     2- Restaurer le bien.");
            System.out.println("     3- Supprimer le bien");
            System.out.println("     4-,Passer au suivant.");
            int_temp=sc.nextInt();
            while (int_temp<1 && int_temp>4){
                int_temp = sc.nextInt();
                System.out.print  ("     !!! Choisissez 1, 2, 3 ou 4 seulement !!! ");
            }
            switch(int_temp){
                case 1: v.afficher_plus();
                    break;
                case 2: t_appartements.add(v);
                    t_appartements_arch.remove(v);
                    break;
                case 3: supprimer_app(v);
                    break;
            }
        }
        System.out.println("     Les maisons:");
        for(Maison v : t_maisons_arch) {
            v.afficher();
            System.out.println("     à choisir:");
            System.out.println("     1- Voir plus.");
            System.out.println("     2- Restaurer le bien.");
            System.out.println("     3- Supprimer le bien");
            System.out.println("     4-,Passer au suivant.");
            int_temp=sc.nextInt();
            while (int_temp<1 && int_temp>4){
                int_temp = sc.nextInt();
                System.out.print  ("     !!! Choisissez 1, 2, 3 ou 4 seulement !!! ");
            }
            switch(int_temp){
                case 1: v.afficher_plus();
                    break;
                case 2: t_maisons.add(v);
                    t_maisons_arch.remove(v);
                    break;
                case 3: supprimer_m(v);
                    break;
            }
        }
        System.out.println("     Les terrains:");
        for(Terrain v : t_terrains_arch) {
            v.afficher();
            System.out.println("     à choisir:");
            System.out.println("     1- Voir plus.");
            System.out.println("     2- Restaurer le bien.");
            System.out.println("     3- Supprimer le bien");
            System.out.println("     4-,Passer au suivant.");
            int_temp=sc.nextInt();
            while (int_temp<1 && int_temp>4){
                int_temp = sc.nextInt();
                System.out.print  ("     !!! Choisissez 1, 2, 3 ou 4 seulement !!! ");
            }
            switch(int_temp){
                case 1: v.afficher_plus();
                    break;
                case 2: t_terrains.add(v);
                    t_terrains_arch.remove(v);
                    break;
                case 3: supprimer_t(v);
                    break;
            }
        }}
    public void visualiser_archive(String s){
        switch(s){
            case "appartement":
                System.out.println("     Les appartements:");
                for(Appartement v : t_appartements_arch) {
                    v.afficher();
                    System.out.println("     à choisir:");
                    System.out.println("     1- Voir plus.");
                    System.out.println("     2- Restaurer le bien.");
                    System.out.println("     3- Supprimer le bien");
                    System.out.println("     4-,Passer au suivant.");
                    int_temp=sc.nextInt();
                    while (int_temp<1 && int_temp>4){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 1, 2, 3 ou 4 seulement !!! ");
                    }
                    switch(int_temp){
                        case 1: v.afficher_plus();
                            break;
                        case 2: t_appartements.add(v);
                            t_appartements_arch.remove(v);
                            break;
                        case 3: supprimer_app(v);
                            break;
                    }
                }
                break;
            case "maison":
                System.out.println("     Les maisons:");
                for(Maison v : t_maisons_arch) {
                    v.afficher();
                    System.out.println("     à choisir:");
                    System.out.println("     1- Voir plus.");
                    System.out.println("     2- Restaurer le bien.");
                    System.out.println("     3- Supprimer le bien");
                    System.out.println("     4-,Passer au suivant.");
                    int_temp=sc.nextInt();
                    while (int_temp<1 && int_temp>4){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 1, 2, 3 ou 4 seulement !!! ");
                    }
                    switch(int_temp){
                        case 1: v.afficher_plus();
                            break;
                        case 2: t_maisons.add(v);
                            t_maisons_arch.remove(v);
                            break;
                        case 3: supprimer_m(v);
                            break;
                    }
                }
                break;
            case "terrain":
                System.out.println("     Les terrains:");
                for(Terrain v : t_terrains_arch) {
                    v.afficher();
                    System.out.println("     à choisir:");
                    System.out.println("     1- Voir plus.");
                    System.out.println("     2- Restaurer le bien.");
                    System.out.println("     3- Supprimer le bien");
                    System.out.println("     4-,Passer au suivant.");
                    int_temp=sc.nextInt();
                    while (int_temp<1 && int_temp>4){
                        int_temp = sc.nextInt();
                        System.out.print  ("     !!! Choisissez 1, 2, 3 ou 4 seulement !!! ");
                    }
                    switch(int_temp){
                        case 1: v.afficher_plus();
                            break;
                        case 2: t_terrains.add(v);
                            t_terrains_arch.remove(v);
                            break;
                        case 3: supprimer_t(v);
                            break;
                    }}
                break;
        }}
    public void visualiser_bien_v_e_l(){}
    public void visualiser_bien_v_e_l(String s){}

    public void visualiser_liste_bien_prop(String name, String surname){
        try {
            if (check_prop(name, surname)){
                System.out.println("    La liste du bien du Mr/Mme " + name + surname +" : ");
                Proprietaire p=t_prop.get(name+surname);
                for (Bien b : p.liste_bien_app){
                    b.afficher();
                    System.out.println("    *Pour afficher les details du bien appuiyez sur 1");
                    System.out.println("    *Pour modifier les infos du bien appuiyez sur 2");
                    System.out.println("    *Pour archiver le bien appuiyez sur 3");
                    System.out.println("    *Pour supprimer le bien appuiyez sur 4");
                    System.out.println("    *Pour passer au suivant appuiyez sur 5");
                    int_temp=sc.nextInt();
                    while ( int_temp<1 || int_temp>5){
                        System.out.println("    SVP choisissez soit 1,2,3,4 ou 5 seulement!!");
                        int_temp = sc.nextInt();
                    }
                    switch(int_temp){
                        case 1: b.afficher_plus();
                            break;
                        case 2: b.modifier_infos_prin();
                            break;
                        case 3:
                            archiver_app((Appartement)b);
                            break;
                        case 4:
                            supprimer_app((Appartement)b);
                            break;
                    }
                }
                for (Bien b : p.liste_bien_m){
                    b.afficher();
                    System.out.println("    *Pour afficher les details du bien appuiyez sur 1");
                    System.out.println("    *Pour modifier les infos du bien appuiyez sur 2");
                    System.out.println("    *Pour archiver le bien appuiyez sur 3");
                    System.out.println("    *Pour supprimer le bien appuiyez sur 4");
                    System.out.println("    *Pour passer au suivant appuiyez sur 5");
                    int_temp=sc.nextInt();
                    while ( int_temp<1 || int_temp>5){
                        System.out.println("    SVP choisissez soit 1,2,3,4 ou 5 seulement!!");
                        int_temp = sc.nextInt();
                    }
                    switch(int_temp){
                        case 1: b.afficher_plus();
                            break;
                        case 2: b.modifier_infos_prin();
                            break;
                        case 3:
                            archiver_m((Maison)b);
                            break;
                        case 4:
                            supprimer_m((Maison)b);
                            break;
                    }
                }
                for (Bien b : p.liste_bien_t){
                    b.afficher();
                    System.out.println("    *Pour afficher les details du bien appuiyez sur 1");
                    System.out.println("    *Pour modifier les infos du bien appuiyez sur 2");
                    System.out.println("    *Pour archiver le bien appuiyez sur 3");
                    System.out.println("    *Pour supprimer le bien appuiyez sur 4");
                    System.out.println("    *Pour passer au suivant appuiyez sur 5");
                    int_temp=sc.nextInt();
                    while ( int_temp<1 || int_temp>5){
                        System.out.println("    SVP choisissez soit 1,2,3,4 ou 5 seulement!!");
                        int_temp = sc.nextInt();
                    }
                    switch(int_temp){
                        case 1: b.afficher_plus();
                            break;
                        case 2: b.modifier_infos_prin();
                            break;
                        case 3:
                            archiver_t((Terrain)b);
                            break;
                        case 4:
                            supprimer_t((Terrain)b);
                            break;
                    }
                }
            }
        } catch (PropNotFound propNotFound) {
            propNotFound.printStackTrace();
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

    public void supprimer_app(Appartement a){
        if (t_appartements.contains(a)){
            t_appartements_sup.add(a);it_app_sup++;
            t_appartements.remove(a);it_app--;}
    }
    public void supprimer_m(Maison m){
        t_maisons_sup.add(m);it_m_sup++;
        t_maisons.remove(m);it_m--;
    }
    public void supprimer_t(Terrain t){
        t_terrains_sup.add(t);it_t_sup++;
        t_terrains.remove(t);it_t--;
    }

    public void archiver_app(Appartement a){
        t_appartements_arch.add(a);it_app_arch++;
        t_appartements.remove(a);it_app--;
        menu_admin();
    }
    public void archiver_m(Maison m){
        t_maisons_arch.add(m);it_m_arch++;
        t_maisons.remove(m);it_m--;
        menu_admin();
    }
    public void archiver_t(Terrain t){
        t_terrains_sup.add(t);it_t_arch++;
        t_terrains.remove(t);it_t--;
        menu_admin();
    }

    public void modifier_bien(Bien b){
        System.out.println("    Quelle partie vous voulez modifier?");
        System.out.println("    1- Le tous (comme si vous recreez ce bien à nouveau)");
        System.out.println("    2- Les informations principales.");
        System.out.println("    3- Les détails.");
        int_temp = sc.nextInt();
        while ( !(int_temp>0   && int_temp <4)){
            System.out.println("    SVP choisissez soit 1,2 ou 3 seulement!!");
            int_temp = sc.nextInt();
        }

        switch(int_temp){
            case 1: b.modifier_infos_prin();
                b.modifier_detail();
                break;
            case 2: b.modifier_infos_prin();
                break;
            case 3: b.modifier_detail();
                break;
        }
    }

    public void valider_app() {
        for ( Appartement a : t_app_admin){
            try {
                if (t_appartements.contains(a)){
                    { // chercher le proprietaire du a
                        Set<Map.Entry<String, Proprietaire>> element_t_prop = t_prop.entrySet();
                        for (Map.Entry<String, Proprietaire> elem : element_t_prop){
                            if (elem.getValue().liste_bien_app.contains(a)){
                                contact_prop(elem.getKey(), " CE BIEN EXISTE DEJA");
                                //elem.getValue().liste_bien_app.remove(a);
                            }
                        }
                    }
                    System.out.println("    CE BIEN EXISTE DEJA !!!");
                    t_appartements_sup.add(a);
                    t_app_admin.remove(a);
                    throw new DuplicateBien("");
                }
                else {
                    { // chercher le proprietaire du a
                        Set<Map.Entry<String, Proprietaire>> element_t_prop = t_prop.entrySet();
                        for (Map.Entry<String, Proprietaire> elem : element_t_prop){
                            if (elem.getValue().liste_bien_app.contains(a)){
                                contact_prop(elem.getKey(), " VOTRE APPARTEMENT EST AJOUTEE AU SYSTEME");
                            }
                        }
                    }
                    System.out.println("    LE BIEN EST AJOUTER AVEC SUCCE");
                    t_appartements.add(a);
                }
            }
            catch (DuplicateBien duplicateBien){
                System.out.println(duplicateBien.getMessage());
                menu_admin();
            }
        }
        t_app_admin.clear();
    }
    public void valider_m() {
        for ( Maison a : t_maisons_admin){
            try {
                if (t_maisons.contains(a)){
                    { // chercher le proprietaire du a
                        Set<Map.Entry<String, Proprietaire>> element_t_prop = t_prop.entrySet();
                        for (Map.Entry<String, Proprietaire> elem : element_t_prop){
                            if (elem.getValue().liste_bien_m.contains(a)){
                                contact_prop(elem.getKey(), " CE BIEN EXISTE DEJA");
                                //elem.getValue().liste_bien_m.remove(a);
                            }
                        }
                    }
                    System.out.println("    CE BIEN EXISTE DEJA !!!");
                    t_maisons_sup.add(a);
                    t_maisons_admin.remove(a);
                    throw new DuplicateBien("");
                }
                else {
                    { // chercher le proprietaire du a
                        Set<Map.Entry<String, Proprietaire>> element_t_prop = t_prop.entrySet();
                        for (Map.Entry<String, Proprietaire> elem : element_t_prop){
                            if (elem.getValue().liste_bien_m.contains(a)){
                                contact_prop(elem.getKey(), " VOTRE MAISON EST AJOUTEE AU SYSTEME");
                            }
                        }
                    }
                    System.out.println("    LE BIEN EST AJOUTER AVEC SUCCE");
                    t_maisons.add(a);
                }
            }
            catch (DuplicateBien duplicateBien){
                System.out.println(duplicateBien.getMessage());
                menu_admin();
            }
        }
        t_maisons_admin.clear();
    }
    public void valider_t() {
        for ( Terrain a : t_terrains_admin){
            try {
                if (t_terrains.contains(a)){
                    { // chercher le proprietaire du a
                        Set<Map.Entry<String, Proprietaire>> element_t_prop = t_prop.entrySet();
                        for (Map.Entry<String, Proprietaire> elem : element_t_prop){
                            if (elem.getValue().liste_bien_t.contains(a)){
                                contact_prop(elem.getKey(), " CE BIEN EXISTE DEJA");
                                //elem.getValue().liste_bien_t.remove(a);
                            }
                        }
                    }
                    System.out.println("    CE BIEN EXISTE DEJA !!!");
                    t_terrains_sup.add(a);
                    t_maisons_admin.remove(a);
                    throw new DuplicateBien("");
                }
                else {
                    { // chercher le proprietaire du a
                        Set<Map.Entry<String, Proprietaire>> element_t_prop = t_prop.entrySet();
                        for (Map.Entry<String, Proprietaire> elem : element_t_prop){
                            if (elem.getValue().liste_bien_t.contains(a)){
                                contact_prop(elem.getKey(), " VOTRE TERRAIN EST AJOUTEE AU SYSTEME");
                            }
                        }
                    }
                    System.out.println("    LE BIEN EST AJOUTER AVEC SUCCE");
                    t_terrains.add(a);
                }
            }
            catch (DuplicateBien duplicateBien){
                System.out.println(duplicateBien.getMessage());
                menu_admin();
            }
        }
        t_terrains_admin.clear();
    }
    public void valider(){
        valider_app();
        valider_m();
        valider_t();
    }

    public void creer_bien_admin(String s){
        switch (s){
            case "appartement":{
                int_temp = Bien.num_bien;
                string_temp = "Bien"+ Integer.toString(int_temp);
                Appartement string_temp = null;
                try {
                    string_temp = new Appartement();
                } catch (InfoBienErronees infoBienErronees) {
                    System.out.println(infoBienErronees.getMessage());
                    menu_admin();
                }
                t_app_admin.add(string_temp);
                menu_admin();
            }
            break;
            case "maison":{
                int_temp = Bien.num_bien;
                string_temp = "Bien"+ Integer.toString(int_temp);
                Maison string_temp = null;
                try {
                    string_temp = new Maison();
                } catch (InfoBienErronees infoBienErronees) {
                    System.out.println(infoBienErronees.getMessage());
                    menu_admin();
                } catch (SuperficieHabitableImpossible superficieHabitableImpossible) {
                    System.out.println(superficieHabitableImpossible.getMessage());
                    menu_admin();
                }
                t_maisons_admin.add(string_temp);
                menu_admin();
            }
            break;
            case "terrain":{
                int_temp = Bien.num_bien;
                string_temp = "Bien"+ Integer.toString(int_temp);
                Terrain string_temp = null;
                try {
                    string_temp = new Terrain();
                } catch (InfoBienErronees infoBienErronees) {
                    System.out.println(infoBienErronees.getMessage());
                    menu_admin();
                }
                t_terrains_admin.add(string_temp);
                menu_admin();
            }
            break;
        }
    }
    public void afficher_list_temp(){
        System.out.println("     Les appartement********************************************************************");
        for (Appartement a : t_app_admin){
            a.afficher();
            System.out.println("*******");
        }
        System.out.println("     Les maisons************************************************************************");
        for (Maison m : t_maisons_admin){
            m.afficher();
            System.out.println("*******");
        }
        System.out.println("     Les terrains************************************************************************");
        for (Terrain t: t_terrains_admin){
            t.afficher();
            System.out.println("*******");
        }
    }

    public void contact_prop(String NomPrenom, String msg){
        if ( t_prop.containsKey(NomPrenom) ) {
            t_prop.get(NomPrenom).notifications.add(msg);
        }
    }
    public void afficher_notif(){
        for (i=0; i<msg_visiteurs.size(); i++){
            System.out.println("msg num"+i);
            System.out.println(msg_visiteurs.get(i));

        }
    }
}