package vue;

import IG.Texte;
import IG.ZoneCliquable;

import javax.swing.*;
import java.awt.*;

/**
 * class abstraite representant l'interface graphique des des carte du jeu
 * il y a plusieur carte : carte d'equipement, carte Aventurier, carte Joueur, carte tempete, carte defausse tempete
 */
public abstract class Carte extends ZoneCliquable {
    private int gap ;
    /**
     * constructeur permetant de creer la carte
     * @param x la largeur
     * @param y la hauteur
     * @param color la couleur du border
     * @param label ce qui sera ecrit sur la carte
     */
    public Carte(int x, int y, Color color, String label){
        super(x, y);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(color, 3));
        this.add(new Texte(label));
        this.gap = 0;
    }
    /**
     * constructeur permetant de creer la carte avec un gap
     * @param x la largeur
     * @param y la hauteur
     * @param color la couleur du border
     * @param label ce qui sera ecrit sur la carte
     * @param g c'est l'espace entre les lignes
     */
    public Carte(int x, int y, Color color, String label,int g){
        this(x,y,color,label);
        this.gap = g;
    }

    /**
     * méthode permetant d'ajouter un element dans une carte
     * @param c la composente JComponent qu'on souhaite ajouter
     */
    public void add(JComponent c){

        super.add(c);
        super.add(Box.createVerticalStrut(this.gap));
    }
    public abstract void clicGauche();
    public abstract void clicDroit();

}