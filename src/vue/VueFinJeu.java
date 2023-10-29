package vue;

import IG.Fenetre;
import IG.Texte;
import Observer.Observer;
import controlleur.ControlleurJeu;
import modele.Desert;

import javax.swing.*;
import java.awt.*;

public class VueFinJeu extends Fenetre implements Observer{
    private Texte t = new Texte("");
    Desert desert = ControlleurJeu.getDesert();
    public VueFinJeu(){
        super("fin de partie",600,400);
        this.t.setFont(new Font("Crystal", Font.BOLD, 20));
        this.add(t);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        desert.addObserver(this);
    }
    /**
     * La fonction "update" vérifie si le joueur a gagné ou perdu la partie et affiche un message correspondant
     * dans la fenêtre de jeu. Ensuite, elle rend cette fenêtre visible.
     * **/
    public void update(){

        if(desert.isGagne()){
            this.t.changeTexte("VOUS AVEZ GAGNÉ!");
            this.setVisible(true);

        }else if(desert.isPerdu()){
            this.t.changeTexte("VOUS AVEZ PERDU");
            this.setVisible(true);
        }else{
            this.setVisible(false);
        }
    }
}
