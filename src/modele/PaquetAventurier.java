package modele;

import java.util.ArrayList;
import java.util.Collections;

import Observer.Observable;
import controlleur.ControlleurJeu;


public class PaquetAventurier  extends Observable {
    ArrayList<Aventurier> paquet;
    private int nombreJoueur;
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    private boolean vaMenu;
    private boolean BouttonSuivant;
    private boolean BouttonAventurier;
    private String desc;

    /**
     * fenetre graphique qui represente le paquet des aventurier que l'on pioche lors de l'inscription des joueur
     */
    public PaquetAventurier(){
        paquet = new ArrayList<>();
        paquet.add(new Aventurier(Aventurier.TypeAventurier.NAVIGATRICE));
        paquet.add(new Aventurier(Aventurier.TypeAventurier.PORTEUSE));
        paquet.add(new Aventurier(Aventurier.TypeAventurier.METEOROLOGUE));
        paquet.add(new Aventurier(Aventurier.TypeAventurier.Explorateur));
        paquet.add(new Aventurier(Aventurier.TypeAventurier.METEOROLOGUE));
        paquet.add(new Aventurier(Aventurier.TypeAventurier.ALPINISTE));
        Collections.shuffle(paquet);

    }
    public String getDesciption(){return this.desc;}
    public boolean getMenu(){return this.vaMenu;}
    public boolean isSuivant(){return this.BouttonSuivant;}
    public boolean isBouttonAventurier(){return this.BouttonAventurier;}
    public void affichedesc(){
        BouttonAventurier = true;
        desc = this.paquet.get(0).getDescription();
        notifyObservers();
        BouttonAventurier = false;
    }
    public void setNombreJoueur(int nb){
        nombreJoueur = nb;
    }
    public void inscription(String nom){
        BouttonSuivant = true;
        Aventurier aventurier = paquet.remove(0);
        Joueur j = new Joueur(nom, aventurier);
        joueurs.add(j);
        vaMenu = joueurs.size() == nombreJoueur;
        if(vaMenu){
            Desert d = new Desert(joueurs);
            ControlleurJeu.setDesert(d);
        }
        notifyObservers();
        BouttonSuivant = false;
    }

}