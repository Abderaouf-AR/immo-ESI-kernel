package TP.Noyau;



import TP.Noyau.myExceptions.*;
import java.util.Scanner;

public class Maison extends Habitable implements Comparable<Maison>
{

    /**************************** LES ATTRIBUTS ****************************/
    private int nb_etages=1;                 // le nombre d'étages
    //private int nb_facades=1;                // le nombre de façades
    private int num_maison=1;
    private boolean garage = false;          // est ce qu'il y a un garage ou pas
    private int superficie_garage=0;         // (s'il y en a) la superficie du garage
    private boolean piscine= false;          // est ce qu'il y a une piscine ou pas
    private int superficie_piscine=0;        // (s'il y en a) la superficie de la piscine
    private boolean jardin= false;           // est ce qu'il y a un jardin ou pas
    private int superficie_jardin=0;         // (s'il y en a) la superficie du jardin

    private String string_temp;
    private int int_temp;
    private final Scanner sc = new Scanner(System.in);

    /**************************** LES METHODES ****************************/
    public Maison()throws InfoBienErronees, SuperficieHabitableImpossible{
        num_bien++;
        num_bien_actu=num_bien;
        creer_bien();
        System.out.println(num_bien_actu);

    }
    public Maison(Transaction trans, double prix_prop, int code_wilaya, int superficie){
        this.trans=trans;
        this.prix_prop=prix_prop;
        this.code_wilaya=code_wilaya;
        this.superficie=superficie;
        num_bien++;
        num_bien_actu=num_bien;
        calcul_prix();
    }
    public Maison(Transaction trans, double prix_prop, int code_wilaya, String s, int superficie){
        this.trans=trans;
        this.prix_prop=prix_prop;
        this.code_wilaya=code_wilaya;
        this.superficie=superficie;
        switch(s){
            case "jardin": jardin=true; break;
            case "garage": garage=true; break;
            case "piscine": piscine = true; break;
        }
        num_bien++;
        num_bien_actu=num_bien;
        calcul_prix();
    }
    public Maison(Transaction trans, double prix_prop, int code_wilaya, String s1, int superficie, int code_wilaya_ech){
        this.trans=trans;
        if (trans==Transaction.echange) this.code_wilaya_ech=code_wilaya_ech;
        this.prix_prop=prix_prop;
        this.code_wilaya=code_wilaya;
        this.superficie=superficie;
        switch(s1){
            case "jardin": jardin=true; break;
            case "garage": garage=true; break;
            case "piscine": piscine = true; break;
        }
        num_bien++;
        num_bien_actu=num_bien;
        calcul_prix();
    }

    public  double calcul_prix(){
        switch (trans){
            case location:{
                if (superficie < 60){
                    if ((prix_prop/superficie)<50000){ prix_final = 1.01 * prix_prop; }
                    else {prix_final = 1.015 * prix_prop;}
                }
                else if (superficie > 59 && superficie < 151){
                    if ((prix_prop/superficie)<50000){ prix_final = 1.02 * prix_prop; }
                    else {prix_final = 1.025 * prix_prop;}
                }
                else {
                    if ((prix_prop/superficie)<50000){ prix_final = 1.03 * prix_prop; }
                    else {prix_final = 1.035 * prix_prop;}
                }
            }
            if ( piscine ) prix_final = prix_final + 50000;
            break;

            case echange:{
                if (prix_prop < 5000000){
                    if ((prix_prop/superficie)<50000){ prix_final = 1.03 * prix_prop; }
                    else {prix_final = 1.035 * prix_prop;}
                }
                else if (prix_prop > 4999999 && prix_prop < 15000001){
                    if ((prix_prop/superficie)<50000){ prix_final = 1.02 * prix_prop; }
                    else {prix_final = 1.025 * prix_prop;}
                }
                else {
                    if ((prix_prop/superficie)<70000){ prix_final = 1.01 * prix_prop; }
                    else {prix_final = 1.02 * prix_prop;}
                }
            }
            if ( piscine || garage || jardin) prix_final = 1.005 * prix_final;
            if (code_wilaya_ech>0) prix_final=prix_final*1.0025;
            break;

            case vente:{
                if (prix_prop < 5000000){
                    if ((prix_prop/superficie)<50000){ prix_final = 1.03 * prix_prop; }
                    else {prix_final = 1.035 * prix_prop;}
                }
                else if (prix_prop > 4999999 && prix_prop < 15000001){
                    if ((prix_prop/superficie)<50000){ prix_final = 1.02 * prix_prop; }
                    else {prix_final = 1.025 * prix_prop;}
                }
                else {
                    if ((prix_prop/superficie)<70000){ prix_final = 1.01 * prix_prop; }
                    else {prix_final = 1.02 * prix_prop;}
                }
            }
            if ( piscine || garage || jardin) prix_final = 1.005 * prix_final;
            break;
        }
        return prix_final;}

    public  void creer_bien()throws InfoBienErronees, SuperficieHabitableImpossible{
        System.out.println("    Veuillez inserer les informations suivantes de votre maison:");
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

        System.out.println("     Le numero de la maison");
        num_maison = sc.nextInt();

        System.out.println("    Le nombre de pieces:");
        nb_pieces = sc.nextInt();

        System.out.println("    Le nombre d'etages:");
        nb_etages= sc.nextInt();

        System.out.println("    Le nombre de façades:");
        nb_facades = sc.nextInt();
        if (nb_facades>5 || nb_facades<1){
            throw new InfoBienErronees("     LES INFO DE CE BIEN SONT ERRONER...(  le nombre de façade est > 5  !!!!!)");
        }

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

        System.out.println("    DES OPTIONS: Est ce qu'il y a un garage?");
        string_temp = sc.next();
        while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
            System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
            string_temp = sc.next();
        }
        if (string_temp.equals("oui") || string_temp.equals("yes")) garage = true;
        else if (string_temp.equals("non") || string_temp.equals("no")) garage = false;
        if (garage){
            System.out.println("    La superficie de la garage:");
            superficie_garage = sc.nextInt();
        }

        System.out.println("    Est ce qu'il y a un jardin?:");
        string_temp = sc.next();
        while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
            System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
            string_temp = sc.next();
        }
        if (string_temp.equals("oui") || string_temp.equals("yes")) jardin = true;
        else if (string_temp.equals("non") || string_temp.equals("no")) jardin = false;
        if (jardin){
            System.out.println("    La superficie de la jardin:");
            superficie_jardin = sc.nextInt();
        }

        System.out.println("    Est ce qu'il y a une piscine?:");
        string_temp = sc.next();
        while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
            System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
            string_temp = sc.next();
        }
        if (string_temp.equals("oui") || string_temp.equals("yes")) piscine = true;
        else if (string_temp.equals("non") || string_temp.equals("no")) piscine = false;
        if (piscine){
            System.out.println("    La superficie de la piscine:");
            superficie_piscine = sc.nextInt();
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
        if ( superficie-superficie_garage-superficie_jardin-superficie_piscine<10) {
            throw new SuperficieHabitableImpossible("   LA SUPERFICIE HABITABLE EST INFERIEURE A 10 M^2 CE QUI EST IMPOSSIBLE");
        }
        if ( num_maison < 1) throw new InfoBienErronees("     Le numero du bien ext inferieure à 1 !!!!");
        if ( !(nb_facades > 0 && nb_facades < 5 )) throw new InfoBienErronees("    Le nombre de façade de ce bien est superierure à 5!!!");
    }

    public void afficher(){
        System.out.println("    Le numero  du bien: " + num_bien_actu + myClass());
        System.out.println("    La date d'ajout au systeme: "+ date);
        System.out.println("    La wilaya: " + Wilaya.get(code_wilaya));
        System.out.println("    La superficie total du bien: " + superficie);
        System.out.println("    Le numero de la maison: "+ num_maison);

        System.out.println("    DES OPTIONS:");
        string_temp = (garage) ? "oui" : "non";
        System.out.println("    Un garage: " + string_temp);
        string_temp = (garage) ? "oui" : "non";
        System.out.println("    Un jardin: " + string_temp);
        string_temp = (garage) ? "oui" : "non";
        System.out.println("    Une piscine: " + string_temp);

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
        System.out.println("    Le nombre de pieces: " + nb_pieces);
        System.out.println("    Le nombre d'etages: " + nb_etages);
        System.out.println("    Le nombre de façades: " + nb_facades);
        string_temp = (meuble) ? "oui" : "non";
        System.out.println("    Est ce qu'il meublé?: " + string_temp );
        if (meuble){System.out.println("    La liste des meubles: " + list_meuble);}
        System.out.println("    le descriptif: " + descriptif);
        System.out.println("    La date d'ajout: ");

    }

    public void modifier_infos_prin(){
        System.out.println("    Voici la liste des informations principales de cette maison");         System.out.println("    Le numero  du bien: " + num_bien);
        System.out.println("    1.La wilaya: " + Wilaya.get(code_wilaya));
        System.out.println("    2.La superficie total du bien: " + superficie);

        System.out.println("    DES OPTIONS:");
        string_temp = (garage) ? "oui" : "non";
        System.out.println("    3.Un garage: " + string_temp);
        string_temp = (garage) ? "oui" : "non";
        System.out.println("    4.Un jardin: " + string_temp);
        string_temp = (garage) ? "oui" : "non";
        System.out.println("    5.Une piscine: " + string_temp);

        System.out.println("    6.Le type de trasaction:  " + trans);
        if (trans ==Transaction.echange ) System.out.println("    La wilaya d'échange souhaité: " + Wilaya.get(code_wilaya_ech));
        System.out.println("    7.Le prix: " + prix_final);
        System.out.println("    8.Le prix est negociable: " + prix_nego);
        //System.out.println("    9.Le propriétaire: " + prop.nom + " " + prop.prenom);
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

                case 3: System.out.println("    DES OPTIONS: Est ce qu'il y a un garage?");
                    string_temp = sc.next();
                    while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
                        System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
                        string_temp = sc.next();
                    }
                    if (string_temp.equals("oui") || string_temp.equals("yes")) garage = true;
                    else if (string_temp.equals("non") || string_temp.equals("no")) garage = false;
                    if (garage){
                        System.out.println("    La superficie de la garage:");
                        superficie_garage = sc.nextInt();
                    }
                    break;

                case 4: System.out.println("    Est ce qu'il y a un jardin?:");
                    string_temp = sc.next();
                    while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
                        System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
                        string_temp = sc.next();
                    }
                    if (string_temp.equals("oui") || string_temp.equals("yes")) jardin = true;
                    else if (string_temp.equals("non") || string_temp.equals("no")) jardin = false;
                    if (jardin){
                        System.out.println("    La superficie de la jardin:");
                        superficie_jardin = sc.nextInt();
                    }
                    break;

                case 5: System.out.println("    Est ce qu'il y a une piscine?:");
                    string_temp = sc.next();
                    while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
                        System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
                        string_temp = sc.next();
                    }
                    if (string_temp.equals("oui") || string_temp.equals("yes")) piscine = true;
                    else if (string_temp.equals("non") || string_temp.equals("no")) piscine = false;
                    if (piscine){
                        System.out.println("    La superficie de la piscine:");
                        superficie_piscine = sc.nextInt();
                    }
                    break;

                case 6: System.out.println("    Quel est le type de trasaction?\n      1-Location");
                    System.out.println("      2-Echange\n      3-Vente");
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

                case 7: System.out.println("    Le prix (c'est le prix du proprietaire sans les frais ajoutés!):");
                    prix_prop = sc.nextInt();
                    calcul_prix();
                    break;

                case 8: System.out.println("    Est ce que le prix est negociable?:");
                    string_temp = sc.next();
                    while(!(string_temp.equals("oui") || string_temp.equals("yes") || string_temp.equals("non") || string_temp.equals("no"))){
                        System.out.println(" REPONDEZ PAR OUI(YES) OU NON(NO)");
                        string_temp = sc.next();
                    }
                    if (string_temp.equals("oui") || string_temp.equals("yes")) prix_nego = true;
                    else if (string_temp.equals("non") || string_temp.equals("no")) prix_nego = false;
                    break;

                case 9: System.out.println("    Il est impossible de modifier le proprietaire ");
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
        System.out.println("    2.Le nombre de pieces: " + nb_pieces);
        System.out.println("    3.Le nombre d'etages: " + nb_etages);
        System.out.println("    4.Le nombre de façades: " + nb_facades);
        string_temp = (meuble) ? "oui" : "non";
        System.out.println("    5.Est ce qu'il meublé?: " + string_temp );
        if (meuble){System.out.println("    La liste des meubles: " + list_meuble);}
        System.out.println("    6.Le descriptif: " + descriptif);

        System.out.println("    ** Choisir le numero du champ que vous voulez modifier (0 pour terminer)");
        System.out.println("      Votre choix: ");
        int_temp = sc.nextInt();
        while(int_temp>0){
            switch(int_temp){
                case 1: System.out.println("    L'adresse exact:");
                    adr_exact = sc.next();
                    break;

                case 2: System.out.println("    Le nombre de pieces:");
                    nb_pieces = sc.nextInt();
                    break;

                case 3: System.out.println("    Le nombre d'etages");
                    nb_etages = sc.nextInt();
                    break;

                case 4: System.out.println("    Le nombre de façades:");
                    nb_facades = sc.nextInt();
                    break;

                case 5: System.out.println("    Est ce qu'il meuble?:");
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

                case 6: System.out.println("    Le descriptif");
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
        //systeme.msg_visiteurs.add(msg_visiteur);
        System.out.println("    MERCI!");
    }

    public boolean correct() throws SuperficieHabitableImpossible, InfoBienErronees {
        if ( superficie-superficie_garage-superficie_jardin-superficie_piscine<10) {
            throw new SuperficieHabitableImpossible("   LA SUPERFICIE HABITABLE EST INFERIEURE A 10 M^2 CE QUI EST IMPOSSIBLE");
        }
        if (nb_facades>5){
            throw new InfoBienErronees("     LES INFO DE CE BIEN SONT ERRONER...(  le nombre de façade est > 5  !!!!!)");
        }
        return true;
    }

    public String myClass(){ return "maison";}

    @Override
    public int compareTo(Maison obj) {
        if ( adr_exact.equals(obj.adr_exact) && superficie== obj.superficie && code_wilaya== obj.code_wilaya && garage== obj.garage && piscine== obj.piscine && jardin== obj.jardin && num_maison== obj.num_maison ) return 0;
        else if (date.compareTo(obj.date)>0 || date.compareTo(obj.date)==0) return 1;
        return -1;
    }
}

