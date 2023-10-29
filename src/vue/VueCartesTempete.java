package vue;

import IG.Fenetre;
import IG.Texte;
import Observer.Observer;
import controlleur.ControlleurJeu;
import modele.Desert;

import java.awt.*;
public class VueCartesTempete extends Carte implements Observer {
    private Desert d;
    private Texte tire = new Texte("");
    public VueCartesTempete() {
        super(100,150, Color.RED,"",30);
        d = ControlleurJeu.getDesert();
        Texte nom = new Texte("Paquet Tempête");
        this.add(nom);
        this.add(tire);
        tire.setFont(new Font("Arial", Font.BOLD, 20));
        d.addObserver(this);

    }

    public void clicGauche() {
        ControlleurJeu.clicTempete();
    }
    /**
     * fonction update de la carte tempete.Change l'affichage quand le joueur doit tirer une carte tempete
     */
    public void update(){
        int nb_action = d.getJoueurJouant().getAction();
        int nbcarte = d.getNombreCarteTempete();
        if(nbcarte != 0 && nb_action == 0)
            this.tire.changeTexte("piochez " + nbcarte + "carte.");
        else
            this.tire.changeTexte("");

    }

    public void clicDroit() {

    }
}

