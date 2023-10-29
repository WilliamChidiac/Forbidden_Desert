package controlleur;

import modele.Desert;
import vue.*;

public class ControlleurJeu {
    private static Desert desert;
    private static VueDesert vuedesert;
    private static VueCarteDefausse defausse;
    private static VueJoueurs joueur;
    private static VueCartesEquipement vueEquipement;
    private static int id_Donneur;
    private static int idJoueurInventaire;

    public static Desert getDesert(){return desert;}
    public static void setDesert(Desert d){desert = d;}
    public static void setDefausse(VueCarteDefausse vcd){defausse = vcd;}
    public static void setVueJoueur(VueJoueurs j){joueur = j;}
    public static void setVueEquipement(VueCartesEquipement vce){vueEquipement = vce;}
    public static void setVuedesert(VueDesert d){vuedesert = d;}

    public static void clicGaucheCase(int idCase){
        desert.clicGaucheCase(idCase);
    }
    public static void clicDroitCase(int idcase){
        desert.clicDroitCase(idcase);
    }
    public static void clicTempete(){
        desert.clicTempete();
    }
    public static void clicDefausse(){
        defausse.setVisible();
    }
    public static void clicEquipement(){
        desert.clicEquipement();
    }
    public static void clicEau(int idJoueur){
        // appeler le modele pou qu'il rende les joueur adjacent
        // une fois le calcule est fini tu veut qu'il notify les
        id_Donneur = idJoueur;
        desert.clicEau(idJoueur);
        joueur.setV(true);
    }
    public static void clicReceveur(int idJoueur){
        desert.echangeEau(id_Donneur, idJoueur);
        joueur.setV(false);
    }
    public static void clicExploration(){desert.clicExploration();}
    public static void clicPrendrePiece(){desert.clicPrendrePiece();}
    public static void clicFinTour(){desert.clicFinTour();}
    public static void IdJoueurInventaire(int idJoueur){idJoueurInventaire = idJoueur;}
    public static void utiliseInventaire(int idInventaire){
        desert.clicInventaire(idJoueurInventaire, idInventaire);
    }
}
