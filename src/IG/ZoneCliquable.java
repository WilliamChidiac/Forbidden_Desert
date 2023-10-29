package IG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * classe pour une zonne pouvant recevoir des click de souris
 */
public abstract class ZoneCliquable extends JPanel implements MouseListener{

    private Texte texte;

    /**
     * constructeur creant une zone cliquable plane
     * @param x width
     * @param y height
     */
    public ZoneCliquable(int x, int y){
        setPreferredSize(new Dimension(x, y));
        addMouseListener(this);
        setBackground(Color.WHITE);
    }

    /**
     * constructeur creant une zone cliquable contenant du texte
     * @param x width
     * @param y height
     * @param text le text a mettre dans la zone
     */
    public ZoneCliquable(String text, int x, int y){
        this(x, y);
        this.texte = new Texte(text);
        this.add(this.texte);
    }

    /**
     * change le texte de la zone
     * @param texte le nouveau texte a afficher
     */
    public void  changeTexte(String texte){
        this.texte.changeTexte(texte);
    }

    /**
     * Methode abstraite qui indique les actions a effectuer lors d'un click droit
     */
    public abstract void clicDroit();

    /**
     * Methode abstraite qui indique les actions a effectuer lors d'un click gauche
     */
    public abstract void clicGauche();


    public void mouseClicked(MouseEvent e){
        if (SwingUtilities.isRightMouseButton(e))
            this.clicDroit();
        else
            this.clicGauche();
    }

    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent event){}
    public void mouseReleased(MouseEvent event){}
}