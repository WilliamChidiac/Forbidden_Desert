package IG;

import javax.swing.*;
import java.awt.*;

/**
 * class qui permet de creer des bouton
 */
public abstract class Button extends JButton{
    protected Fenetre fenetre;

    /**
     * constructeur qui permet d'ecrire du texte dans le bouton et
     * de choisir une fenetre sur lequel le bouton agira
     * @param texte le texte ecrit dans la case du bouton
     * @param fenetre la fenetre sur laquel le bouton agit
     */
    public Button(String texte, Fenetre fenetre){
        this(texte);
        this.fenetre = fenetre;
    }
    public Button(String texte){
        super(texte);
        this.setVisible(true);
        this.addActionListener((e) -> {
            clicGauche();
        });
    }


    /**
     * rend le bouton pressable
     */
    public void active(){
        this.setEnabled(true);
    }

    /**
     * rend le boutton non pressable
     */
    public  void desactive(){
        this.setEnabled(false);
    }

    /**
     * action a fair si le boutton est presse
     */
    public abstract void clicGauche();

}