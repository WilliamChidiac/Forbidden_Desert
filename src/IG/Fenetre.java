package IG;

import javax.swing.*;
import java.awt.*;

/**
 * class des fenetre graphiques.
 */

public class Fenetre extends JFrame{

    //Ensemble des elements contenus dans la fenetre graphique
    private JPanel elements;
    private CardLayout cards;
    /**
     * creation d'une fenetre graphique
     * il faut  quand meme dessiner la fenetre avec la methode , dessineFenetre()
     * @param nom
     */
    public Fenetre(String nom, int width, int height){
        super(nom);
        this.elements = new JPanel(new CardLayout());
        this.cards = this.getLayout();
        this.setContentPane(elements);
        this.setSize(width, height);
        this.setPreferredSize(new Dimension(600, 600));
        this.setVisible(true);
    }

    /**
     * ajout d'un element graphique dans la fenetre
     * @param elem
     */
    public void ajouteElement(JPanel elem, String label){
        elements.add(elem, label);
    }

    /**
     * prend le layout d'une fenetre et le rend sous forme de carte si il ne l'est pas deja
     * @return le layout de la Fenetre
     */
    public CardLayout getLayout(){
        return (CardLayout) this.elements.getLayout();
    }

    /**
     * affiche la fenetre
     */
    public void dessineFenetre() {

        this.pack();
        this.setVisible(true);

    }

    /**
     * affiche un certain Jpannel a la fenetre
     * @param panel_name le nom associe a ce panneau dans le cardLayout de la fenetre
     */
    public void dessinePanel(String panel_name){
        CardLayout c = this.getLayout();
        c.show(elements, panel_name);
        this.repaint();

    }
}
