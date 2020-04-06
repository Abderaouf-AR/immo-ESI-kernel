package TP.Noyau;
import TP.Noyau.myExceptions.InfoBienErronees;

import java.util.*;

public class Appartement extends Habitable implements Comparable<Appartement>
{

    /**************************** LES ATTRIBUTS ****************************/
    protected int etage=1;                     // le numero d'étage ou il se situe
    protected int num_bloc;
    protected int num_app;
    public boolean ascenseur= false;
    public boolean simplexe= false;                // vrai s'il s'agit d'un simplexe, faux pour le duplexe
    public String string_temp;
    public boolean bool_temp= false;
    public int int_temp;
    public final Scanner sc = new Scanner(System.in);

    /**************************** LES METHODES ****************************/
    public Appartement()throws InfoBienErronees {
        num_bien++;
        num_bien_actu=num_bien;
        creer_bien();
        System.out.println(num_bien_actu);
    }
    public Appartement(Transaction trans, double prix_prop, String Ascenseur, int etage, int code_wilaya, int superficie, int nb_pieces){
        this.trans=trans;
        this.prix_prop=prix_prop;
        this.etage=etage;
        this.code_wilaya=code_wilaya;
        this.superficie=superficie;
        this.nb_pieces=nb_pieces;
        switch(Ascenseur){
            case "Avec Ascenseur": ascenseur= true; break;
            case "Sans Ascenseur": ascenseur= false; break;
            case "Ascenseur": ascenseur= true; break;
        }
        num_bien++;
        num_bien_actu=num_bien;
        calcul_prix();
    }
    public Appartement(Transaction trans, double prix_prop, int etage, int code_wilaya, int superficie, int nb_pieces){
        this.trans=trans;
        this.prix_prop=prix_prop;
        this.etage=etage;
        this.code_wilaya=code_wilaya;
        this.superficie=superficie;
        this.nb_pieces=nb_pieces;
        num_bien++;
        num_bien_actu=num_bien;
        calcul_prix();
    }

    public boolean correct() throws InfoBienErronees {
        if ((nb_pieces>6 && simplexe) || (nb_pieces>12 && !(simplexe))) throw new InfoBienErronees("    LES INFO DU BIEN SONT ERRONEES....(nb_pieces>6 && simplexe) || (nb_pieces>12 && !(simplexe) DETECTED") ;
        return true;
    }

    public  double calcul_prix(){
        switch (trans){
            case location:{
                if (superficie < 60){
                    if (wilaya_prix[code_wilaya-1]<50000){ prix_final = 1.01 * prix_prop; }
                    else {prix_final = 1.015 * prix_prop;}
                }
                else if (superficie > 59 && superficie < 151){
                    if (wilaya_prix[code_wilaya-1]<50000){ prix_final = 1.02 * prix_prop; }
                    else {prix_final = 1.025 * prix_prop;}
                }
                else {
                    if (wilaya_prix[code_wilaya-1]<50000){ prix_final = 1.03 * prix_prop; }
                    else {prix_final = 1.035 * prix_prop;}
                }
                if (etage > -1 && etage < 3) prix_final = prix_final + 5000;
                if (etage > 5 ) prix_final = prix_final - 500 * superficie;}
            break;

            case echange:{
                if (prix_prop < 5000000){
                    if (wilaya_prix[code_wilaya-1]<50000){ prix_final = 1.03 * prix_prop; }
                    else {prix_final = 1.035 * prix_prop;}
                }
                else if (prix_prop > 4999999 && prix_prop < 15000001){
                    if (wilaya_prix[code_wilaya-1]<50000){ prix_final = 1.02 * prix_prop; }
                    else {prix_final = 1.025 * prix_prop;}
                }
                else {
                    if (wilaya_prix[code_wilaya-1]<70000){ prix_final = 1.01 * prix_prop; }
                    else {prix_final = 1.02 * prix_prop;}
                }
                if (etage > -1 && etage < 3) prix_final = prix_final + 50000;
                if (code_wilaya_ech>0) prix_final=prix_final*1.0025;}
            break;

            case vente: {
                if (prix_prop < 5000000){
                    if ( wilaya_prix[code_wilaya-1]<50000 ){ prix_final = 1.03 * prix_prop; }
                    else {prix_final = 1.035 * prix_prop;}
                }
                else if (prix_prop > 4999999 && prix_prop < 15000001){
                    if (wilaya_prix[code_wilaya-1]<50000){ prix_final = 1.02 * prix_prop; }
                    else {prix_final = 1.025 * prix_prop;}
                }
                else {
                    if (wilaya_prix[code_wilaya-1]<70000){ prix_final = 1.01 * prix_prop; }
                    else {prix_final = 1.02 * prix_prop;}
                }
                if (etage > -1 && etage < 3) prix_final = prix_final + 50000;}
            break;
        }
        return prix_final;}

    public  void creer_bien() throws InfoBienErronees{
        System.out.println("    Veuillez inserer les informations suivantes de votre appartement:");
        System.out.println("    L'adresse exact:");
        adr_exact = sc.next();

        System.out.println("    Le code de la wilaya:");
        code_wilaya = sc.nextInt();
        while(!(Wilaya.containsKey(code_wilaya))){
            System.out.println("    IL N'Y A PAS UNE WULAYA AVEC LE CODE " +code_wilaya +" !!");
            System.out.println("    Re-entrez le code de la wilaya svp:");
            code_wilaya = sc.nextInt();
        }

        System.out.println("    La superficie total du bien:");
        superficie = sc.nextInt();

        System.out.println("    Le nombre de pieces:");
        nb_pieces = sc.nextInt();

        System.out.println("    Le numero d'etage:");
        do {
            etage = sc.nextInt();
        } while (etage<0);

        System.out.println("    Le numero de bloc:");
        num_bloc = sc.nextInt();

        System.out.println("    Le numero d'appartement :");
        num_app = sc.nextInt();

        System.out.println("    Il s'agit d'un simplexe?:");
        string_temp = sc.next();
        while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
            System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
            string_temp = sc.next();
        }
        if (string_temp.equals("oui") || string_temp.equals("yes")) simplexe = true;
        else if (string_temp.equals("non") || string_temp.equals("no")) simplexe = false;

        System.out.println("    L'existance d'un ascenseur:");
        string_temp = sc.next();
        while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
            System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
            string_temp = sc.next();
        }
        if (string_temp.equals("oui") || string_temp.equals("yes")) ascenseur = true;
        else if (string_temp.equals("non") || string_temp.equals("no")) ascenseur = false;

        System.out.println("    Est ce qu'il meuble?:");
        string_temp = sc.next();
        while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
            System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
            string_temp = sc.next();
        }
        if (string_temp.equals("oui") || string_temp.equals("yes")) meuble = true;
        else if (string_temp.equals("non") || string_temp.equals("no")) meuble = false;
        if (meuble){
            System.out.println("    La liste des meubles:");
            list_meuble = sc.next();
        }

        System.out.println("    Quel est le type de trasaction?\n      1-Location");
        System.out.println("\n      2-Echange\n      3-Vente");
        int_temp = sc.nextInt();
        while (int_temp>3 && 1>int_temp){
            System.out.println("      ! Choisir 1, 2 ou 3 seulement !");
        }
        switch(int_temp){
            case 1: trans=Transaction.location;
                break;
            case 2: trans=Transaction.echange;
                System.out.println("      Donner le code de la wilaya d'echange souhaité ");
                code_wilaya_ech = sc.nextInt();
                while(!(Wilaya.containsKey(code_wilaya_ech))){
                    System.out.println("    IL N'Y A PAS UNE WULAYA AVEC LE CODE " +code_wilaya_ech +" !!");
                    System.out.println("    Re-entrez le code de la wilaya svp:");
                    code_wilaya_ech = sc.nextInt();
                }
                break;
            case 3: trans=Transaction.vente;
                break;
            default: System.out.println("faux");
        }

        System.out.println("    Le prix:");
        prix_prop = sc.nextInt();

        System.out.println("    Est ce que le prix est negociable?:");
        string_temp = sc.next();
        while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
            System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
            string_temp = sc.next();
        }
        if (string_temp.equals("oui") || string_temp.equals("yes")) prix_nego = true;
        else if (string_temp.equals("non") || string_temp.equals("no")) prix_nego = false;

        System.out.println("    Ajoutez un petit descriptif:");
        descriptif = sc.next();
        calcul_prix();
        if ((nb_pieces>6 && simplexe) || (nb_pieces>12 && !(simplexe))) throw new InfoBienErronees("    LES INFO DU BIEN SONT ERRONEES....(nb_pieces>6 && simplexe) || (nb_pieces>12 && !(simplexe) DETECTED") ;
    }

    public void afficher(){
        System.out.println("    Le numero  du bien: " + num_bien_actu + myClass());
        System.out.println("    La date d'ajout au systeme: "+ date);
        System.out.println("    La wilaya: " + Wilaya.get(code_wilaya));
        System.out.println("    La superficie total du bien: " + superficie);
        System.out.println("    Le nombre de pieces: " + nb_pieces + " (F" + nb_pieces + ")");
        System.out.println("    L'etage numero: " + etage);
        System.out.println("    Le type de trasaction:  " + trans);
        if (trans ==Transaction.echange ) System.out.println("    La wilaya d'échange souhaité: " + Wilaya.get(code_wilaya_ech));
        calcul_prix();
        System.out.println("    Le prix: " + prix_final);
        System.out.println("    Le prix est negociable: " + prix_nego);
        //System.out.println("    Le propriétaire: " + prop.nom + " " + prop.prenom);
    }

    public void afficher_plus(){
        afficher();
        System.out.println("    L'adresse exact: " + adr_exact);
        string_temp = (meuble) ? "oui" : "non";
        System.out.println("    Le numero du bloc: " + num_bloc);
        System.out.println("    Le numero d'appartement: " + num_app);
        System.out.println("    Est ce qu'il meublé?: " + string_temp );
        if (meuble){System.out.println("    La liste des meubles: " + list_meuble);}
        string_temp = (ascenseur) ? "oui" : "non";
        System.out.println("    L'existance d'un ascenseur: " + string_temp);
        string_temp = (simplexe) ? "Simplexe" : "Duplexe";
        System.out.println("    Simplexe ou Duplexe: " + string_temp);
        System.out.println("    le descriptif: " + descriptif);
        System.out.println("    La date d'ajout: ");

    }

    public void modifier_infos_prin(){
        System.out.println("    Voici la liste des informations principales de cette maison");         System.out.println("    Le numero  du bien: " + num_bien);
        System.out.println("    1.La wilaya: " + Wilaya.get(code_wilaya));
        System.out.println("    2.La superficie total du bien: " + superficie);
        System.out.println("    3.Le nombre de pieces: " + nb_pieces + " (F" + nb_pieces + ")");
        System.out.println("    4.L'etage numero: " + etage);
        System.out.println("    5.Le type de trasaction:  " + trans);
        if (trans ==Transaction.echange ) System.out.println("    La wilaya d'échange souhaité: " + Wilaya.get(code_wilaya_ech));
        System.out.println("    6.Le prix: " + prix_final);
        System.out.println("    7.Le prix est negociable: " + prix_nego);
        //System.out.println("    8.Le propriétaire: " + prop.nom + " " + prop.prenom);
        System.out.println("    ** Choisir le numero du champ que vous voulez modifier (0 pour terminer)");
        System.out.println("      Votre choix: ");
        int_temp = sc.nextInt();
        while(int_temp>0){
            switch(int_temp){
                case 1: System.out.println("    Le code de la wilaya:");
                    code_wilaya = sc.nextInt();
                    while(!(Wilaya.containsKey(code_wilaya))){
                        System.out.println("    IL N'Y A PAS UNE WULAYA AVEC LE CODE " +code_wilaya +" !!");
                        System.out.println("    Re-entrez le code de la wilaya svp:");
                        code_wilaya = sc.nextInt();
                    }
                    break;

                case 2: System.out.println("    La superficie total du bien:");
                    superficie = sc.nextInt();
                    break;

                case 3: System.out.println("    Le nombre de pieces:");
                    nb_pieces = sc.nextInt();
                    break;

                case 4: System.out.println("    L'etage numero::");
                    etage = sc.nextInt();
                    break;

                case 5: System.out.println("    Quel est le type de trasaction?\n      1-Location");
                    System.out.println("\n      2-Echange\n      3-Vente");
                    int_temp = sc.nextInt();
                    while (int_temp>3 && 1>int_temp){
                        System.out.println("      ! Choisir 1, 2 ou 3 seulement !");
                    }
                    switch(int_temp){
                        case 1: trans=Transaction.location;
                            break;
                        case 2: trans=Transaction.echange;
                            System.out.println("      Donner le code de la wilaya d'echange souhaité ");
                            code_wilaya_ech = sc.nextInt();
                            while(!(Wilaya.containsKey(code_wilaya_ech))){
                                System.out.println("    IL N'Y A PAS UNE WULAYA AVEC LE CODE " +code_wilaya_ech +" !!");
                                System.out.println("    Re-entrez le code de la wilaya svp:");
                                code_wilaya_ech = sc.nextInt();
                            }
                            break;
                        case 3: trans=Transaction.vente;
                            break;
                    }
                    break;

                case 6: System.out.println("    Le prix (c'est le prix du proprietaire sans les frais ajoutés!):");
                    prix_prop = sc.nextInt();
                    calcul_prix();
                    break;

                case 7: System.out.println("    Est ce que le prix est negociable?:");
                    string_temp = sc.next();
                    while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
                        System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
                        string_temp = sc.next();
                    }
                    if (string_temp.equals("oui") || string_temp.equals("yes")) prix_nego = true;
                    else if (string_temp.equals("non") || string_temp.equals("no")) prix_nego = false;
                    break;

                case 8: System.out.println("    Il est impossible de modifier le proprietaire ");
                    break;

                default:System.out.println("    ! Choisir un nombre entre 0 et 9 svp !:");
                    break;
            }
            System.out.println("    ** Choisir le numero du champ que vous voulez modifier (0 pour terminer)");
            System.out.println("      Votre choix: ");
            int_temp = sc.nextInt();
        }
    }

    public void modifier_detail(){
        System.out.println("    Voici la liste des informations détails de cette maison");
        System.out.println("    1.L'adresse exact: " + adr_exact);
        string_temp = (meuble) ? "oui" : "non";
        System.out.println("    2.Est ce qu'il meublé?: " + string_temp );
        if (meuble){System.out.println("    La liste des meubles: " + list_meuble);}
        string_temp = (ascenseur) ? "oui" : "non";
        System.out.println("    3.L'existance d'un ascenseur: " + string_temp);
        string_temp = (simplexe) ? "Simplexe" : "Duplexe";
        System.out.println("    4.Simplexe ou Duplexe: " + string_temp);
        System.out.println("    5.le descriptif: " + descriptif);

        System.out.println("    ** Choisir le numero du champ que vous voulez modifier (0 pour terminer)");
        System.out.println("      Votre choix: ");
        int_temp = sc.nextInt();
        while(int_temp>0){
            switch(int_temp){
                case 1: System.out.println("    L'adresse exact:");
                    adr_exact = sc.next();
                    break;

                case 2: System.out.println("    Est ce qu'il meuble?:");
                    string_temp = sc.next();
                    while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
                        System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
                        string_temp = sc.next();
                    }
                    if (string_temp.equals("oui") || string_temp.equals("yes")) meuble = true;
                    else if (string_temp.equals("non") || string_temp.equals("no")) meuble = false;
                    if (meuble){
                        System.out.println("    La liste des meubles:");
                        list_meuble = sc.next();
                    }
                    break;

                case 3: System.out.println("    L'existance d'un ascenseur:");
                    string_temp = sc.next();
                    while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
                        System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
                        string_temp = sc.next();
                    }
                    if (string_temp.equals("oui") || string_temp.equals("yes")) ascenseur = true;
                    else if (string_temp.equals("non") || string_temp.equals("no")) ascenseur = false;
                    break;

                case 4: System.out.println("    Il s'agit d'un simplexe?:");
                    string_temp = sc.next();
                    while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
                        System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
                        string_temp = sc.next();
                    }
                    if (string_temp.equals("oui") || string_temp.equals("yes")) simplexe = true;
                    else if (string_temp.equals("non") || string_temp.equals("no")) simplexe = false;
                    break;

                case 5: System.out.println("    Le descriptif");
                    descriptif = sc.next();
                    break;

                default:System.out.println("    ! Choisir un nombre entre 0 et 6 svp !:");
                    break;
            }
            System.out.println("    ** Choisir le numero du champ que vous voulez modifier (0 pour terminer)");
            System.out.println("      Votre choix: ");
            int_temp = sc.nextInt();
        }
    }

    public void contacter_agc(){ // contacter l'agence
        System.out.println("    Ecrire le message que vous voulez transmetre à l'agence/proprietaire");
        System.out.println("    (Essayez de mentionner voter information pour qu'on puisse vous contacter)");
        msg_visiteur  = sc.next();
        systeme.msg_visiteurs.add(msg_visiteur);
        System.out.println("    MERCI!");
    }

    public String myClass(){ return "appartement";}

    @Override
    public int compareTo(Appartement obj) {
        if ( adr_exact.equals(obj.adr_exact) && superficie== obj.superficie && code_wilaya== obj.code_wilaya && num_app== obj.num_app && num_bloc== obj.num_bloc && etage== obj.etage ) return 0;
        else if (date.compareTo(obj.date)>0 || date.compareTo(obj.date)==0) return 1;
        return -1;
    }

    public String getAdr(){return adr_exact;}
    public int getSup(){return superficie;}

}