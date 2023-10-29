package vue;

import IG.*;
import IG.Button;
import controlleur.ControlleurDebutJeu;


import javax.swing.*;

public class MenuNombreJoueur extends JPanel{
    private JSpinner numberSelector;

    /**
     * creer le menu dans lequel le joueur fait le choix du nombre de joueur
     * @param frame
     */
    public MenuNombreJoueur(Fenetre frame){
        ControlleurDebutJeu.setF(frame);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        SpinnerModel model = new SpinnerNumberModel(2, 2, 5, 1);
        numberSelector = new JSpinner(model);
        Texte texte = new Texte("Combien de joueur etes vous a jouer la Partie?");
        ButtonJouer jouer = new ButtonJouer(frame);
        BouttonQuitter quitter = new BouttonQuitter(frame);
        this.add(texte);
        this.add(numberSelector);
        this.add(jouer);
        this.add(quitter);
        this.setVisible(true);
    }


    /**
     * @return le nombre d joueur choisi par l'utilisateur,
     * dans le contexte du jeu ce nombre vari entre (2 et 5)
     */
    public int getNombreJoueur(){
        return (int) this.numberSelector.getValue();
    }


    class ButtonJouer extends Button {
        /**
         * creer un bouton cliquable pour commencer à jouer
         * @param fenetre fenetre dans laquelle va se situer le bouton
         */
        public ButtonJouer(Fenetre fenetre){
            super("Jouer", fenetre);
        }
        /**
         * clic gauche du bouton jouer communiquant avec le controlleur
         */
        public void clicGauche(){
            int nb_jou = getNombreJoueur();
            ControlleurDebutJeu.clicJoueur(nb_jou);
            this.setVisible(false);
        }
    }
    class BouttonQuitter extends Button{
        /**
         * creer un bouton cliquable pour quitter le jeu
         * @param fenetre fenetre dans laquelle va se situer le bouton
         */
        public BouttonQuitter(Fenetre fenetre){
            super("Quitter", fenetre);
        }
        /**
         * clic gauche du bouton quitter communiquant avec le controlleur
         */
        public void clicGauche(){
            ControlleurDebutJeu.clicQuitter();
        }
    }

}

