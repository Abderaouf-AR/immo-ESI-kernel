package TP.Noyau;

import TP.Noyau.myExceptions.*;

import java.util.*;

public abstract class Bien
{

    /**************************** LES ATTRIBUTS ****************************/
    protected String adr_exact="";                    // l'adress exact du bien
    public  static TreeMap Wilaya = new TreeMap<Integer, String>();// la wilaya ou le bien se situe
    public  static Integer[] wilaya_prix = new Integer[48];
    protected int code_wilaya=0;                   // le code de la wilaya
    protected int code_wilaya_ech=0;               // le code de la wilaya d'échange souhaité
    protected int superficie=0;                    // la superficie du bien
    protected int nb_facades=1;
    protected Proprietaire prop;                   // le propriétaire du bien
    protected Transaction trans;                   // le type de transaction (vente, échange ou bien location)
    protected boolean prix_nego= false;            // est ce que le prix est negociable ou pas
    protected double prix_prop=0;                  // le prix proposé par le propriétaire
    protected double prix_final=0;                 // le prix final du bien aprés l'ajout des frais de l'agence
    protected String descriptif="";                // un descriptif pour le bien
    protected Calendar date=Calendar.getInstance();                      // la date d'insertion dans le système
    protected String photo="";                     // l'URL des photos du bien (s'il y en a)
    protected boolean insere= false;               // est ce que le bien est déjà inseré ou pas
    protected int num_annonce=0;                   // le numero d'annonce
    static int num_bien = 0;                       // le numero des biens crées
    protected int num_bien_actu;                   // le num du bien actuel
    protected String msg_visiteur;                 // le message du visiteur
    private   static int i;                        // indice qq


    /**************************** LES METHODES ****************************/

    //public abstract boolean correct(Bien b);
    public abstract double calcul_prix();
    public abstract void creer_bien() throws InfoBienErronees, SuperficieHabitableImpossible;
    public abstract void afficher_plus();
    public abstract void afficher();
    public abstract void modifier_infos_prin();
    public abstract void modifier_detail();
    public abstract String myClass();
    public static void init_wilaya(){
        Wilaya.put(04, "Oum el bouaghi");
        Wilaya.put(05, "Batna");
        Wilaya.put(40, "Khenchla");
        for(i = 0; i != 48; i++){
            wilaya_prix[i]=0;
        }
        wilaya_prix[3]=100000;
        wilaya_prix[4]=30000;
        wilaya_prix[39]=60000;
    }
    public int getNum_bien_actu(){return num_bien_actu;}
}


