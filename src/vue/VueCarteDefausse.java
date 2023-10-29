package vue;

import IG.Fenetre;
import IG.Texte;
import Observer.Observer;
import controlleur.ControlleurJeu;
import modele.CarteTempete;
import modele.CarteTempete.*;

import javax.swing.*;
import java.awt.*;


public class VueCarteDefausse extends Carte implements Observer{
    private JFrame hist;
    private Texte mouvement;
    private CarteTempete carte;

    public VueCarteDefausse(CarteTempete carte) {
        super(100,150, Color.RED,"",10);
        Texte nom = new Texte("Défausse");
        mouvement = new Texte("");
        this.add(nom);
        this.add(mouvement);
        hist = new JFrame("Historique");
        hist.setSize(400,400);
        hist.setLayout(new GridLayout(10, 1, 2, 2));
        this.carte = carte;
        carte.addObserver(this);
        ControlleurJeu.setDefausse(this);
    }
    public void setVisible(){
        hist.setVisible(true);
    }

    /**
     * fonction update de la carte defausse. Met à jour l'affichage de la carte et de la fenetre historique(hist)
     * en fonction de la tempete tirée.
     */
    public void update(){
        TypeCarteT nvType =this.carte.getCarte();
        if(nvType == TypeCarteT.VENT_SOUFFLE){
            String message = "<html>"+nvType.getType()+"<br/>"+carte.getBoussol()+"("+carte.getDistance()+")</html>";
            this.mouvement.changeTexte(message);
            this.hist.add(new Texte(message));
        }else{
            this.mouvement.changeTexte(nvType.getType());
            this.hist.add(new Texte(nvType.getType()));
        }
    }

    public void clicGauche() {
        ControlleurJeu.clicDefausse();
    }

    public void clicDroit() {
    }
}
