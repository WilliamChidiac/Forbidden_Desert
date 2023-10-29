package vue;


import IG.Texte;
import controlleur.ControlleurJeu;
import modele.Desert;
import Observer.Observer;

import java.awt.*;

public class VueCartesEquipement extends Carte implements Observer{
    private Desert d;
    private Texte pioche = new Texte("");
    public VueCartesEquipement(){
        super(100,150, Color.BLUE,"",0);
        d = ControlleurJeu.getDesert();
        Texte nom = new Texte("Paquet Equipement");
        pioche.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(nom);
        this.add(pioche);
        ControlleurJeu.setVueEquipement(this);
        d.addObserver(this);
    }
    public void clicGauche() {
        ControlleurJeu.clicEquipement();
    }
    public void update(){
        if (this.d.isFreezeForEquipement()){
            pioche.changeTexte("tirez 1 cartes");
        }else{
            pioche.changeTexte("");
            revalidate();
            repaint();
        }
    }

    public void clicDroit() {

    }
}
