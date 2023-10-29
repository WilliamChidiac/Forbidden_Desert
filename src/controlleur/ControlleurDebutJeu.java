package controlleur;

import IG.Fenetre;
import modele.PaquetAventurier;
import vue.MenuInscription;

public class ControlleurDebutJeu {
    private static PaquetAventurier paquetAventurier = new PaquetAventurier();
    private static Fenetre f;
    public ControlleurDebutJeu(){}
    public static void setF(Fenetre fenetre){f = fenetre;}
    public static PaquetAventurier getPaquetAventurier(){return paquetAventurier;}
    public static void clicJoueur(int nbj){
        paquetAventurier.setNombreJoueur(nbj);
        MenuInscription mI = new MenuInscription(f);
        f.ajouteElement(mI, "menu inscription");
        f.dessinePanel("menu inscription");
        f.dessineFenetre();
    }
    public static void clicQuitter(){
        f.dispose();
    }
    public static void clicAventurier(){paquetAventurier.affichedesc();}
    public static void clicSuivant(String nom){
        paquetAventurier.inscription(nom);
    }
}
