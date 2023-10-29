package IG;

import javax.swing.*;

/**
 * class pour une zone de texte modifiable
 */
public class Texte extends JLabel{

    /**
     * @param texte le text initial qui sera affiche dans la zone
     */
    public Texte(String texte){
        super(texte);
    }

    /**
     * Methode pour la modification du texte.
     * @param texte le nouveau texte a afficher dans la zone
     */
    public void changeTexte(String texte){
        this.setText(texte);
        this.repaint();
    }
}