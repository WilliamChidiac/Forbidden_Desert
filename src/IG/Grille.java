package IG;

import javax.swing.*;
import java.awt.*;

/**
 * Class pour un exemple d'element graphique dispose en grille
 * On pourra s'en sevire pour affiche :
 * les carte des personnage
 * le plateau de jeu
 * les carte equipement
 */
public class Grille extends JPanel{

    /**
     * prend en parametre la dimension de la grille et l'espace qu'il y aura entre les differents elements de la grille
     * @param hauteur Nombre de lignes
     * @param largeur Nombre de colonnes
     * @param espace_horizontal espace entre les colonnes
     * @param espace_vertical espace entre les lignes
     */
    public Grille(int hauteur, int largeur, int espace_horizontal, int espace_vertical){
        setLayout(new GridLayout(hauteur, largeur, espace_horizontal, espace_vertical));
    }

    /**
     * Ajoute un element dans la grille
     * Notons que les element sont ajoute de gauche a droite, de haut en bas
     * @param element l'element a ajouter
     */
    public void ajouteElement(JComponent element){
        this.add(element);
    }
}

