package TP.Noyau;

import TP.Noyau.myExceptions.*;

public abstract class Habitable extends Bien
{
    /**************************** LES ATTRIBUTS ****************************/
    protected boolean meuble= false;          // est ce que l'appartement est meublé ou pas
    protected String list_meuble="";            // la liste des meubles (s'il existe)
    protected int nb_pieces=1;                 // le nombre de pieces (chambres+salon)



    public abstract double calcul_prix();
    public abstract void creer_bien() throws InfoBienErronees, SuperficieHabitableImpossible;
    public abstract void afficher_plus();
    public abstract void afficher();
    public abstract void modifier_infos_prin();
    public abstract void modifier_detail();
    public abstract String myClass();
}

